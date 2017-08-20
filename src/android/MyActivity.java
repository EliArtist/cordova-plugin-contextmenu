package mx.ferreyra.callnumber;

import android.app.Activity;
import android.view.ActionMode;
import android.view.Menu;

import android.util.Log;

public class MyActivity extends Activity {

    @Override
    public void onStartActionMode(ActionMode mode) {
        Menu menu = mode.getMenu();
        menu.clear();

        Log.i("YaskawaManuals", "This is on start action mode callback!");

        super.onActionModeStarted(mode);
    }

}