package com.games.bitworxx.engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.renderscript.RenderScript;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.games.bitworxx.engine.characters.GameConst;


public class MainActivity extends ActionBarActivity {


    public static Runnable Update;
    public static SharedPreferences Preferences;
    private static GameConst GM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GM=new GameConst();
        setContentView(R.layout.activity_main);
        Preferences=getSharedPreferences(TXT.KEY_GLOBAL, Context.MODE_PRIVATE);
        getWindowManager().getDefaultDisplay().getMetrics(GM.Metrics);
        GM.FONT.setTypeface(Typeface.createFromAsset(getAssets(), "venus.ttf"));
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
                findViewById(R.id.view).invalidate();findViewById(R.id.viewControl).invalidate();
            }
        });
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


    public void saveHigh(int best)
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
        int best =  pref.getInt(TXT.KEY_BEST+String.valueOf( code), -1);
        return best==-1?0:best;
    }


}
