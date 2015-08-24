package com.games.bitworxx.engine;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.games.bitworxx.engine.characters.GameConst;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WEIS on 30.07.2015.
 */
public class Splash extends View {


    public Splash(Context context, AttributeSet attrs) {
        super(context, attrs);
        invalidate();
        Exit=new Timer();
        Exit.schedule(new TimerTask() {
            @Override
            public void run() {
                ((Activity)getContext()).finish();
            }
        },3000);
    }
private Timer Exit;
    @Override
    protected void onDraw(Canvas canvas)
    {
        Rect r = new Rect(0,0,getWidth(),getHeight());
        Paint back = new Paint();
        back.setStyle(Paint.Style.FILL);
        back.setColor(Color.DKGRAY);

        ArrayList<Rect> main =  RectHandler.getGrid(16, 1, r);
        ArrayList<Rect> main2 =  RectHandler.getGrid(5, 1, RectHandler.combineRects(main.get(3),main.get(9)));
        back.setShader(new RadialGradient((float) r.centerX(), (float) r.centerY(), (float) getHeight(), Color.argb(200, 80, 110, 180), Color.BLACK, Shader.TileMode.MIRROR));

        back.setAntiAlias(true);

        canvas.drawRect(r, back);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.cat);

        int w=getWidth()/3;
        int h=getWidth()/4;


        Rect rbit = new Rect(r.centerX()-w/2,r.centerY()-h/2,r.centerX()+w/2,r.centerY()+h/2);
        canvas.drawBitmap(icon, new Rect(0, 0, icon.getWidth(), icon.getHeight()), rbit, null);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "a biitworx game", main.get(10), 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Tasty Flyer", main.get(2), 0);
        if(MainActivity.readBuy(51)==0) {
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "    The ads can be disabled in the  ", main.get(12), 0);
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "      options via in app purchase     ", main.get(13), 1);

        }

    }
}
