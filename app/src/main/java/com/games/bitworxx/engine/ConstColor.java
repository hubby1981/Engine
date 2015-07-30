package com.games.bitworxx.engine;

/**
 * Created by WEIS on 27.07.2015.
 */
public class ConstColor {

    public static int RED=50;
    public static int GREEN=150;
    public static int BLUE=150;
    public static int LAST=0;

    public static String Level1="261015453212";
    public static String Level2="101612123413";
    public static int Count1 =0;
    public static int Count2 =0;

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
}
