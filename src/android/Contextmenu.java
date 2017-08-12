package org.apache.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Contextmenu extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        //        MyWebView myWebView = (MyWebView) webView;
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

//protected class MyWebView extends CordovaWebView {
//
//    private ActionMode mActionMode = null;
//
//    @Override
//    public void onActionModeStarted(ActionMode mode) {
//        if (mActionMode == null) {
//            mActionMode = mode;
//            Menu menu = mode.getMenu();
//            // Remove the default menu items (select all, copy, paste, search)
//            menu.clear();
//
//            // If you want to keep any of the defaults,
//            // remove the items you don't want individually:
//            // menu.removeItem(android.R.id.[id_of_item_to_remove])
//
//            // Inflate your own menu items
////            mode.getMenuInflater().inflate(R.menu.my_custom_menu, menu);
//        }
//
//        super.onActionModeStarted(mode);
//    }
//
//    // This method is what you should set as your item's onClick
//    // <item android:onClick="onContextualMenuItemClicked" />
////    public void onContextualMenuItemClicked(MenuItem item) {
////        switch (item.getItemId()) {
////            case R.id.example_item_1:
////                // do some stuff
////                break;
////            case R.id.example_item_2:
////                // do some different stuff
////                break;
////            default:
////                // ...
////                break;
////        }
////
////        // This will likely always be true, but check it anyway, just in case
////        if (mActionMode != null) {
////            mActionMode.finish();
////        }
////    }
//
//    @Override
//    public void onActionModeFinished(ActionMode mode) {
//        mActionMode = null;
//        super.onActionModeFinished(mode);
//    }
//}