package com.games.bitworxx.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.games.bitworxx.engine.characters.BaseCharacter;
import com.games.bitworxx.engine.characters.Fly;
import com.games.bitworxx.engine.characters.GameConst;

import java.util.ArrayList;

/**
 * Created by WEIS on 27.07.2015.
 */
public class ScrollBack extends View {

    public  boolean GAME_OVER = false ;
    public boolean PLAY=false;
    public BackDrawer BACK=new BackDrawer();
    public MaceDrawer MACE=new MaceDrawer();

    Rect TOP;
    Rect BOTTOM;



    private int MAX=8;
    public boolean UPDATE=true;
    public ScrollBack(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    public void release()
    {
        if(BACK!=null)
        {
            BACK.UPDATE=false;
            BACK.MOVEME=false;
            BACK.Update.interrupt();
            BACK.Update=null;
        }
        if(MACE!=null)
        {

            MACE.MOVEME=false;

        }
        UPDATE=false;
    }

    public Controls getControls()
    {
        return ((GameActivity) getContext()).getControl();
    }
    @Override
    protected void onDraw(Canvas canvas)
    {

        if(UPDATE){
            UPDATE=false;


            TOP=new Rect(0,0,getWidth(),0+getHeight()/MAX);
            BOTTOM=new Rect(0,(0+getHeight())-getHeight()/MAX,getWidth(),0+getHeight());

            super.onDraw(canvas);

            BACK.MOVEME=!GAME_OVER;
            MACE.MOVEME=!GAME_OVER;

            BACK.onDraw(canvas, new Rect(0, 0, getWidth(), getHeight()));


            if(!GAME_OVER&&!getControls().ThisChar.IsDead)
                MACE.onDraw(canvas, new Rect(0, 0, getWidth(), BOTTOM.top), TOP.bottom);

            MACE.move(GameConst.MOVE_MACE);
            Paint Mace = new Paint();
            Mace.setColor(Color.BLACK);
            Mace.setStyle(Paint.Style.FILL);


            canvas.drawRect(TOP, Mace);
            canvas.drawRect(BOTTOM, Mace);
            if(!GAME_OVER && PLAY) {
                String s = GameConst.GameCount < 10 ? "00" + String.valueOf(GameConst.GameCount) : GameConst.GameCount < 100 ? "0" + String.valueOf(GameConst.GameCount) : String.valueOf(GameConst.GameCount);

               FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT,canvas,s,new Rect(TOP.left+ TOP.width()/4, TOP.top,TOP.right-TOP.width()/4,TOP.bottom),0);
            }


            UPDATE=true;
        }



    }

    public boolean isInRect(int x,int y)
    {
        if(MACE!=null)
            return MACE.isInRect(x,y);
        return false;
    }
    public boolean isInRect(float x,float y)
    {
        if(MACE!=null)
            return MACE.isInRect((int)x,(int)y);
        return false;
    }

}
