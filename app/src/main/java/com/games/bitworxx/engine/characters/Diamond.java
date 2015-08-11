package com.games.bitworxx.engine.characters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.games.bitworxx.engine.MainActivity;
import com.games.bitworxx.engine.R;

/**
 * Created by WEIS on 11.08.2015.
 */
public class Diamond {

    public int X=0;
    public int Y=0;
    public boolean IsHit=false;
    Bitmap Icon = null;
    public Diamond(int x,int y,Resources context)
    {
        X=x;
        Y=y;
        Icon= BitmapFactory.decodeResource(context, R.drawable.diamond);
    }

    public void onDraw(Canvas canvas)
    {
        if(!IsHit)
        {
            if(Icon!=null)
            {
                canvas.drawBitmap(Icon,X,Y,null);
            }

        }
    }

    public void move(int move)
    {
        X-=move;
    }


    public boolean checkHit(Rect rc)
    {
        IsHit=false;
        if(rc.contains(X,Y))
        {
            IsHit=true;
            GameConst.GameCount++;
        }
        return IsHit;
    }
}
