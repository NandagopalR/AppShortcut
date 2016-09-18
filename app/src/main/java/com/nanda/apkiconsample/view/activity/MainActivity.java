package com.nanda.apkiconsample.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nanda.apkiconsample.R;
import com.nanda.apkiconsample.utils.AppConstants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mAddShortCut;
    private Button mRemoveShortCut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (mAddShortCut = (Button) findViewById(R.id.btnAddShortCut)).setOnClickListener(this);
        (mRemoveShortCut = (Button) findViewById(R.id.btnRemoveShortCut)).setOnClickListener(this);
    }

    /**
     * Method gets invoked when 'Create Shortcut' button is clicked
     */
    public void createShortCut(int imageResourceId) {
        Intent shortCutInt = new Intent(getApplicationContext(),
                MainActivity.class);
        shortCutInt.setAction(Intent.ACTION_MAIN);
        Intent addInt = new Intent();
        addInt.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortCutInt);
        addInt.putExtra(Intent.EXTRA_SHORTCUT_NAME, getResources().getString(R.string.app_name));
        addInt.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(getApplicationContext(),
                        imageResourceId));
        // Set Install action in Intent
        addInt.setAction(AppConstants.APP_SHORTCUT_ACTION_INSTALL);
        // Broadcast the created intent
        getApplicationContext().sendBroadcast(addInt);
    }

    /**
     * Method gets invoked when 'Remove Shortcut' button is clicked
     */
    public void removeShortCut() {
        Intent shortcutInt = new Intent(getApplicationContext(),
                MainActivity.class);
        shortcutInt.setAction(Intent.ACTION_MAIN);
        Intent addInt = new Intent();
        addInt.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutInt);
        addInt.putExtra(Intent.EXTRA_SHORTCUT_NAME, getResources().getString(R.string.app_name));
        // Set Uninstall action in Intent
        addInt.setAction(AppConstants.APP_SHORTCUT_ACTION_UNINSTALL);
        // Broadcast the created intent
        getApplicationContext().sendBroadcast(addInt);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddShortCut:
                createShortCut(R.drawable.ic_app_icon);
                break;
            case R.id.btnRemoveShortCut:
                removeShortCut();
                break;
        }
    }
}
