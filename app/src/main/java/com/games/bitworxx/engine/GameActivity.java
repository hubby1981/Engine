package com.games.bitworxx.engine;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends Activity {
    private Runnable Update;
    public Timer Updater=new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Update=new Runnable() {
            @Override
            public void run() {
                update();
            }
        };
        runOnUiThread(Update);

    Updater.schedule(new TimerTask() {
        @Override
        public void run() {
            Update.run();
        }
    },0,50);
    }
    private void update()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (getBack() != null)
                    if (getBack().UPDATE == true) {
                        findViewById(R.id.view).invalidate();

                    }

                if (getBack().GAME_OVER && !getControl().IS_START)
                    Updater.cancel();
                findViewById(R.id.viewControl).invalidate();

                findViewById(R.id.viewSpider).invalidate();

            }
        });
    }

    @Override
    public void onBackPressed() {

        MainActivity.MP.setVolume(0f,0f);

        ScrollBack b = getBack();
        Updater.cancel();
        b.GAME_OVER=true;
        b.release();
        finish();
        return;
    }
public void startUpdater()
{
    Updater.cancel();
    Updater=new Timer();
    Updater.schedule(new TimerTask() {
        @Override
        public void run() {
            Update.run();
        }
    }, 0, 50);
}
    public void finishEx()
    {
        ScrollBack b = getBack();
        Updater.cancel();
        b.GAME_OVER=true;
        b.release();
        finish();
    }

    public ScrollBack getBack()
    {
        return (ScrollBack)findViewById(R.id.view);
    }


    public Controls getControl()
    {
        return (Controls)findViewById(R.id.viewControl);
    }
    public Spiders getSpider()
    {
        return (Spiders)findViewById(R.id.viewSpider);
    }
}
