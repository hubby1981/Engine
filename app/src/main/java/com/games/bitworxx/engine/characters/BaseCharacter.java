package com.games.bitworxx.engine.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;


import com.games.bitworxx.engine.RandomRange;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WEIS on 20.07.2015.
 */
public abstract class BaseCharacter {

    public int BackColor;
    public int FlyColor;
    public String Name;
    public int X=0;
    public int Y=0;
    public int Size=30;
    private Bitmap Icon=null;
    public int FlySizeW=4;
    public int FlySizeH=1;
protected  String StoreProduct;
    private int FlyMode=1;
    public int SinkRate=4;
    public boolean IsDead=false;
    public boolean IsAnimated=true;
    private Timer Animate=null;

    public BaseCharacter(){
        this(0, 0);
    }
    public BaseCharacter(int x,int y)
    {
        X=x;
        Y=y;
        setup();
        if(Animate==null)
        {
            Animate=new Timer();
            Animate.schedule(new TimerTask() {
                @Override
                public void run() {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            animate();
                        }
                    }).start();
                }
            },0,40);
        }

    }

    public String getProduct()
    {
        return StoreProduct;
    }

   public abstract void setup();

    public Rect getBody()
    {
        return new Rect(X,Y,X+Size,Y+Size);
    }


    public abstract BaseCharacter getCopy();

    public boolean isInRect(Rect rc)
    {
        int seed=8;
        if(rc.contains(getBody().left+seed,getBody().top+seed))
            return true;
        else if(rc.contains(getBody().right-seed,getBody().top+seed))
            return true;
        else if(rc.contains(getBody().right-seed,getBody().bottom-seed))
            return true;
        else if(rc.contains(getBody().left+seed,getBody().bottom-seed))
            return true;
        return false;
    }

    public abstract int getMouth();

    public void onDraw(Canvas canvas)
    {
        Paint back = new Paint();
        back.setStyle(Paint.Style.FILL);
        back.setColor(BackColor);


        Rect body = getBody();
        if(!IsDead) {
            int x1 = Size / 6;
            int w1 = Size / 6;

            drawEye(body, canvas, back, x1, w1);

            back.setColor(BackColor);

            onDrawOther(canvas, back);
            if(!IsAnimated)
            {

                canvas.drawRect(getFlyRect1(body), back);

                canvas.drawRect(getFlyRect2(body), back);

            }else{
            back.setColor(FlyColor);
            onDrawFly(canvas, back, false, body);}

        }
        else
        {
            Path p=new Path();

            p.moveTo(body.exactCenterX(),body.exactCenterY());

            for(int x=0;x<20;x++)
            {
                if(x<5)
                {
                    int xx= RandomRange.getRandom(body.left, body.centerX());
                    int yy= RandomRange.getRandom(body.top, body.centerY());
                    p.lineTo(xx,yy);
                }

                if(x>5 && x <10)
                {
                    int xx= RandomRange.getRandom(body.right,body.centerX());
                    int yy= RandomRange.getRandom(body.top, body.centerY());
                    p.lineTo(xx,yy);
                }

                if(x>10 && x <15)
                {
                    int xx= RandomRange.getRandom(body.right,body.centerX());
                    int yy= RandomRange.getRandom(body.bottom, body.centerY());
                    p.lineTo(xx,yy);
                }

                if(x>15 && x <20)
                {
                    int xx= RandomRange.getRandom(body.left,body.centerX());
                    int yy= RandomRange.getRandom(body.bottom, body.centerY());
                    p.lineTo(xx,yy);
                }
            }

            p.lineTo(body.centerX(), body.centerY());
            p.close();

            canvas.drawPath(p,back);
        }
        back=null;
    }


    public abstract boolean isLocked();


    public Bitmap drawBitmap(boolean release)
    {
        if(!release&&Icon!=null)
            return Icon;

        Icon=null;

        Rect body =new Rect(25,25,160,180);

        Icon=Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(Icon);
        Paint back = new Paint();
        back.setStyle(Paint.Style.FILL);
        back.setColor(BackColor);

        int x1 = Size/6;
        int w1=Size/6;

        drawEye(body, canvas, back, x1, w1);

        back.setColor(BackColor);

        onDrawOther(canvas, back);
        if(!IsAnimated)
        {

            canvas.drawRect(getFlyRect3(body), back);

            canvas.drawRect(getFlyRect4(body), back);

        }else{
        onDrawFly(canvas, back, !IsAnimated,body);}

        back=null;
        canvas=null;
        return Icon;
    }

    protected  abstract void drawEye(Rect body, Canvas canvas, Paint back, int x1, int w1);

    public abstract void onDrawOther(Canvas canvas,Paint back);
    public  void onDrawFly(Canvas canvas,Paint back,boolean all,Rect body)
    {
        if(!all) {
            Rect rcFly = FlyMode == 1 ? getFlyRect1(body) : getFlyRect2(body);


            if (rcFly != null) {
                canvas.drawRect(rcFly, back);
            }
        }
                else
        {
            canvas.drawRect(getFlyRect3(body), back);
            canvas.drawRect(getFlyRect4(body), back);

        }

    }

    public abstract Rect getFlyRect1(Rect body);
    public abstract Rect getFlyRect2(Rect body);

    public abstract Rect getFlyRect3(Rect body);
    public abstract Rect getFlyRect4(Rect body);
    public void animate()
    {
        if(IsAnimated)
        {animateFlyMode();
        animatePosition();}
    }

    public void animateFlyMode()
    {
        if(FlyMode==1)
            FlyMode=2;
        else
            FlyMode=1;
    }

    public abstract float getSinkRate();
    public abstract float getPowerRate();

    public abstract void animatePosition();

    public abstract int getCode();

    public void animateSink(int x,int y)
    {

        if(x==0 && y==1)
        {

            Y+=getSinkRate()*SinkRate*getPowerRate()*1.2;
        }
        else if(x==1 && y==0)
        {
            Y-=(getSinkRate()*SinkRate*getPowerRate())/getPowerRate();
            X-=getSinkRate()*SinkRate*getPowerRate()*1.4;
        }
        else
        {
            Y-=getSinkRate()*SinkRate*getPowerRate()/2;

            X+=getSinkRate()*SinkRate*getPowerRate()/2;
        }


    }
}
