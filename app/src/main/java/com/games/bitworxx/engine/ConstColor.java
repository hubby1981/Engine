package com.games.bitworxx.engine;

/**
 * Created by WEIS on 27.07.2015.
 */
public class ConstColor {

    public static int RED=50;
    public static int GREEN=150;
    public static int BLUE=150;
    public static int LAST=0;

    public static String Level1="26101545321234126101545321234131015453212341321015453411013234121234112521015453411013210154534110122123112352";
    public static String Level2="10161212234133410161212234133413234132341334101612122341334132341324321301323413243214024321401323413243214022";
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
