package com.games.bitworxx.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.games.bitworxx.engine.characters.GameConst;
import com.games.bitworxx.engine.characters.Spider;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by WEIS on 29.07.2015.
 */
public class Spiders extends View {


    ArrayList<Spider> Spiders = null;

    public Spiders(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Rect bounds = new Rect(0,0,getWidth(),getHeight());


        if(Spiders==null)
        {
            Spiders=new ArrayList<>();
            doSpider(1);
        }

        if(Spiders.size()>0)
            for(Spider s :Spiders)
                s.onDraw(canvas);
    }
    public Controls getControls()
    {
        return ((GameActivity) getContext()).getControl();
    }
    public void doSpider(int index)
    {
        BackRect rr=getBack().getMaceRect(index);
        if(Spiders!=null &&rr!=null) {
            if(Spiders.size()<5) {
                int max = Spiders.size() == 0 ? 1 : Spiders.size() == 1 ? 2 : 1;
                max = RandomRange.getRandom(1, max);
                for (int mm = 0; mm < max; mm++) {

                    int si = GameConst.MyChar.Size;

                    int ss = RandomRange.getRandom(1,5);
                    if(ss==1)si*=0.8;
                    if(ss==2)si*=1;
                    if(ss==3)si*=1.2;
                    if(ss==4)si*=1.4;
                    if(ss==5)si*=1.6;
                    Spider s = new Spider(RandomRange.getRandom(rr.BaseRect.left + si, rr.BaseRect.right - si), rr.BaseRect.bottom + RandomRange.getRandom(getHeight() / 20, getHeight() / 10), null, false);
                    s.Net = new Rect(s.X,rr.BaseRect.bottom-si,s.X+si,rr.BaseRect.bottom+si/4);
                    s.OWN_DOWN = RandomRange.getRandom(getHeight() / 100, getHeight() / 50);
                    s.Size = si;
                    Spiders.add(s);

                }
            }
        }
    }

    public void onMove(int move)
    {
        ArrayList<Spider> index = new ArrayList<>();

        if(Spiders!=null && Spiders.size()>0)
            for(Spider s :Spiders)
            {
                if(s.Net!=null) {
                    s.Net.left -= move;
                    s.Net.right -= move;

                }
                s.X-=move;
                if (s.X + s.Size < 0) {
                    index.add(s);
                }

            }

        for(Spider ii : index)
        {
            Spiders.remove(ii);
        }
    }

    public ScrollBack getBack ()
    {
        return ((GameActivity)getContext()).getBack();
    }
}
