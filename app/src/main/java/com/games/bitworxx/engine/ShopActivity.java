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
import com.games.bitworxx.engine.util.SkuDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ShopActivity extends Activity {
public final String TAG ="com.games.billing";

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
            = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result,
                                          Purchase purchase)
        {
            if (result.isFailure()) {
                // Handle error
                return;
            }
            else if (purchase.getSku().equals("buypack1")) {
                consumeItem();
                MainActivity.saveBuy(66,97);
            }

        }
    };

    public void consumeItem() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }
    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {

            if (result.isFailure()) {
                // Handle failure
            } else {
                mHelper.consumeAsync(inventory.getPurchase("buypack1"),
                        mConsumeFinishedListener);
            }
        }
    };
public IabHelper mHelper;
    public static boolean CanBuy=false;


    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(final IabResult result,
                                             final Inventory inventory) {

        if(result.isSuccess()) {

        }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        mHelper = new IabHelper(this, MainActivity.KEY);

        mHelper.startSetup(new
                                   IabHelper.OnIabSetupFinishedListener() {
                                       public void onIabSetupFinished(IabResult result) {
                                           if (result.isSuccess()) {
                                               CanBuy=true;
                                                mHelper.queryInventoryAsync(mGotInventoryListener);
                                           }
                                       }
                                   });




    }

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
            new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase,
                                              IabResult result) {

                    if (result.isSuccess()) {

                    } else {
                        // handle error
                    }
                }
            };
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data)
    {
        if (!mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void buyPack()
    {

        mHelper.launchPurchaseFlow(this,"buypack1",66,mPurchaseFinishedListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }



}
