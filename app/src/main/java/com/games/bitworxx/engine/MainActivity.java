package com.games.bitworxx.engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.renderscript.RenderScript;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.games.bitworxx.engine.characters.GameConst;


public class MainActivity extends Activity {

    public static Runnable Shop;

    public static Runnable Update;
    public static Runnable Start;

    public static String KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsL4Tq2tCeqRkdk6rhHiY3SakvlWAqgCws4ZIM34Z5UCFB2g2DtL851UU6lODrMe7lVcIJyxrW15fOQTM8YPaHaldo0xCi6qxfpkLvC0sxYo/OkF8OU31g+NNV/+7h9CF7uytvlJkF4dyE/ay0TKSxsnOmM8QBbIb8FftifTJVGR6L8HiQbIZRsKYWDteZ2yGKxIrnUjQ6rZeDOK1CGFf2SP4yygjkFH1SIHETWzEopu6JcgiR4P7DGg5bUvkIwV5K9fnwTDydbCh/tOQXPxPGf8rhKMHz7iMSZ1/9kfwK6SAgehStvC+uhshRhkEoxGV4JYELqi5kSV2luJKiHJQSQIDAQAB";

    public static SharedPreferences Preferences;
    private static GameConst GM;
    public static MediaPlayer MP=new MediaPlayer();
    public static MediaPlayer MP_UP=new MediaPlayer();
    public static MediaPlayer MP_PONG = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GM=new GameConst();

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

    public static int readHigh()
    {
        SharedPreferences pref = getPref();
        int best =  pref.getInt(TXT.KEY_HIGH, -1);
        return best==-1?0:best;
    }


    public static void saveHigh(int best)
    {
        SharedPreferences pref = getPref();
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(TXT.KEY_HIGH, best);
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
