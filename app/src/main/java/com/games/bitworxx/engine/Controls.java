package com.games.bitworxx.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.games.bitworxx.engine.characters.BaseCharacter;
import com.games.bitworxx.engine.characters.Fly;
import com.games.bitworxx.engine.characters.GameConst;
import com.games.bitworxx.engine.characters.Locust;
import com.games.bitworxx.engine.characters.Spider;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WEIS on 27.07.2015.
 */
public class Controls extends View {

    public Controls(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
public boolean CheckCollision=false;
    public boolean IS_START=false;
public Timer StartTimer=new Timer();
    public RectF ClickLeft;
    public RectF ClickRight;
    public RectF ClickMiddle;
    private int MAX=8;
    private boolean START=true;
    private int INDEX=0;
    public BaseCharacter ThisChar=new Fly();
    public static int FLYX=0;
    public static  int FLYY=0;
    private Rect Retry;
    private Rect Quit;
    public long TimeStart=0l;
    public long TimeEnd=0l;
    public String DEAD_BY="";
    public boolean UPDATE=true;
    Rect TOP =null;
            Rect BOTTOM=null;

    @Override
    protected void onDraw(Canvas canvas) {
if(UPDATE) {
    Paint Mace = new Paint();
    Mace.setColor(Color.BLACK);
    Mace.setStyle(Paint.Style.FILL);
    float w = getHeight() / GameConst.SIZE;
    float max = (float) 1.6;
    TOP = new Rect(0, 0, getWidth(), 0 + getHeight() / MAX);

    BOTTOM = new Rect(0, (0 + getHeight()) - getHeight() / MAX, getWidth(), 0 + getHeight());

    if (START) {
        initGameTimer();

    }
    ClickLeft = new RectF(getLeft() + w / max, (getBottom() + w / 2) - w * max, getLeft() + w * max, (getBottom() + w / 2) - w / max);
    ClickRight = new RectF(getRight() - w * max, (getBottom() + w / 2) - w * max, getRight() - w / max, (getBottom() + w / 2) - w / max);

    float left1 = (BOTTOM.centerX() - w) - w / 8;
    ClickMiddle = new RectF(left1 + w / max, (getBottom() + w / 2) - w * max, left1 + w * max, (getBottom() + w / 2) - w / max);




    if(!getBack().GAME_OVER) {

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
        power = circle = null;

    }


    if (!getBack().GAME_OVER && !ThisChar.IsDead) {
        ThisChar.animateSink(FLYX, FLYY);
        ThisChar.onDraw(canvas);
        checkCollision();
    }
    else
    {
        if(!IS_START)
            ThisChar.onDraw(canvas);

    }


    if (IS_START) {
        Mace.setColor(Color.argb(200, 0, 0, 0));
        Rect bounds = new Rect(0, 0, getWidth(), getHeight());
        //canvas.drawRect(bounds,Mace);
        String st = INDEX == 3 ? "LOAD" : INDEX == 2 ? "READY" : "GO";
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, st, bounds, 0);

    }

    if (getBack().GAME_OVER && !IS_START&&!getBack().PLAY)
    {
        ThisChar.IsDead=true;
        ThisChar.onDraw(canvas);
        drawOver(canvas);

    }
}
    }

    public void checkCollision()
    {
        boolean isDead=false;

        if(CheckCollision&&ThisChar!=null&&!ThisChar.IsDead) {

            for(Point p : ThisChar.getHitPoints())
            {
                if(!isDead){
                    isDead=getBack().isInRect(p.x,p.y);
                }
                if(!isDead)
                    isDead=TOP.contains(p.x,p.y);
                if(!isDead)
                    isDead=BOTTOM.contains(p.x,p.y);
            }

            if(isDead)
            {
                DEAD_BY="bam!!! u feel the block?";
                MainActivity.MP_PONG.start();
            }
            else
                DEAD_BY="tasty spider candy - yam yam";


            if (isDead) {
                ThisChar.IsDead=true;
                getBack().PLAY = false;
                getBack().GAME_OVER = true;
                if(MainActivity.readBest(GameConst.MyChar.getCode())<GameConst.GameCount)
                    MainActivity.saveBest(GameConst.GameCount);
                if(MainActivity.readHigh()<GameConst.GameCount)
                    MainActivity.saveHigh(GameConst.GameCount);
                TimeEnd = SystemClock.elapsedRealtime();
            }
        }
    }

