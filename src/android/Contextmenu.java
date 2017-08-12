package org.apache.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.cordova.mywebview.MyWebView;

public class Contextmenu extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        MyWebView myWebView = (MyWebView) webView;

        super.initialize(cordova, webView);
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