package com.games.bitworxx.engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.games.bitworxx.engine.characters.Bee;
import com.games.bitworxx.engine.characters.Butter;
import com.games.bitworxx.engine.characters.GameConst;
import com.games.bitworxx.engine.characters.Lady;
import com.games.bitworxx.engine.characters.Locust;
import com.games.bitworxx.engine.characters.Spirit;
import com.games.bitworxx.engine.util.ShopHelperFlyer;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by WEIS on 30.07.2015.
 */
public class Options extends View {
    Rect ClickClose;
    Rect ClickMute;

    Rect ClickSlow;
    Rect ClickNormal;
    Rect ClickFast;
    Rect ClickRate;
    Rect ClickAd;

    public Options(Context context, AttributeSet attrs) {
        super(context, attrs);
        invalidate();
    }



        public Rect getBounds()
        {
            return new Rect(0,0,getWidth(),getHeight());
        }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        GameConst.FONT.setColor(GameConst.MACE_COLOR);
        ColorTapet.drawOnRect2(canvas, getBounds(), GameConst.SIZE, false, 150,50,150);
        Paint paintButton = new Paint();
        paintButton.setColor(Color.argb(200, 180, 210, 180));

        paintButton.setStyle(Paint.Style.FILL_AND_STROKE);

        Paint paintStrokeButton=new Paint();
        paintStrokeButton.setStyle(Paint.Style.STROKE);

        paintStrokeButton.setStrokeWidth(4);
        paintStrokeButton.setColor(GameConst.MACE_COLOR);
        Paint back1 = new Paint();
        back1.setStyle(Paint.Style.FILL);
        back1.setColor(Color.argb(100, 50, 50, 50));
        Paint back2 = new Paint();
        back2.setStyle(Paint.Style.FILL);
        back2.setColor(Color.argb(75,50,50,50));

        ArrayList<Rect> main =  RectHandler.getGrid(8, 1, getBounds());
        String title = "options";
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, title, main.get(0), 0);



        ClickClose=main.get(7);
        canvas.drawRect(ClickClose, paintButton);
        canvas.drawRect(ClickClose, paintStrokeButton);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Close the options", ClickClose, 0);

        ClickRate=main.get(6);
        canvas.drawRect(ClickRate, paintButton);
        canvas.drawRect(ClickRate, paintStrokeButton);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Rate the app", ClickRate, 0);

        if(MainActivity.readBuy(51)==0) {
            ClickAd = main.get(5);
            canvas.drawRect(ClickAd, paintButton);
            canvas.drawRect(ClickAd, paintStrokeButton);
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Remove ADS", ClickAd, 0);
        }

        String sped=MainActivity.readSpeed()==10?"Slow":MainActivity.readSpeed()==30?"Fast":"Normal";
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Speed: "+sped, main.get(3), 0);

        ArrayList<Rect> speeds = RectHandler.getGrid(1,9,main.get(4));

        ClickSlow = RectHandler.combineRects(speeds.get(0),speeds.get(2));
        ClickNormal = RectHandler.combineRects(speeds.get(3),speeds.get(5));
        ClickFast = RectHandler.combineRects(speeds.get(6),speeds.get(8));

        canvas.drawRect(ClickSlow, paintButton);
        canvas.drawRect(ClickSlow, paintStrokeButton);

        canvas.drawRect(ClickNormal, paintButton);
        canvas.drawRect(ClickNormal, paintStrokeButton);

        canvas.drawRect(ClickFast, paintButton);
        canvas.drawRect(ClickFast, paintStrokeButton);

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "SLOW", ClickSlow, 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Normal",ClickNormal, 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "FAST",ClickFast, 0);



        ArrayList<Rect> music = RectHandler.getGrid(1,8,main.get(2));

        Bitmap icon = BitmapFactory.decodeResource(getResources(),MainActivity.readMusic()==1?R.drawable.mute_off:R.drawable.mute_on);
        ClickMute=new Rect(music.get(1).left,music.get(1).top,music.get(1).left+icon.getWidth(),music.get(1).top+icon.getHeight());
        canvas.drawBitmap(icon,ClickMute.left,ClickMute.top,null);
        ClickMute=main.get(2);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, MainActivity.readMusic()==0?"MUSIC SET TO ON":"MUSIC SET TO OFF", RectHandler.combineRects(music.get(3),music.get(7)), 0);

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas,"tap on the icon to change a setting",main.get(1), 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            if(ClickClose.contains((int)event.getX(),(int)event.getY()))
            {
                ((Activity)getContext()).finish();

            }

            if(ClickSlow.contains((int)event.getX(),(int)event.getY()))
            {
                MainActivity.saveSpeed(15);
                GameConst.MOVE_MACE=15;
                GameConst.MOVE_BACK=15;

            }

            if(ClickNormal.contains((int)event.getX(),(int)event.getY()))
            {
                MainActivity.saveSpeed(20);
                GameConst.MOVE_MACE=20;
                GameConst.MOVE_BACK=20;

            }

            if(ClickFast.contains((int)event.getX(),(int)event.getY()))
            {
                MainActivity.saveSpeed(30);
                GameConst.MOVE_MACE=30;
                GameConst.MOVE_BACK=30;

            }

            if(ClickRate.contains((int)event.getX(),(int)event.getY()))
            {
                MainActivity.sendTracking("Options", "Rate", "UX", "Rate the app "+ Locale.getDefault().getCountry());

                final String appPackageName = getContext().getPackageName(); // getPackageName() from Context or Activity object
                try {
                    getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }

            }

            if(ClickAd.contains((int)event.getX(),(int)event.getY()))
            {
                 MainActivity.sendTracking("Options", "Buy NoAds", "UX", "Remove ads in "+ Locale.getDefault().getCountry());

                 MainActivity.ShopHelper.buyNoAds();


            }
            if(ClickMute.contains((int)event.getX(),(int)event.getY()))
            {
                MainActivity.saveMusic(MainActivity.readMusic() == 0 ? 1 : -1);
                if(MainActivity.readMusic()==1)
                    MainActivity.MP.setVolume(0f,0f);
                else
                    MainActivity.MP.setVolume(1f,1f);
            }
        }
        invalidate();

        return true;
    }


}
