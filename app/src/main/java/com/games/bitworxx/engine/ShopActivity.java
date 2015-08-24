package com.games.bitworxx.engine;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.vending.billing.IInAppBillingService;
import com.games.bitworxx.engine.util.IabException;
import com.games.bitworxx.engine.util.IabHelper;
import com.games.bitworxx.engine.util.IabResult;
import com.games.bitworxx.engine.util.Inventory;
import com.games.bitworxx.engine.util.Purchase;
import com.games.bitworxx.engine.util.ShopHelperFlyer;
import com.games.bitworxx.engine.util.SkuDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ShopActivity extends Activity {

        ShopHelperFlyer ShopHelper;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ShopHelper.mHelper != null) ShopHelper.mHelper.dispose();
        ShopHelper.mHelper = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data)
    {
        if (!ShopHelper.mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShopHelper=new ShopHelperFlyer(this);
        setContentView(R.layout.activity_shop);





    }

}