    private void initGameTimer() {

        ThisChar=GameConst.MyChar.getCopy();
        ThisChar.X= TOP.centerX();
        ThisChar.Y=getHeight()/2;
        ThisChar.Size= TOP.height()/3;
        GameConst.MyChar.Size=ThisChar.Size;

        START=false;
        getBack().GAME_OVER=true;
        INDEX=4;
        IS_START=true;
        ((GameActivity)getContext()).startUpdater();
        StartTimer=new Timer();
        StartTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                INDEX--;
                MainActivity.MP_UP.start();
                if(INDEX==0)
                {
                    IS_START=false;
                    startGame();
                }
            }
        },0,1000);
    }

    public void startGame()
    {

        TimeStart=SystemClock.elapsedRealtime();
        if(StartTimer!=null)
            StartTimer.cancel();
        getBack().GAME_OVER=false;
        getBack().PLAY=true;

        GameConst.GameCount=0;

    }


    public ScrollBack getBack()
    {
        return ((GameActivity) getContext()).getBack();
    }
    private String getTime()
    {
        long time = TimeEnd-TimeStart;
        time/=1000;
        String result="00:00";

        if(time>60)
        {
            long mm= time%60;
            long m = (time-mm)/60;
            if(m<10&&mm<10)
            {
                result="0"+String.valueOf(m)+":0"+String.valueOf(mm);
            }
            else if(m<10&&mm>10)
            {

                result="0"+String.valueOf(m)+":"+String.valueOf(mm);

            }
            else
            {
                if(mm<10)
                {
                    result=String.valueOf(m)+":0"+String.valueOf(mm);
                }
                else
                {
                    result=String.valueOf(m)+":"+String.valueOf(mm);

                }
            }
        }
        else
        {
            if(time<10)
            {
                result="00:0"+String.valueOf(time);
            }
            else
            {
                result="00:"+String.valueOf(time);
            }
        }
        return result;
    }
    public void drawOver(Canvas canvas)
    {
        Paint back1 = new Paint();

        back1.setColor(Color.argb(75, 50, 50, 50));
        back1.setStyle(Paint.Style.FILL);
        //canvas.drawRect(getBounds(), back1);
        int alpha=220;
        Paint back3 = new Paint();
        int hm=15;

        back3.setColor(Color.argb(alpha+hm,50, 150,150));
        back3.setStyle(Paint.Style.FILL);

        Paint back2 = new Paint();

        back2.setColor(Color.argb(alpha-hm,50,50,150));
        back2.setStyle(Paint.Style.FILL);

        Paint back5 = new Paint();

        back5.setColor(Color.argb(alpha+hm,50,150,150));
        back5.setStyle(Paint.Style.FILL);

        Paint back4 = new Paint();

        back4.setColor(Color.argb(alpha-hm,50,50,150));
        back4.setStyle(Paint.Style.FILL);


        Paint back6 = new Paint();

        back6.setColor(Color.argb(255,50,150,150));
        back6.setStyle(Paint.Style.FILL);


        Paint back21 = new Paint();

        back21.setColor(Color.argb(255, 150, 50, 50));
        back21.setStyle(Paint.Style.FILL);

        Paint back31 = new Paint();

        back31.setColor(Color.argb(255, 50, 150, 50));
        back31.setStyle(Paint.Style.FILL);

        ArrayList<Rect> main =  RectHandler.getGrid(8, 1, new Rect(0,0,getWidth(),getHeight()));

        String title="Game Over";
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, title, main.get(0), 0);


        canvas.drawRect(main.get(2), back2);
        canvas.drawRect(main.get(3), back3);
        canvas.drawRect(main.get(4), back4);


        canvas.drawRect(main.get(5),back5);

        canvas.drawRect(main.get(1),back6);

        ArrayList<Rect> rects2 = RectHandler.getGrid(1,8,main.get(2));
        ArrayList<Rect> rects3 = RectHandler.getGrid(1,8,main.get(3));
        ArrayList<Rect> rects4 = RectHandler.getGrid(1, 8, main.get(6));
        ArrayList<Rect> rects5 = RectHandler.getGrid(1, 8, main.get(4));
        ArrayList<Rect> rects6 = RectHandler.getGrid(1, 8, main.get(5));


        Rect score = RectHandler.combineRects(rects2.get(0), rects2.get(3));
        Rect best = RectHandler.combineRects(rects3.get(0), rects3.get(3));
        Rect score2 = RectHandler.combineRects(rects2.get(6), rects2.get(6));
        Rect best2 = RectHandler.combineRects(rects3.get(6),rects3.get(6));
        Retry = RectHandler.combineRects(rects4.get(0),rects4.get(3));
        Quit = RectHandler.combineRects(rects4.get(4),rects4.get(7));
        Rect high = RectHandler.combineRects(rects5.get(0), rects5.get(3));
        Rect high2 = RectHandler.combineRects(rects5.get(6), rects5.get(6));
        Rect time = RectHandler.combineRects(rects6.get(0), rects6.get(3));
        Rect time2 = RectHandler.combineRects(rects6.get(5), rects6.get(7));

        String scoreValue = String.valueOf(GameConst.GameCount)+" ";
        String bestValue = String.valueOf(MainActivity.readBest())+" ";
        String highValue = String.valueOf(MainActivity.readHigh())+" ";

        String timeValue =getTime()+" ";


        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Game:", score, 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Best:", best, 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "high:", high, 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Time:", time, 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, DEAD_BY, main.get(1), 0);
        FontRectPainter.drawtextOnCanvas(GameConst.FONT,canvas,scoreValue,score2,0);
        FontRectPainter.drawtextOnCanvas(GameConst.FONT, canvas, bestValue, best2, 0);
        FontRectPainter.drawtextOnCanvas(GameConst.FONT, canvas, highValue, high2, 0);
        FontRectPainter.drawtextOnCanvas(GameConst.FONT, canvas, timeValue, time2, 0);

        canvas.drawRect(Retry, back31);
        canvas.drawRect(Quit,back21);

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Retry", Retry, 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Close", Quit, 0);

    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
if(!getBack().GAME_OVER){
                if (ClickLeft.contains(event.getX(), event.getY())) {

                    FLYX = 1;
                    FLYY = 0;     MainActivity.MP_UP.start();

                }else if (ClickRight.contains(event.getX(), event.getY())) {
                    FLYY = 1;
                    FLYX = 0;MainActivity.MP_UP.start();

                }
                else if (ClickMiddle.contains(event.getX(), event.getY())) {


                }}else
{
    if(!IS_START)
    {
        if (Retry.contains((int)event.getX(), (int)event.getY())) {

            ((GameActivity) getContext()).finishEx();
            MainActivity.Start.run();

        }
        else  if (Quit.contains((int)event.getX(), (int)event.getY())) {

            ((GameActivity) getContext()).finishEx();

        }
    }
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
