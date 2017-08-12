package org.apache.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ContextMenu;

public class Contextmenu extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
//        Mywebview myWebView = (Mywebview) webView;

        Activity activity = cordova.getActivity();

        activity.unregisterForContextMenu(webView.getView());

        super.initialize(cordova, myWebView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("hideContextMenu")) {
            String message = args.getString(0);
            this.hideContextMenu(callbackContext);
            return true;
        }
        return false;
    }

    private void hideContextMenu(CallbackContext callbackContext) {
        callbackContext.success("This was a great success!");
    }
}