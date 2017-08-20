package mx.ferreyra.callnumber;

import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.CordovaWebViewEngine;
import android.view.View;

import java.util.Map;

import android.util.Log;

public class MyCordovaWebViewImpl extends CordovaWebViewImpl {

    @Override
    private View mCustomView;

    public MyCordovaWebViewImpl(CordovaWebViewEngine cordovaWebViewEngine) {
        this.engine = cordovaWebViewEngine;
    }

    @Override
    public void loadUrlIntoView(final String url, boolean recreatePlugins) {
        LOG.d(TAG, ">>> loadUrl(" + url + ")");
        if (url.equals("about:blank") || url.startsWith("javascript:")) {
            engine.loadUrl(url, false);
            return;
        }

        recreatePlugins = recreatePlugins || (loadedUrl == null);

        if (recreatePlugins) {
            // Don't re-initialize on first load.
            if (loadedUrl != null) {
                appPlugin = null;
                pluginManager.init();
            }
            loadedUrl = url;
        }

        // Create a timeout timer for loadUrl
        final int currentLoadUrlTimeout = loadUrlTimeout;
        final int loadUrlTimeoutValue = preferences.getInteger("LoadUrlTimeoutValue", 20000);

        // Timeout error method
        final Runnable loadError = new Runnable() {
            public void run() {
                stopLoading();
                LOG.e(TAG, "CordovaWebView: TIMEOUT ERROR!");

                // Handle other errors by passing them to the webview in JS
                JSONObject data = new JSONObject();
                try {
                    data.put("errorCode", -6);
                    data.put("description", "The connection to the server was unsuccessful.");
                    data.put("url", url);
                } catch (JSONException e) {
                    // Will never happen.
                }
                pluginManager.postMessage("onReceivedError", data);
            }
        };

        // Timeout timer method
        final Runnable timeoutCheck = new Runnable() {
            public void run() {
                try {
                    synchronized (this) {
                        wait(loadUrlTimeoutValue);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // If timeout, then stop loading and handle error
                if (loadUrlTimeout == currentLoadUrlTimeout) {
                    ((MyActivity) cordova.getActivity()).runOnUiThread(loadError);
                }
            }
        };

        final boolean _recreatePlugins = recreatePlugins;
        ((MyActivity) cordova.getActivity()).runOnUiThread(new Runnable() {
            public void run() {
                if (loadUrlTimeoutValue > 0) {
                    cordova.getThreadPool().execute(timeoutCheck);
                }
                engine.loadUrl(url, _recreatePlugins);
            }
        });
    }

    @Override
    public void showWebPage(String url, boolean openExternal, boolean clearHistory, Map<String, Object> params) {
        LOG.d(TAG, "showWebPage(%s, %b, %b, HashMap)", url, openExternal, clearHistory);

        // If clearing history
        if (clearHistory) {
            engine.clearHistory();
        }

        // If loading into our webview
        if (!openExternal) {
            // Make sure url is in whitelist
            if (pluginManager.shouldAllowNavigation(url)) {
                // TODO: What about params?
                // Load new URL
                loadUrlIntoView(url, true);
            } else {
                LOG.w(TAG, "showWebPage: Refusing to load URL into webview since it is not in the <allow-navigation> whitelist. URL=" + url);
            }
        }
        if (!pluginManager.shouldOpenExternalUrl(url)) {
            LOG.w(TAG, "showWebPage: Refusing to send intent for URL since it is not in the <allow-intent> whitelist. URL=" + url);
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            // To send an intent without CATEGORY_BROWSER, a custom plugin should be used.
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            Uri uri = Uri.parse(url);
            // Omitting the MIME type for file: URLs causes "No Activity found to handle Intent".
            // Adding the MIME type to http: URLs causes them to not be handled by the downloader.
            if ("file".equals(uri.getScheme())) {
                intent.setDataAndType(uri, resourceApi.getMimeType(uri));
            } else {
                intent.setData(uri);
            }
            ((MyActivity) cordova.getActivity()).startActivity(intent);
        } catch (android.content.ActivityNotFoundException e) {
            LOG.e(TAG, "Error loading url " + url, e);
        }
    }
}