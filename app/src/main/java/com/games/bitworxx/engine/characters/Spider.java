package com.games.bitworxx.engine.characters;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


import com.games.bitworxx.engine.MainActivity;
import com.games.bitworxx.engine.RectHandler;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WEIS on 22.07.2015.
 */
public class Spider extends BaseCharacter {

    private int UP=4;
    private int DOWN =0;
    private boolean ALT=false;
    private Timer Animate;
    public int OWN_DOWN=0;
    private int LAST=0;
    private boolean Main = false;

    @Override
    public String getUnlockText() {
        return "is unlocked";
    }
    @Override
    public void setup() {
        BackColor= GameConst.BACK_SPIDER_NORMAL;
        FlyColor= GameConst.BACK_SPIDER_FLY;
        Name="Spider";

        UP=OWN_DOWN!=0?OWN_DOWN: Main?GameConst.UP_SPIDER:GameConst.UP_SPIDER2;
        Animate=new Timer();
        Animate.schedule(new TimerTask() {
            @Override
            public void run() {

                if(LAST==5) {
                    ALT = !ALT;
                    if(UP>0)
                    {
                        Y-=Size/8;
                        UP--;

                        if(UP==0)
                            DOWN=OWN_DOWN!=0?OWN_DOWN: Main?GameConst.UP_SPIDER:GameConst.UP_SPIDER2;
                    }


                    if(DOWN>0)
                    {
                        Y+=Size/8;
                        DOWN--;
                        if(DOWN==0)
                            UP=OWN_DOWN!=0?OWN_DOWN: Main?GameConst.UP_SPIDER:GameConst.UP_SPIDER2;
                    }
                LAST=1;
                }else
                {
                    LAST++;
                }


                if(Main)
                {
                    MainActivity.Update.run();
                }


            }
        },0,GameConst.UP_SPIDER_PERIOD);
    }
    @Override
    public int getCode() {
        return 98;
    }
    @Override
    public BaseCharacter getCopy() {
        return null;
    }
    public Rect Net=null;
    @Override
    public int getMouth() {
        return 0;
    }

    public Spider(int x,int y,Rect net,boolean main)
    {
        super(x,y);
        Net = net;
        Main = main;
    }
    @Override
    public boolean isLocked() {

       return false;
    }
    @Override
    public void onDrawOther(Canvas canvas, Paint back) {

        Rect body = getBody();
        int h = body.height()/8;
        int w = body.width()/2;

        int alt1 =0;

        int alt2=0;

        if(ALT)
        {
            alt1= w/3;
        }
        else
        {
            alt2= w/3;
        }



        Rect left1 = new Rect((body.left-w)+alt2,body.centerY()-h*3,body.left,(body.centerY()-h*3)+h);
        Rect left2 = new Rect((body.left-w)+alt1,left1.bottom+h/2,left1.right,left1.bottom+h/2+h);

        Rect left3 = new Rect((body.left-w)+alt2,left2.bottom+h/2,left1.right,left2.bottom+h/2+h);
        Rect left4 = new Rect((body.left-w)+alt1,left3.bottom+h/2,left1.right,left3.bottom+h/2+h);


        Rect up1 = new Rect(left1.left,left1.top-h,left1.left+h,left1.top);
        Rect up2 = new Rect(left4.left,left4.bottom,left4.left+h,left4.bottom+h);


        canvas.drawRect(left1,back);
        canvas.drawRect(left2,back);
        canvas.drawRect(left3,back);
        canvas.drawRect(left4,back);
        canvas.drawRect(up1,back);
        canvas.drawRect(up2,back);



        left1 = new Rect(body.right,body.centerY()-h*3,(body.right+w)-alt1,(body.centerY()-h*3)+h);
        left2 = new Rect(left1.left,left1.bottom+h/2,(body.right+w)-alt2,left1.bottom+h/2+h);


        left3 = new Rect(left1.left,left2.bottom+h/2,(body.right+w)-alt1,left2.bottom+h/2+h);
        left4 = new Rect(left1.left,left3.bottom+h/2,(body.right+w)-alt2,left3.bottom+h/2+h);
        up1 = new Rect(left1.right-h,left1.top-h,left1.right,left1.top);
        up2 = new Rect(left4.right-h,left4.bottom,left4.right,left4.bottom+h);
        canvas.drawRect(left1,back);
        canvas.drawRect(left2,back);
        canvas.drawRect(left3,back);
        canvas.drawRect(left4,back);
        canvas.drawRect(up1,back);
        canvas.drawRect(up2,back);

        if(Net!=null)
        {

            Paint line = new Paint();
            line.setColor(back.getColor());
            line.setStyle(Paint.Style.STROKE);

            line.setStrokeWidth(2);
         canvas.drawLine(body.exactCenterX(),body.exactCenterY(),Net.exactCenterX(),Net.exactCenterY(),line);

            if(Main) {
                int hm = (Net.height() / 2) / 3;
                int yl = (int) Net.exactCenterY() + hm + hm / 2;

                canvas.drawLine(Net.exactCenterX() - hm, yl, Net.exactCenterX(), yl + hm, line);
                canvas.drawLine(Net.exactCenterX() + hm, yl, Net.exactCenterX(), yl + hm, line);
                canvas.drawLine(Net.exactCenterX() - hm * 2, yl, Net.exactCenterX(), yl + hm * 2, line);
                canvas.drawLine(Net.exactCenterX() + hm * 2, yl, Net.exactCenterX(), yl + hm * 2, line);
                canvas.drawLine(Net.exactCenterX() - hm * 3, yl, Net.exactCenterX(), yl + hm * 3, line);
                canvas.drawLine(Net.exactCenterX() + hm * 3, yl, Net.exactCenterX(), yl + hm * 3, line);
            }
        }
    }

    @Override
    protected  void drawEye(Rect body, Canvas canvas, Paint back, int x1, int w1) {
        ArrayList<Rect> rects1 = RectHandler.getGrid(16, 1, body);





        canvas.drawRect(body, back);

        back.setColor(GameConst.EYE_COLOR);
        ArrayList<Rect> rects=RectHandler.getGrid(1,16,rects1.get(13));
        canvas.drawRect(RectHandler.combineRects(rects.get(3), rects.get(4)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(14));
        canvas.drawRect(RectHandler.combineRects(rects.get(3), rects.get(4)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(13));
        canvas.drawRect(RectHandler.combineRects(rects.get(6), rects.get(7)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(14));
        canvas.drawRect(RectHandler.combineRects(rects.get(6), rects.get(7)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(13));
        canvas.drawRect(RectHandler.combineRects(rects.get(10), rects.get(11)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(14));
        canvas.drawRect(RectHandler.combineRects(rects.get(10), rects.get(11)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(13));
        canvas.drawRect(RectHandler.combineRects(rects.get(13), rects.get(14)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(14));
        canvas.drawRect(RectHandler.combineRects(rects.get(13), rects.get(14)), back);


    }

    @Override
    public Rect getFlyRect1(Rect body) {
        return null;
    }

    @Override
    public Rect getFlyRect2(Rect body) {
        return null;
    }

    @Override
    public Rect getFlyRect3(Rect body) {
        return null;
    }

    @Override
    public Rect getFlyRect4(Rect body) {
        return null;
    }

    @Override
    public float getSinkRate() {
        return 0;
    }

    @Override
    public float getPowerRate() {
        return 0;
    }

    @Override
    public void animatePosition() {

    }
}
