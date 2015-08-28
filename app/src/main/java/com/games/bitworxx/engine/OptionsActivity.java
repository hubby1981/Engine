package com.games.bitworxx.engine;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.games.bitworxx.engine.util.ShopHelperFlyer;


public class OptionsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_options);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void buyNodAds()
    {
        if(MainActivity.ShopHelper!=null)
            MainActivity.ShopHelper.buyNoAds();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data)
    {
        if (!MainActivity.ShopHelper.mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
