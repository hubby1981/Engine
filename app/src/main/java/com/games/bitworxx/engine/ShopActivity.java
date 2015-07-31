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
import java.util.List;


public class ShopActivity extends Activity {


    public static String Price1=null;
    public static String Price2=null;
    public static String Price3=null;
    public static String Price4=null;
    public static String Price5=null;
    public static String Price6=null;


    public String SKU_BUY1="buychar1";
    public String SKU_BUY2="buychar2";
    public String SKU_BUY3="buychar4";
    public String SKU_BUY4="buychar5";
    public String SKU_BUY5="buychar3";
    public String SKU_BUY6="buypack1";


    public int SKU_CODE_BUY1=61;
    public int SKU_CODE_BUY2=62;
    public int SKU_CODE_BUY3=63;
    public int SKU_CODE_BUY4=64;
    public int SKU_CODE_BUY5=65;
    public int SKU_CODE_BUY6=66;

    public int SKU_CODE2_BUY1=2;
    public int SKU_CODE2_BUY2=3;
    public int SKU_CODE2_BUY3=4;
    public int SKU_CODE2_BUY4=5;
    public int SKU_CODE2_BUY5=6;
    public int SKU_CODE2_BUY6=97;
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
            else {
                mHelper.consumeAsync(purchase, mConsumeFinishedListener);
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


            SkuDetails buypack = inventory.getSkuDetails(SKU_BUY6);
            if(buypack!=null)
            {
                Purchase  pOld = inventory.getPurchase(SKU_BUY6);
                if(pOld !=null)
                {
                    if(pOld.getToken()!=null)
                    {
                        MainActivity.saveBuy(SKU_CODE_BUY6,SKU_CODE2_BUY6);
                    }
                }
                Price6=buypack.getPrice();

            }

            SkuDetails buychar1 = inventory.getSkuDetails(SKU_BUY1);
            if(buychar1!=null)
            {
                Purchase  pOld = inventory.getPurchase(SKU_BUY1);
                if(pOld !=null)
                {
                    if(pOld.getToken()!=null)
                    {
                        MainActivity.saveBuy(SKU_CODE_BUY1,SKU_CODE2_BUY1);
                    }
                }
                Price1=buychar1.getPrice();

            }

            SkuDetails buychar2 = inventory.getSkuDetails(SKU_BUY2);
            if(buychar2!=null)
            {
                Purchase  pOld = inventory.getPurchase(SKU_BUY2);
                if(pOld !=null)
                {
                    if(pOld.getToken()!=null)
                    {
                        MainActivity.saveBuy(SKU_CODE_BUY2,SKU_CODE2_BUY2);
                    }
                }
                Price2=buychar2.getPrice();
            }

            SkuDetails buychar3 = inventory.getSkuDetails(SKU_BUY3);
            if(buychar3!=null)
            {
                Purchase  pOld = inventory.getPurchase(SKU_BUY3);
                if(pOld !=null)
                {
                    if(pOld.getToken()!=null)
                    {
                        MainActivity.saveBuy(SKU_CODE_BUY3,SKU_CODE2_BUY3);
                    }
                }
                Price3=buychar3.getPrice();
            }

            SkuDetails buychar4 = inventory.getSkuDetails(SKU_BUY4);
            if(buychar4!=null)
            {
                Purchase  pOld = inventory.getPurchase(SKU_BUY4);
                if(pOld !=null)
                {
                    if(pOld.getToken()!=null)
                    {
                        MainActivity.saveBuy(SKU_CODE_BUY4,SKU_CODE2_BUY4);
                    }
                }
                Price4=buychar4.getPrice();
            }

            SkuDetails buychar5 = inventory.getSkuDetails(SKU_BUY5);
            if(buychar5!=null)
            {
                Purchase  pOld = inventory.getPurchase(SKU_BUY5);
                if(pOld !=null)
                {
                    if(pOld.getToken()!=null)
                    {
                        MainActivity.saveBuy(SKU_CODE_BUY5,SKU_CODE2_BUY5);
                    }
                }
                Price5=buychar5.getPrice();
            }

            findViewById(R.id.view).invalidate();
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
                                               ArrayList<String> l = new ArrayList<String>();
                                               l.add(SKU_BUY1);
                                               l.add(SKU_BUY2);
                                               l.add(SKU_BUY3);
                                               l.add(SKU_BUY4);
                                               l.add(SKU_BUY5);
                                               l.add(SKU_BUY6);


                                               mHelper.queryInventoryAsync(true, l, mGotInventoryListener);
                                           }
                                       }
                                   });




    }

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
            new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase,
                                              IabResult result) {

                    if (result.isSuccess()) {

                       if (purchase.getSku().equals(SKU_BUY1)) {


                            MainActivity.saveBuy(SKU_CODE_BUY1,SKU_CODE2_BUY1);
                        }
                        else if (purchase.getSku().equals(SKU_BUY2)) {

                            MainActivity.saveBuy(SKU_CODE_BUY2,SKU_CODE2_BUY2);
                        } else if (purchase.getSku().equals(SKU_BUY3)) {

                            MainActivity.saveBuy(SKU_CODE_BUY3,SKU_CODE2_BUY3);
                        } else if (purchase.getSku().equals(SKU_BUY4)) {

                            MainActivity.saveBuy(SKU_CODE_BUY4,SKU_CODE2_BUY4);
                        } else if (purchase.getSku().equals(SKU_BUY5)) {

                            MainActivity.saveBuy(SKU_CODE_BUY5,SKU_CODE2_BUY5);
                        } else if (purchase.getSku().equals(SKU_BUY6)) {

                            MainActivity.saveBuy(SKU_CODE_BUY6,SKU_CODE2_BUY6);
                        }
                        try{
                        findViewById(R.id.view).invalidate();}catch(Exception e){};
                    }


                    else {
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

        mHelper.launchPurchaseFlow(this,SKU_BUY6,SKU_CODE_BUY6,mPurchaseFinishedListener);
    }

    public void buyChar1()
    {

        mHelper.launchPurchaseFlow(this,SKU_BUY1,SKU_CODE_BUY1,mPurchaseFinishedListener);
    }
    public void buyChar2()
    {

        mHelper.launchPurchaseFlow(this,SKU_BUY2,SKU_CODE_BUY2,mPurchaseFinishedListener);
    }
    public void buyChar3()
    {

        mHelper.launchPurchaseFlow(this,SKU_BUY3,SKU_CODE_BUY3,mPurchaseFinishedListener);
    }
    public void buyChar4()
    {

        mHelper.launchPurchaseFlow(this,SKU_BUY4,SKU_CODE_BUY4,mPurchaseFinishedListener);
    }
    public void buyChar5()
    {

        mHelper.launchPurchaseFlow(this,SKU_BUY5,SKU_CODE_BUY5,mPurchaseFinishedListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }



}
