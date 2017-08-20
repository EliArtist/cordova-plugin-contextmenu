package mx.ferreyra.callnumber;

import android.app.Activity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.View;
import android.view.ContextMenu;

import android.util.Log;

public class MyActivity extends Activity {

    public MyActivity() {
        super();
        Log.i("YaskawaManuals", "My Activity is initialized");
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        Menu menu = mode.getMenu();
        menu.clear();

        Log.i("YaskawaManuals", "This is on start action mode callback!");

        super.onActionModeStarted(mode);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.i("YaskawaManuals", "ContextMenu gets created!");
        Log.i("YaskawaManuals", "has visible items: " + menu.hasVisibleItems());
        Log.i("YaskawaManuals", "size of menu items: " + menu.size());
    }

}