package com.games.bitworxx.engine;

/**
 * Created by WEIS on 27.07.2015.
 */
public class ConstColor {

    public static int RED=50;
    public static int GREEN=150;
    public static int BLUE=150;
    public static int LAST=0;

    public static String Level1_normal="21233232";
    public static String Level2_normal="12132211";

    public static String Level1_locust="21301545321234126101545321";
    public static String Level2_locust="13131212234133412161212234";

    public static String Level1_bee="26101545321234126101545321234131015";
    public static String Level2_bee="20161212234133410161212234133413234";

    public static String Level1_budda="2123413101545261015453212341261015453211310154532123413210152";
    public static String Level2_budda="1212234133410110161212234133410161212234133424132341334101612";

    public static String Level1_spirit="3230154532123412610154532123413101545321234132101545555341101323412123411";
    public static String Level2_spirit="1036121223413341016121223413341323413234133410161212111234133413234132432";

    public static String Level1_lady="26101545321234126101545321234131015453212341321015453411013234121234112521015453411013210154534110122123112352";
    public static String Level2_lady="10161212234133410161212234133413234132341334101612122341334132341324321301323413243214024321401323413243214022";


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
