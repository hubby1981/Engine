package com.games.bitworxx.engine.characters;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


import com.games.bitworxx.engine.RandomRange;
import com.games.bitworxx.engine.RectHandler;

import java.util.ArrayList;

/**
 * Created by WEIS on 20.07.2015.
 */
public class Fly extends BaseCharacter {
    @Override
    public void setup() {
        BackColor= GameConst.BACK_CHAR0_NORMAL;
        FlyColor= GameConst.BACK_CHAR0_FLY;
        Name="Arty McFly";
    }
    @Override
    public String getUnlockText() {
        return "is unlocked";
    }
    @Override
    public BaseCharacter getCopy() {
        return new Fly();
    }

    @Override
    public int getMouth() {
        return 4;
    }

    @Override
    public void onDrawOther(Canvas canvas, Paint back) {

    }

    @Override
    protected  void drawEye(Rect body, Canvas canvas, Paint back, int x1, int w1) {
        ArrayList<Rect> rects1 = RectHandler.getGrid(16, 1, body);





        canvas.drawRect(body, back);

        back.setColor(GameConst.EYE_COLOR);
        ArrayList<Rect> rects=RectHandler.getGrid(1,16,rects1.get(3));
        canvas.drawRect(RectHandler.combineRects(rects.get(13), rects.get(14)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(5));
        canvas.drawRect(RectHandler.combineRects(rects.get(10), rects.get(11)), back);
       rects=RectHandler.getGrid(1,16,rects1.get(4));
        canvas.drawRect(RectHandler.combineRects(rects.get(13), rects.get(14)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(6));
        canvas.drawRect(RectHandler.combineRects(rects.get(10),rects.get(11)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(10));
        canvas.drawRect(RectHandler.combineRects(rects.get(14),rects.get(15)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(11));
        canvas.drawRect(RectHandler.combineRects(rects.get(13),rects.get(14)), back);

    }

    @Override
    public Rect getFlyRect1(Rect body) {


        int w = body.width()/6;
        int l = body.left;
        int t = body.top+w;
        return new Rect(l-w,t,l,t+w);
    }

    @Override
    public Rect getFlyRect2(Rect body) {

        int w = body.width()/6;
        int l = body.left+w;
        int t = body.top;
        return new Rect(l,t-w,l+w,t);
    }

    @Override
    public Rect getFlyRect3(Rect body) {


        int w = body.width()/6;
        int l = body.left;
        int t = body.top+w;
        return new Rect(l-w,t,l,t+w);
    }

    @Override
    public Rect getFlyRect4(Rect body) {

        int w = body.width()/6;
        int l = body.left+w;
        int t = body.top;
        return new Rect(l,t-w,l+w,t);
    }
    @Override
    public boolean isLocked() {

       return false;
    }
    @Override
    public float getSinkRate() {
        return (float)2.8;
    }

    @Override
    public float getPowerRate() {
        return (float)2.2;
    }


    @Override
    public void animatePosition() {

        int x=8;
        int y=8;
        X+= RandomRange.getRandom(-x, y);
        Y+=RandomRange.getRandom(-x,y);
    }

    @Override
    public int getCode() {
        return 1;
    }
}
