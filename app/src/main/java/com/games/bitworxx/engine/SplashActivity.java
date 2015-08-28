package com.games.bitworxx.engine;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(MainActivity.readBuy(51)!=0)
        {
            findViewById(R.id.adView).setVisibility(View.INVISIBLE);
        }
        else
        {
            findViewById(R.id.adView).setVisibility(View.VISIBLE);

            AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

            adRequestBuilder.addTestDevice("2A399D156F5F2E0FEE7B0056DD3D0D56");
            AdView view =(AdView) findViewById(R.id.adView);
            AdRequest r = adRequestBuilder.build();
            view.loadAd(r);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
