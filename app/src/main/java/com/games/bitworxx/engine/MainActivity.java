package com.games.bitworxx.engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.renderscript.RenderScript;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.games.bitworxx.engine.characters.GameConst;
import com.games.bitworxx.engine.util.ShopHelperFlyer;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

import java.util.UUID;


public class MainActivity extends Activity {

    public static Runnable Shop;

    public static Runnable Update;
    public static Runnable Start;
    public static Runnable Tutorial;
    public static Runnable Options;
    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    public static String KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsL4Tq2tCeqRkdk6rhHiY3SakvlWAqgCws4ZIM34Z5UCFB2g2DtL851UU6lODrMe7lVcIJyxrW15fOQTM8YPaHaldo0xCi6qxfpkLvC0sxYo/OkF8OU31g+NNV/+7h9CF7uytvlJkF4dyE/ay0TKSxsnOmM8QBbIb8FftifTJVGR6L8HiQbIZRsKYWDteZ2yGKxIrnUjQ6rZeDOK1CGFf2SP4yygjkFH1SIHETWzEopu6JcgiR4P7DGg5bUvkIwV5K9fnwTDydbCh/tOQXPxPGf8rhKMHz7iMSZ1/9kfwK6SAgehStvC+uhshRhkEoxGV4JYELqi5kSV2luJKiHJQSQIDAQAB";
    public static String KEY2="UA-65112560-2";
    public static SharedPreferences Preferences;
    private static GameConst GM;
    public static MediaPlayer MP=new MediaPlayer();
    public static MediaPlayer MP_UP=new MediaPlayer();
    public static MediaPlayer MP_PONG = new MediaPlayer();

    public static ShopHelperFlyer ShopHelper;
    public static GoogleApiClient ApiClient;

