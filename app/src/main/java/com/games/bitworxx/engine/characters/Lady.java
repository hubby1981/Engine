package com.games.bitworxx.engine.characters;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


import com.games.bitworxx.engine.MainActivity;
import com.games.bitworxx.engine.RandomRange;
import com.games.bitworxx.engine.RectHandler;

import java.util.ArrayList;

/**
 * Created by WEIS on 24.07.2015.
 */
public class Lady extends BaseCharacter {
    @Override
    public void setup() {
        BackColor= GameConst.BACK_CHAR5_NORMAL;
        FlyColor= GameConst.BACK_CHAR5_FLY;
        Name="Lady Bug";
        StoreProduct="buychar3";
    }

    @Override
    public BaseCharacter getCopy() {
        return isLocked()? new Fly():new Lady();
    }

    @Override
    public int getMouth() {
        return 4;
    }

    @Override
    public boolean isLocked() {
        if(MainActivity.readBuy(getCode())>0)
            return false;
        if(MainActivity.readBest(5)>999)
            return false;
        return true;
    }
    @Override
    public String getUnlockText() {
        return "to unlock Lady Bug score 999 with Little Spirit";
    }

    @Override
    public void onDrawOther(Canvas canvas, Paint back) {
        ArrayList<Rect> rects1 = RectHandler.getGrid(16, 1, getBody());
        back.setColor(FlyColor);

        ArrayList<Rect> rects = RectHandler.getGrid(1, 16, rects1.get(9));
        canvas.drawRect(RectHandler.combineRects(rects.get(1), rects.get(2)), back);
        rects = RectHandler.getGrid(1, 16, rects1.get(10));
        canvas.drawRect(RectHandler.combineRects(rects.get(1), rects.get(2)), back);

        rects = RectHandler.getGrid(1, 16, rects1.get(9));
        canvas.drawRect(RectHandler.combineRects(rects.get(4), rects.get(5)), back);
        rects = RectHandler.getGrid(1, 16, rects1.get(10));
        canvas.drawRect(RectHandler.combineRects(rects.get(4), rects.get(5)), back);

        rects = RectHandler.getGrid(1, 16, rects1.get(9));
        canvas.drawRect(RectHandler.combineRects(rects.get(7), rects.get(8)), back);
        rects = RectHandler.getGrid(1, 16, rects1.get(10));
        canvas.drawRect(RectHandler.combineRects(rects.get(7), rects.get(8)), back);

        rects = RectHandler.getGrid(1, 16, rects1.get(12));
        canvas.drawRect(RectHandler.combineRects(rects.get(1), rects.get(2)), back);
        rects = RectHandler.getGrid(1, 16, rects1.get(13));
        canvas.drawRect(RectHandler.combineRects(rects.get(1), rects.get(2)), back);

        rects = RectHandler.getGrid(1, 16, rects1.get(12));
        canvas.drawRect(RectHandler.combineRects(rects.get(4), rects.get(5)), back);
        rects = RectHandler.getGrid(1, 16, rects1.get(13));
        canvas.drawRect(RectHandler.combineRects(rects.get(4), rects.get(5)), back);

        rects = RectHandler.getGrid(1, 16, rects1.get(12));
        canvas.drawRect(RectHandler.combineRects(rects.get(7), rects.get(8)), back);
        rects = RectHandler.getGrid(1, 16, rects1.get(13));
        canvas.drawRect(RectHandler.combineRects(rects.get(7), rects.get(8)), back);
        back.setColor(BackColor);
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


        int w = body.width()/4;
        int l = body.left;
        int t = body.top+w;
        return new Rect(l-w*2,t,l,t+w);
    }

    @Override
    public Rect getFlyRect2(Rect body) {

        int w = body.width()/4;
        int l = body.left+w;
        int t = body.top;
        return new Rect(l,t-w*2,l+w,t);
    }

    @Override
    public Rect getFlyRect3(Rect body) {


        int w = body.width()/3;
        int l = body.left;
        int t = body.top+w;
        return new Rect(l-w,t,l,t+w);
    }

    @Override
    public Rect getFlyRect4(Rect body) {

        int w = body.width()/3;
        int l = body.left+w;
        int t = body.top;
        return new Rect(l,t-w,l+w,t);
    }

    @Override
    public float getSinkRate() {
        return (float)3.2;
    }

    @Override
    public float getPowerRate() {
        return (float)1.6;
    }


    @Override
    public void animatePosition() {

        int x=4;
        int y=12;
        X+= RandomRange.getRandom(-x, y);
        Y+=RandomRange.getRandom(-x,y);
    }

    @Override
    public int getCode() {
        return 6;
    }
}
