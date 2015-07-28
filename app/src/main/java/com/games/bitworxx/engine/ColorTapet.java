package com.games.bitworxx.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by WEIS on 17.07.2015.
 */
public class ColorTapet {

    public static int MAX=(int)(255/1.5);
    public static int LOW=MAX/2;
    public static int RED=50;
    public static int GREEN=150;
    public static int BLUE=150;
    public static int LAST=0;

    public static void setRandom()
    {
        if(RandomRange.getRandom(1,1)==1) {
            int rnd = RandomRange.getRandom(1, 6);
            while(rnd==LAST)
                rnd = RandomRange.getRandom(1, 6);
            LAST=rnd;
            if (rnd == 1) {
                RED = 50;
                GREEN = 150;
                BLUE = 150;
            }
            if (rnd == 2) {
                RED = 50;
                GREEN = 150;
                BLUE = 70;
            }
            if (rnd == 3) {
                RED = 150;
                GREEN = 50;
                BLUE = 150;
            }
            if (rnd == 4) {
                RED = 150;
                GREEN = 50;
                BLUE = 50;
            }
            if (rnd == 5) {
                RED = 50;
                GREEN = 50;
                BLUE = 150;
            }
            if (rnd == 6) {
                RED = 150;
                GREEN = 150;
                BLUE = 150;
            }
        }
    }



    public static int drawOnRect2(Canvas canvas,Rect display,int size,boolean alt,int red,int green,int blue)
    {

        size/=3;
        int maxSize=(display.width()/size)/2;
        int left=0;
        int right=0;

        Paint backMax = new Paint();
        Paint backLow=new Paint();
        backMax.setColor(Color.argb(MAX,red,green,blue));
        backLow.setColor(Color.argb(LOW,red,green,blue));
        backMax.setStyle(Paint.Style.FILL);
        backLow.setStyle(Paint.Style.FILL);

        for(int x=0;x<size*2;x++)
        {
            right=left+maxSize;
            Rect d = new Rect(left,display.top,right,display.bottom);
            if(alt)
            {
                canvas.drawRect(d,backLow);
            }
            else
            {
                canvas.drawRect(d,backMax);
            }
            left+=maxSize;
            alt=!alt;
        }

        return maxSize;
    }
    public static int drawOnRect(Canvas canvas,Rect display,int size,boolean alt)
    {
        setRandom();
        size/=3;
        int maxSize=(display.width()/size)/2;
        int left=0;
        int right=0;

        Paint backMax = new Paint();
        Paint backLow=new Paint();
        backMax.setColor(Color.argb(MAX,RED,GREEN,BLUE));
        backLow.setColor(Color.argb(LOW,RED,GREEN,BLUE));
        backMax.setStyle(Paint.Style.FILL);
        backLow.setStyle(Paint.Style.FILL);

        for(int x=0;x<size*2;x++)
        {
            right=left+maxSize;
            Rect d = new Rect(left,display.top,right,display.bottom);
            if(alt)
            {
                canvas.drawRect(d,backLow);
            }
            else
            {
                canvas.drawRect(d,backMax);
            }
            left+=maxSize;
            alt=!alt;
        }

        return maxSize;
    }
}
