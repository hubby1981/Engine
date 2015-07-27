package com.games.bitworxx.engine;

import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by WEIS on 21.07.2015.
 */
public class TriangleHelper {

    public static Path getTriangleDown(RectF rcIn)
    {
        Rect rc=getRect(rcIn);
        Path p = new Path();
        int seed=5;
        int x=rc.centerX()-rc.width()/seed;
        int y=rc.centerY()-rc.height()/seed;


        p.moveTo(x,y);
        x=rc.centerX();
        y=rc.centerY()+rc.height()/seed;
        p.lineTo(x,y);

        x=rc.centerX()+rc.width()/seed;
        y=rc.centerY()-rc.height()/seed;
        p.lineTo(x,y);
        x=rc.centerX()-rc.width()/seed;
        y=rc.centerY()-rc.height()/seed;
        p.lineTo(x,y);

        return p;
    }

    public static Path getTriangleUp(RectF rcIn)
    {
        Rect rc=getRect(rcIn);
        Path p = new Path();
        int seed=5;
        int x=rc.centerX()-rc.width()/seed;
        int y=rc.centerY()+rc.height()/seed;


        p.moveTo(x,y);
        x=rc.centerX();
        y=rc.centerY()-rc.height()/seed;
        p.lineTo(x,y);

        x=rc.centerX()+rc.width()/seed;
        y=rc.centerY()+rc.height()/seed;
        p.lineTo(x,y);
        x=rc.centerX()-rc.width()/seed;
        y=rc.centerY()+rc.height()/seed;
        p.lineTo(x,y);

        return p;
    }

    public static Path getTriangleLeft(RectF rcIn)
    {
        Rect rc=getRect(rcIn);
        Path p = new Path();
        int seed=5;
        int x=rc.centerX()+rc.width()/seed;
        int y=rc.centerY()-rc.height()/seed;


        p.moveTo(x,y);
        x=rc.centerX()-rc.width()/seed;
        y=rc.centerY();
        p.lineTo(x,y);

        x=rc.centerX()+rc.width()/seed;
        y=rc.centerY()+rc.height()/seed;
        p.lineTo(x,y);
        x=rc.centerX()+rc.width()/seed;
        y=rc.centerY()-rc.height()/seed;
        p.lineTo(x,y);

        return p;
    }

    public static Path getTriangleRight(RectF rcIn)
    {
        Rect rc=getRect(rcIn);
        Path p = new Path();
        int seed=5;
        int x=rc.centerX()-rc.width()/seed;
        int y=rc.centerY()-rc.height()/seed;


        p.moveTo(x,y);
        x=rc.centerX()+rc.width()/seed;
        y=rc.centerY();
        p.lineTo(x,y);

        x=rc.centerX()-rc.width()/seed;
        y=rc.centerY()+rc.height()/seed;
        p.lineTo(x,y);
        x=rc.centerX()-rc.width()/seed;
        y=rc.centerY()-rc.height()/seed;
        p.lineTo(x,y);

        return p;
    }

    public static Rect getRect(RectF rc)
    {
        return new Rect((int)rc.left,(int)rc.top,(int)rc.right,(int)rc.bottom);
    }
}
