package com.games.bitworxx.engine.util;

import android.app.Activity;
import android.content.Intent;

import com.games.bitworxx.engine.MainActivity;
import com.games.bitworxx.engine.R;

import java.util.ArrayList;

/**
 * Created by WEIS on 24.08.2015.
 */
public class ShopHelperFlyer {


    private Activity MyActivity = null;
    public ShopHelperFlyer(Activity a)
    {
        MyActivity = a;

        init();
    }

    public void init()
    {
        mHelper = new IabHelper(MyActivity, MainActivity.KEY);
        MainActivity.sendTracking("Shop", "shop", "UX", "open shop");

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
                                               l.add(SKU_BUY7);


                                               mHelper.queryInventoryAsync(true, l, mGotInventoryListener);
                                           }
                                       }
                                   });
    }

    public  String Price1=null;
    public  String Price2=null;
    public  String Price3=null;
    public  String Price4=null;
    public  String Price5=null;
    public  String Price6=null;
    public  String Price7=null;


    public  String SKU_BUY1="buychar1";
    public  String SKU_BUY2="buychar2";
    public  String SKU_BUY3="buychar4";
    public  String SKU_BUY4="buychar5";
    public  String SKU_BUY5="buychar3";
    public  String SKU_BUY6="buypack1";
    public  String SKU_BUY7="noads";

    public  int SKU_CODE_BUY1=61;
    public  int SKU_CODE_BUY2=62;
    public  int SKU_CODE_BUY3=63;
    public  int SKU_CODE_BUY4=64;
    public  int SKU_CODE_BUY5=65;
    public  int SKU_CODE_BUY6=66;
    public  int SKU_CODE_BUY7=51;

    public  int SKU_CODE2_BUY1=2;
    public  int SKU_CODE2_BUY2=3;
    public  int SKU_CODE2_BUY3=4;
    public  int SKU_CODE2_BUY4=5;
    public  int SKU_CODE2_BUY5=6;
    public  int SKU_CODE2_BUY6=97;
    public  int SKU_CODE2_BUY7=51;


    public String getPrice(int code)
    {
        if(code==1)return Price1;
        if(code==2)return Price2;
        if(code==3)return Price3;
        if(code==4)return Price4;
        if(code==5)return Price5;
        if(code==6)return Price6;
        if(code==7)return Price7;
        return "0.99";
    }

    public final String TAG ="com.games.billing";

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
            = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result,
                                          Purchase purchase)
        {
            if (result.isFailure()) {
                // Handle error
                MainActivity.sendTracking("Shop", "buy", "ERROR", result.getMessage());
                return;
            }
            else {
                MainActivity.sendTracking("Shop", "buy", "Consume",result.getMessage());
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


                SkuDetails buychar7 = inventory.getSkuDetails(SKU_BUY7);
                if(buychar7!=null)
                {
                    Purchase  pOld = inventory.getPurchase(SKU_BUY7);
                    if(pOld !=null)
                    {
                        if(pOld.getToken()!=null)
                        {
                            MainActivity.saveBuy(SKU_CODE_BUY7,SKU_CODE2_BUY7);
                        }
                    }
                    Price7=buychar7.getPrice();
                }

                try{
                    MyActivity.findViewById(R.id.view).invalidate();

                }catch(Exception e){


                }

            }
        }
    };


    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
            new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase,
                                              IabResult result) {

                    if (result.isSuccess()) {
                        MainActivity.sendTracking("Shop", "buy", "SUCCESS CONSUME",result.getMessage());
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
                        else if (purchase.getSku().equals(SKU_BUY7)) {

                            MainActivity.saveBuy(SKU_CODE_BUY7,SKU_CODE2_BUY7);
                        }
                        try{
                            MyActivity.findViewById(R.id.view).invalidate();}catch(Exception e){};
                    }


                    else {
                        // handle error
                        MainActivity.sendTracking("Shop", "buy", "ERROR CONSUME",result.getMessage());
                    }
                }
            };

    public void buyPack()
    {
        try{
            MainActivity.sendTracking("Shop", "buy", "UX", "Buy complete pack");

            mHelper.launchPurchaseFlow(MyActivity,SKU_BUY6,SKU_CODE_BUY6,mPurchaseFinishedListener);
        }
        catch(Exception e){
            MainActivity.sendTracking("Shop", "buy", "ERROR CALL6",e.getMessage());
            mHelper.dispose();
            init();
            try
            {
                mHelper.launchPurchaseFlow(MyActivity,SKU_BUY6,SKU_CODE_BUY6,mPurchaseFinishedListener);

            }catch (Exception e2){}
        }


    }

    public void buyChar1()
    {
        try{
            MainActivity.sendTracking("Shop", "buy", "UX", "Buy char1");

            mHelper.launchPurchaseFlow(MyActivity,SKU_BUY1,SKU_CODE_BUY1,mPurchaseFinishedListener);
        }
        catch(Exception e){
            MainActivity.sendTracking("Shop", "buy", "ERROR CALL1",e.getMessage());
            mHelper.dispose();
            init();
            try
            {
                mHelper.launchPurchaseFlow(MyActivity,SKU_BUY1,SKU_CODE_BUY1,mPurchaseFinishedListener);

            }catch (Exception e2){}
        }
    }

    public void buyNoAds()
    {
        try{
            MainActivity.sendTracking("Shop", "buy", "UX", "no ads");

            mHelper.launchPurchaseFlow(MyActivity,SKU_BUY7,SKU_CODE_BUY7,mPurchaseFinishedListener);
        }
        catch(Exception e){
            MainActivity.sendTracking("Shop", "buy", "ERROR CALL7",e.getMessage());
            mHelper.dispose();
            init();
            try
            {
                mHelper.launchPurchaseFlow(MyActivity,SKU_BUY7,SKU_CODE_BUY7,mPurchaseFinishedListener);

            }catch (Exception e2){}
        }
    }
    public void buyChar2()
    {
        try {
            MainActivity.sendTracking("Shop", "buy", "UX", "Buy char2");

            mHelper.launchPurchaseFlow(MyActivity,SKU_BUY2,SKU_CODE_BUY2,mPurchaseFinishedListener);
        }
        catch(Exception e){
            MainActivity.sendTracking("Shop", "buy", "ERROR CALL2",e.getMessage());
            mHelper.dispose();
            init();
            try
            {
                mHelper.launchPurchaseFlow(MyActivity,SKU_BUY2,SKU_CODE_BUY2,mPurchaseFinishedListener);

            }catch (Exception e2){}
        }
    }
    public void buyChar3()
    {
        try{
            MainActivity.sendTracking("Shop", "buy", "UX", "Buy char3");

            mHelper.launchPurchaseFlow(MyActivity,SKU_BUY3,SKU_CODE_BUY3,mPurchaseFinishedListener);
        }
        catch(Exception e){
            MainActivity.sendTracking("Shop", "buy", "ERROR CALL3",e.getMessage());
            mHelper.dispose();
            init();
            try
            {
                mHelper.launchPurchaseFlow(MyActivity,SKU_BUY3,SKU_CODE_BUY3,mPurchaseFinishedListener);

            }catch (Exception e2){}
        }
    }
    public void buyChar4()
    {
        try{
            MainActivity.sendTracking("Shop", "buy", "UX", "Buy char4");

            mHelper.launchPurchaseFlow(MyActivity,SKU_BUY4,SKU_CODE_BUY4,mPurchaseFinishedListener);
        }
        catch(Exception e){
            MainActivity.sendTracking("Shop", "buy", "ERROR CALL4",e.getMessage());
            mHelper.dispose();
            init();
            try
            {
                mHelper.launchPurchaseFlow(MyActivity,SKU_BUY4,SKU_CODE_BUY4,mPurchaseFinishedListener);

            }catch (Exception e2){}
        }
    }
    public void buyChar5()
    {
        try {
            MainActivity.sendTracking("Shop", "buy", "UX", "Buy char5");

            mHelper.launchPurchaseFlow(MyActivity, SKU_BUY5, SKU_CODE_BUY5, mPurchaseFinishedListener);
        }
        catch(Exception e){
            MainActivity.sendTracking("Shop", "buy", "ERROR CALL5",e.getMessage());
            mHelper.dispose();
            init();
            try
            {
                mHelper.launchPurchaseFlow(MyActivity,SKU_BUY5,SKU_CODE_BUY5,mPurchaseFinishedListener);

            }catch (Exception e2){}
        }
    }
}
