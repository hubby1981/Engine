package com.games.bitworxx.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by WEIS on 27.07.2015.
 */
public class ScrollBack extends View {

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
            BACK.onDraw(canvas, new Rect(0, 0, getWidth(), getHeight()));

            MACE.onDraw(canvas, new Rect(0, 0, getWidth(), BOTTOM.top), TOP.bottom);
            MACE.move(10);
            Paint Mace = new Paint();
            Mace.setColor(Color.BLACK);
            Mace.setStyle(Paint.Style.FILL);


            canvas.drawRect(TOP,Mace);
            canvas.drawRect(BOTTOM,Mace);

            UPDATE=true;
        }

    }


}
