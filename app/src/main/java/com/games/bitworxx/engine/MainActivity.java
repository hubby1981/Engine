package com.games.bitworxx.engine;

import android.renderscript.RenderScript;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {


    public static Runnable Update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Update=new Runnable() {
            @Override
            public void run() {
                update();
            }
        };
        runOnUiThread(Update);
    }

    private void update()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.view).invalidate();
            }
        });
    }


}
