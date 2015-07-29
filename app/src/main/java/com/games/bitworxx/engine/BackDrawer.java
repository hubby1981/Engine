package com.games.bitworxx.engine;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.games.bitworxx.engine.characters.GameConst;

import java.util.ArrayList;

/**
 * Created by WEIS on 27.07.2015.
 */
public class BackDrawer {

    private ArrayList<BackRect> BackRects = new ArrayList<>();
    public Thread Update=null;
    Rect BOUNDS=null;
    boolean Running=false;
    public int LAST =0;
    public boolean UPDATE = false;
    public boolean MOVEME=true;
    public void onDraw(Canvas canvas,Rect bounds)
    {
        BOUNDS=bounds;
        if(BackRects.size()==0)    generateRects();
        try {
            if(UPDATE) {
                UPDATE = false;
                for (BackRect r : BackRects)
                    r.onDraw(canvas,bounds);


            }
        }catch(Exception e){}
    }



    private void generateRects()
    {
        int size = 60;
UPDATE=true;
        int max = BOUNDS.width() /6;
        boolean alt = false;
        int l=0;
        int t=0;

        for(int x=0;x<size;x++)
        {
            BackRects.add(new BackRect(new Rect(l,t,l+max,t+BOUNDS.height()),alt?128:255,false,0,0));
            l+=max;
            alt=!alt;
        }

        if(Update==null)
        {
            Update=new Thread(new Runnable() {
                @Override
                public void run() {

                    Running=true;
                    while(Running) {
                        move(GameConst.MOVE_BACK);
                        UPDATE=true;

                        try {
                            Thread.sleep(32);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            Update.start();
        }

    }

    public void move(int move)
    {
        if(!MOVEME)return;
        for(BackRect r : BackRects)
        {
            r.moveLeft(move);

        }
        BackRect r = BackRects.get(0);
        if(r.isInvisible())
        {
            int p = BackRects.get(BackRects.size()-1).BaseRect.right;
            int w = r.BaseRect.width();
            r.BaseRect.left=p;
            r.BaseRect.right=p+w;
            r.moveLeft(move);
            BackRects.remove(0);
            BackRects.add(r);
            LAST++;
            if(LAST>=50&&GameConst.ChangeColor)
            {
                LAST=0;
                ConstColor.setRandom();
            }

        }


    }
}
