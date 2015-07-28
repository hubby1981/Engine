package com.games.bitworxx.engine.characters;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import com.games.bitworxx.engine.RandomRange;

/**
 * Created by WEIS on 20.07.2015.
 */
public class GameConst {

    public static int SIZE=10;

    public static int MACE_COLOR= Color.DKGRAY;
    public static int UP_SPIDER=20;
    public static int UP_SPIDER_PERIOD=20;
    public static int UP_SPIDER2=10;
    public static boolean HARDCORE=false;
    public static int EYE_COLOR= Color.WHITE;
    public static long PEROID=15;
    public static int FRAME_RATE=1;
    public static int MOVE_BACK=20;
    public static int MOVE_MACE=20;

    public static float PEROID_FACTOR=(float)0.5;
    public static int BACK_CHAR0_NORMAL = Color.argb(255,100,100,100);
    public static int BACK_CHAR0_FLY = Color.argb(128,100,100,100);

    public static int BACK_CHAR1_NORMAL = Color.argb(255,180,180,50);
    public static int BACK_CHAR1_FLY = Color.argb(128,10,10,10);

    public static int BACK_CHAR3_NORMAL = Color.argb(255,40,60,100);
    public static int BACK_CHAR3_FLY = Color.argb(128,40,60,100);

    public static int BACK_CHAR4_NORMAL = Color.argb(255,130,30,140);
    public static int BACK_CHAR4_FLY = Color.argb(200,250,210,240);

    public static int BACK_CHAR5_NORMAL = Color.argb(255,150,10,10);
    public static int BACK_CHAR5_FLY = Color.argb(200,10,10,10);

    public static int LINE_H=10;
    public static int BACK_SPIDER_NORMAL = Color.argb(255,100,100,100);
    public static int BACK_SPIDER_FLY = Color.argb(255,100,100,100);
    public static int PERIOD_FRAME=32;
        public static BaseCharacter MyChar;
    public static int BACK_CHAR2_NORMAL = Color.argb(255,10,100,30);
    public static int BACK_CHAR2_FLY = Color.argb(128,10,100,30);

    public static Paint FONT=null;
    public static DisplayMetrics Metrics=new DisplayMetrics();
public static int GameCount=0;
    static
    {
        FONT=new Paint();

        FONT.setColor(EYE_COLOR);
        FONT.setTextSize(50);
        FONT.setStyle(Paint.Style.FILL);
        FONT.setAntiAlias(true);

        if(RandomRange.getRandom(1, 2)==1)
            MyChar=new Fly();
        else
            MyChar=new Locust();
    }

}
