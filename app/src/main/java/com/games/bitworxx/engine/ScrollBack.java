package com.games.bitworxx.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.games.bitworxx.engine.characters.BaseCharacter;
import com.games.bitworxx.engine.characters.Fly;
import com.games.bitworxx.engine.characters.GameConst;

import java.util.ArrayList;

/**
 * Created by WEIS on 27.07.2015.
 */
public class ScrollBack extends View {

    public static  boolean GAME_OVER = false ;
    BackDrawer BACK=new BackDrawer();
    MaceDrawer MACE=new MaceDrawer();

    Rect TOP;
    Rect BOTTOM;



    private int MAX=8;
    private boolean UPDATE=true;
    public ScrollBack(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        if(UPDATE){
            UPDATE=false;
            TOP=new Rect(0,0,getWidth(),0+getHeight()/MAX);
            BOTTOM=new Rect(0,(0+getHeight())-getHeight()/MAX,getWidth(),0+getHeight());

            super.onDraw(canvas);

            BACK.MOVEME=!GAME_OVER;
            MACE.MOVEME=!GAME_OVER;

            BACK.onDraw(canvas, new Rect(0, 0, getWidth(), getHeight()));

            MACE.onDraw(canvas, new Rect(0, 0, getWidth(), BOTTOM.top), TOP.bottom);
            MACE.move(10);
            Paint Mace = new Paint();
            Mace.setColor(Color.BLACK);
            Mace.setStyle(Paint.Style.FILL);


            canvas.drawRect(TOP, Mace);
            canvas.drawRect(BOTTOM, Mace);

            String s=GameConst.GameCount<10?"00"+ String.valueOf(GameConst.GameCount):GameConst.GameCount<100?"0"+ String.valueOf(GameConst.GameCount):String.valueOf(GameConst.GameCount);
            Paint font = new Paint();
            font.setColor( GameConst.FONT.getColor());
            font.setStyle(Paint.Style.FILL);
            font.setTextSize(TOP.height());
            font.setTypeface(GameConst.FONT.getTypeface());
            canvas.drawText(s, TOP.exactCenterX() - ((float) (s.length() / 1.8)) * font.getTextSize(), TOP.bottom - font.getTextSize() / 8, font);



            UPDATE=true;
        }



    }


}
