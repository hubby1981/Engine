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

/**
 * Created by WEIS on 27.07.2015.
 */
public class Controls extends View {

    public Controls(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public RectF ClickLeft;
    public RectF ClickRight;
    public RectF ClickMiddle;
    private int MAX=8;
    private boolean START=true;

    BaseCharacter ThisChar=new Fly();
    public static int FLYX=0;
    public static  int FLYY=0;

    @Override
    protected void onDraw(Canvas canvas) {

        Paint Mace = new Paint();
        Mace.setColor(Color.BLACK);
        Mace.setStyle(Paint.Style.FILL);
        float w = getHeight() / GameConst.SIZE;
        float max=(float)1.6;
        Rect TOP=new Rect(0,0,getWidth(),0+getHeight()/MAX);

        Rect BOTTOM=new Rect(0,(0+getHeight())-getHeight()/MAX,getWidth(),0+getHeight());
        if(START)
        {
            ThisChar.X= TOP.centerX();
            ThisChar.Y=getHeight()/2;
            ThisChar.Size= TOP.height()/4;
            START=false;
        }
        ClickLeft = new RectF(getLeft() + w / max, (getBottom()+w/2) - w * max, getLeft() + w * max, (getBottom()+w/2) - w / max);
        ClickRight = new RectF(getRight() - w * max,(getBottom()+w/2) - w * max, getRight() - w / max, (getBottom()+w/2) - w / max);

        float left1 = (BOTTOM.centerX()-w)-w/8;
        ClickMiddle = new RectF(left1 + w / max, (getBottom()+w/2) - w * max, left1 + w * max, (getBottom()+w/2) - w / max);
        Bitmap iconChar = BitmapFactory.decodeResource(getResources(), R.drawable.bolt);
        Paint circle = new Paint();
        circle.setColor(Color.argb(200, 150, 150, 150));
        circle.setStyle(Paint.Style.FILL);

        Paint power = new Paint();
        power.setColor(Color.argb(225, 200, 170, 0));
        power.setStyle(Paint.Style.FILL_AND_STROKE);

        power.setAntiAlias(true);
        power.setStrokeWidth(TOP.width() / 80);

        canvas.drawArc(ClickMiddle, 0, 360, true, circle);
        if (FLYX == 1)
            canvas.drawArc(ClickLeft, 0, 360, true, power);
        else
            canvas.drawArc(ClickLeft, 0, 360, true, circle);
        if (FLYY == 1)
            canvas.drawArc(ClickRight, 0, 360, true, power);
        else
            canvas.drawArc(ClickRight, 0, 360, true, circle);



        RectF middle2 = new RectF(ClickMiddle.left + ClickMiddle.width() / 6, ClickMiddle.top + ClickMiddle.height() / 6, ClickMiddle.right - ClickMiddle.width() / 6, ClickMiddle.bottom - ClickMiddle.height() / 6);
        canvas.drawBitmap(iconChar, null, middle2, null);

        Path p = TriangleHelper.getTriangleLeft(ClickLeft);
        canvas.drawPath(p, Mace);
        p = TriangleHelper.getTriangleDown(ClickRight);
        canvas.drawPath(p, Mace);

        power=circle=null;



        if(!ScrollBack.GAME_OVER&&!ThisChar.IsDead)
        {
            ThisChar.animateSink(FLYX, FLYY);
            ThisChar.onDraw(canvas);
        }
    }


    @Override
    public boolean onTouchEvent(final MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {

                if (ClickLeft.contains(event.getX(), event.getY())) {

                    FLYX = 1;
                    FLYY = 0;

                }else if (ClickRight.contains(event.getX(), event.getY())) {
                    FLYY = 1;
                    FLYX = 0;

                }


        }

        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            FLYX=0;
            FLYY=0;


        }

        invalidate();
        return true;
    }

    private void checkTouch(MotionEvent event) {

    }
}
