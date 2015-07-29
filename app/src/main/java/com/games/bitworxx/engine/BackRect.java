package com.games.bitworxx.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.games.bitworxx.engine.characters.GameConst;
import com.games.bitworxx.engine.characters.Spider;

import java.util.ArrayList;

/**
 * Created by WEIS on 27.07.2015.
 */
public class BackRect {



    public int A=0;
    public Rect BaseRect=null;
    public Rect BaseRect2=null;
    public boolean IsMace=false;
    public int Count=0;
    public int Height=0;



    public BackRect(Rect baseRect,int alpha,boolean isMace,int count,int height)
    {
        BaseRect = baseRect;
IsMace=isMace;
        A=alpha;
        Count=count;
        Height=height;




    }



    public void onDraw(Canvas canvas,Rect bounds)
    {
        Paint back = new Paint();


        if(!IsMace) {
            back.setColor(Color.WHITE);
            canvas.drawRect(BaseRect, back);
            back.setColor(Color.argb(A, ConstColor.RED, ConstColor.GREEN, ConstColor.BLUE));
        }else
        {
            back.setColor(Color.DKGRAY);

        }
        back.setStyle(Paint.Style.FILL);
        if(Count>0&&IsMace)
        {
            int c = Count;
            BaseRect2=new Rect(BaseRect.left,bounds.bottom- ((Height*2)*c),BaseRect.right,bounds.bottom);
            canvas.drawRect(BaseRect2,back);
        }
        canvas.drawRect(BaseRect, back);


        back=null;
    }

    public void moveLeft(int move)
    {

        BaseRect.left-=move;
        BaseRect.right-=move;


    }

    public boolean isInvisible()
    {



        return BaseRect.right<=0;
    }

}
