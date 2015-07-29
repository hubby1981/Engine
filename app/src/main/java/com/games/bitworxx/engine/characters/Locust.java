package com.games.bitworxx.engine.characters;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


import com.games.bitworxx.engine.MainActivity;
import com.games.bitworxx.engine.RandomRange;
import com.games.bitworxx.engine.RectHandler;

import java.util.ArrayList;

/**
 * Created by WEIS on 20.07.2015.
 */
public class Locust extends BaseCharacter {
    @Override
    public void setup() {
        BackColor= GameConst.BACK_CHAR2_NORMAL;
        FlyColor= GameConst.BACK_CHAR2_FLY;
        Name = "Lolo Locust";
        StoreProduct="buychar1";
    }
    @Override
    public String getUnlockText() {
        return "to unlock Lolo Locust score 25 with Arty McFly";
    }
    @Override
    public int getMouth() {
        return 5;
    }

    @Override
    public void onDrawOther(Canvas canvas, Paint back) {
        int w = getBody().width()/8;

        Rect rcFeet1 = new Rect(getBody().left+w,getBody().bottom,getBody().left+w+w,getBody().bottom+w*3);
        Rect rcFeet2 = new Rect(getBody().left+w+w+2,getBody().bottom,getBody().left+w+w+w+2,getBody().bottom+w*2);

        canvas.drawRect(rcFeet1,back);
        canvas.drawRect(rcFeet2,back);

    }

    @Override
    public Rect getFlyRect1(Rect body) {


        int w = body.width()/4;
        int l = body.left;
        int t = body.top+w;
        return new Rect(l-w*3,t,l,(int)(t+w*1.4));
    }

    @Override
    public Rect getFlyRect2(Rect body) {

        int w = body.width()/4;
        int l = body.left+w;
        int t = body.top;
        return new Rect(l,t-w*3,(int)(l+w*1.4),t);
    }

    @Override
    public boolean isLocked() {
        if(MainActivity.readBuy(getCode())>0)
            return false;
        if(MainActivity.readBest(1)>25)
            return false;
        return true;
    }
    @Override
    public Rect getFlyRect3(Rect body) {


        int w = body.width()/8;
        int l = body.left;
        int t = body.top+w;
        return new Rect(l-w*6,t,l,(int)(t+w*1.4));
    }

    @Override
    public Rect getFlyRect4(Rect body) {

        int w = body.width()/8;
        int l = body.left+w;
        int t = body.top;
        return new Rect(l,t-w*6,(int)(l+w*1.4),t);
    }

    @Override
    protected  void drawEye(Rect body, Canvas canvas, Paint back, int x1, int w1) {
        ArrayList<Rect> rects1 = RectHandler.getGrid(16, 1, body);





        canvas.drawRect(body, back);

        back.setColor(GameConst.EYE_COLOR);
        ArrayList<Rect> rects=RectHandler.getGrid(1,16,rects1.get(1));
        canvas.drawRect(RectHandler.combineRects(rects.get(12), rects.get(14)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(2));
        canvas.drawRect(RectHandler.combineRects(rects.get(8),rects.get(10)), back);
        rects=RectHandler.getGrid(1,16,rects1.get(14));
        canvas.drawRect(RectHandler.combineRects(rects.get(13),rects.get(15)), back);

    }
    @Override
    public BaseCharacter getCopy() {
        return isLocked()?new Fly(): new Locust();
    }
    @Override
    public float getSinkRate() {
        return (float)2.0;
    }

    @Override
    public float getPowerRate() {
        return (float) 2.8;
    }
    @Override
    public int getCode() {
        return 2;
    }

    @Override
    public void animatePosition() {

        int x=2;
        int y=4;
        X+= RandomRange.getRandom(-x, y);
        Y+=RandomRange.getRandom(-x,y);
    }
}