    public static Shader MetalShader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GM=new GameConst();

        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1080);

        tracker = analytics.newTracker(KEY2);
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);

        sendTracking("Main", "info", "UX", "start app");

        new Thread(new Runnable() {
            @Override
            public void run() {
                doMusic();
            }
        }).start();
        Preferences=getSharedPreferences(TXT.KEY_GLOBAL, Context.MODE_PRIVATE);
        getWindowManager().getDefaultDisplay().getMetrics(GM.Metrics);
        GM.FONT.setTypeface(Typeface.createFromAsset(getAssets(), "venus.ttf"));
        setContentView(R.layout.activity_main);

        Update=new Runnable() {
            @Override
            public void run() {
                update();
            }
        };
        runOnUiThread(Update);
        Start=new Runnable() {
            @Override
            public void run() {
                startGame();
            }
        };
        Shop=new Runnable() {
            @Override
            public void run() {
                startShop();
            }
        };
        Tutorial=new Runnable() {
            @Override
            public void run() {
                startTutorial();
            }
        };
        Options=new Runnable() {
            @Override
            public void run() {
                startOptions();
            }
        };



        MetalShader= new BitmapShader(BitmapFactory.decodeResource(getResources(),R.drawable.metal), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        ShopHelper = new ShopHelperFlyer(this);
        saveId(readId());
/*
        ApiClient = new GoogleApiClient.Builder(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
        if(ApiClient!=null)
            ApiClient.connect();*/
        startSplash();

        GameConst.MOVE_MACE=readSpeed();
        GameConst.MOVE_BACK=readSpeed();

        if(MP!=null)
            if(MainActivity.readMusic()==1)
                MainActivity.MP.setVolume(0f,0f);
            else
                MainActivity.MP.setVolume(1f,1f);


    }


    public static void sendTracking(String screen, String label, String category, String action)
    {
        tracker.setScreenName(screen);
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());
    }


    public void startSplash()
    {
        Intent t = new Intent(this,SplashActivity.class);
        startActivity(t);
    }
    public void startOptions()
    {
        Intent t = new Intent(this,OptionsActivity.class);
        startActivity(t);
    }
    public void startTutorial()
    {
        Intent t = new Intent(this,TutorialActivity.class);
        startActivity(t);
    }
    @Override
    public void onBackPressed() {

        MP = null;
        MP_PONG=null;
        MP_UP=null;

        System.exit(0);
        return;
    }


    public  void startGame()
    {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }
    public  void startShop()
    {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
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

    public void doMusic()
    {
        AssetFileDescriptor descriptor = null;
        try {
            descriptor = getAssets().openFd("intro.MP3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MP.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            descriptor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            MP.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(MP!=null) {
            MP.setVolume(0.1f,0.1f);
            MP.setLooping(true);
            MP.start();
        }
        descriptor = null;
        try {
            descriptor = getAssets().openFd("up.MP3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            MP_UP.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            descriptor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            MP_UP.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(MP_UP!=null)
            MP_UP.setVolume(0.3f,0.3f);


        descriptor = null;
        try {
            descriptor = getAssets().openFd("pong.MP3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MP_PONG.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            descriptor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            MP_PONG.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(MP_PONG!=null)
            MP_PONG.setVolume(0.3f,0.3f);


    }

    protected static SharedPreferences getPref()
    {
        return Preferences;
    }
    public static int readBest()
    {
        SharedPreferences pref = getPref();
        int best =  pref.getInt(TXT.KEY_BEST+String.valueOf( GameConst.MyChar.getCode()), -1);
        return best==-1?0:best;
    }


    public static void saveBest(int best)
    {
        SharedPreferences pref = getPref();
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(TXT.KEY_BEST+String.valueOf( GameConst.MyChar.getCode()), best);
        edit.commit();
    }

    public static String readId()
    {
        SharedPreferences pref = getPref();
        String id =  pref.getString(TXT.KEY_ID, "-1");
        return id=="-1"? UUID.randomUUID().toString():id;
    }


    public static void saveId(String id)
    {
        SharedPreferences pref = getPref();
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(TXT.KEY_ID, id);
        edit.commit();
    }

    public static int readAd()
    {
        SharedPreferences pref = getPref();
        int best =  pref.getInt(TXT.KEY_AD, -1);
        return best==-1?0:best;
    }


    public static void saveAd()
    {
        SharedPreferences pref = getPref();
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(TXT.KEY_AD, 1);
        edit.commit();
    }

    public static int readHigh()
    {
        SharedPreferences pref = getPref();
        int best =  pref.getInt(TXT.KEY_HIGH, -1);
        return best==-1?0:best;
    }

    public static void saveSpeed(int speed)
    {
        SharedPreferences pref = getPref();
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(TXT.KEY_SPEED, speed);
        edit.commit();
    }

    public static int readSpeed()
    {
        SharedPreferences pref = getPref();
        int best =  pref.getInt(TXT.KEY_SPEED, -1);
        return best==-1?20:best;
    }


    public static void saveHigh(int best)
    {
        SharedPreferences pref = getPref();
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(TXT.KEY_HIGH, best);
        edit.commit();
    }

    public static int readMusic()
    {
        SharedPreferences pref = getPref();
        int best =  pref.getInt(TXT.KEY_MUSIC, -1);
        return best==-1?0:best;
    }


    public static void saveMusic(int music)
    {
        SharedPreferences pref = getPref();
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(TXT.KEY_MUSIC,music);
        edit.commit();
    }

    public static int loadChar()
    {
        SharedPreferences pref = getPref();
        int best =  pref.getInt(TXT.KEY_CHAR, -1);
        return best==-1?1:best;
    }


    public static void saveChar(int best)
    {
        SharedPreferences pref = getPref();
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(TXT.KEY_CHAR, best);
        edit.commit();
    }


    public static int readBest(int code)
    {
        SharedPreferences pref = getPref();
        int best =  pref.getInt(TXT.KEY_BEST + String.valueOf(code), -1);
        return best==-1?0:best;
    }


    public static int readBuy(int code)
    {
        SharedPreferences pref = getPref();
        int buy =  pref.getInt(TXT.KEY_BUY + String.valueOf(code), -1);
        return buy==-1?0:buy;
    }
    public static void saveBuy(int buy,int code)
    {
        SharedPreferences pref = getPref();
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(TXT.KEY_BUY+String.valueOf(code), buy);
        edit.commit();
    }




}
