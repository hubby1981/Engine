package com.games.bitworxx.engine;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.games.bitworxx.engine.characters.GameConst;
import com.games.bitworxx.engine.characters.Spider;

import java.util.ArrayList;

/**
 * Created by WEIS on 27.07.2015.
 */
public class MaceDrawer {

    private ArrayList<BackRect> BackRects = new ArrayList<>();

    Rect BOUNDS=null;
    public boolean MOVEME=true;

    ArrayList<Spider> Spiders = new ArrayList<>();

    public void onDraw(Canvas canvas,Rect bounds,int t)
    {
        BOUNDS = bounds;
        if (BackRects.size() == 0) generateRects(t);
        try {

            int index =2;
            for(int x=0;x<index;x++)
                BackRects.get(x).onDraw(canvas,bounds);

            for(Spider s:Spiders)
                s.onDraw(canvas);

        }catch(Exception e){};
    }

        public boolean isInRect(int x,int y)
        {
            int index =2;
            boolean result=false;
            for(int xx=0;xx<index;xx++)
                if(!result)
                {
                    BackRect r=BackRects.get(xx);
                    result = r.BaseRect.contains(x,y);

                    if(!result&&r.BaseRect2!=null)
                        result=r.BaseRect2.contains(x,y);
                }
            return result;
        }

    private void generateRects(int t)
    {
        int size = ConstColor.Level1.length();

        int max = BOUNDS.width() /1;
        int height = BOUNDS.height()/20;
        boolean alt = false;
        int l=0;


        for(int x=0;x<size;x++)
        {
            int count=getCount1();
            int h =(int)(height*1.6)*count;
            Rect rc=new Rect(l,t,l+max,t+h);
            BackRects.add(new BackRect(rc,alt?128:255,true,getCount2(),height));


            l+=max;
            alt=!alt;
        }



    }

    public int getCount1()
    {
        if(ConstColor.Count1>ConstColor.Level1.length()-1)ConstColor.Count1=0;
        String result = ConstColor.Level1.substring(ConstColor.Count1,ConstColor.Count1+1);
        ConstColor.Count1++;
        return Integer.parseInt(result);
    }
    public int getCount2()
    {
        if(ConstColor.Count2>ConstColor.Level2.length()-1)ConstColor.Count2=0;
        String result = ConstColor.Level2.substring(ConstColor.Count2,ConstColor.Count2+1);
        ConstColor.Count2++;
        return Integer.parseInt(result);
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
            GameConst.GameCount++;
        }


    }
}
