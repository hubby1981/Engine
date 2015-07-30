package com.games.bitworxx.engine;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.games.bitworxx.engine.characters.Bee;
import com.games.bitworxx.engine.characters.Butter;
import com.games.bitworxx.engine.characters.GameConst;
import com.games.bitworxx.engine.characters.Lady;
import com.games.bitworxx.engine.characters.Locust;
import com.games.bitworxx.engine.characters.Spirit;

import java.util.ArrayList;

/**
 * Created by WEIS on 30.07.2015.
 */
public class Options extends View {
    Rect ClickClose;
    public Options(Context context, AttributeSet attrs) {
        super(context, attrs);
        invalidate();
    }

        public Rect getBounds()
        {
            return new Rect(0,0,getWidth(),getHeight());
        }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        GameConst.FONT.setColor(GameConst.MACE_COLOR);
        ColorTapet.drawOnRect2(canvas, getBounds(), GameConst.SIZE, false, 150,50,150);
        Paint paintButton = new Paint();
        paintButton.setColor(Color.argb(200, 180, 210, 180));

        paintButton.setStyle(Paint.Style.FILL_AND_STROKE);

        Paint paintStrokeButton=new Paint();
        paintStrokeButton.setStyle(Paint.Style.STROKE);

        paintStrokeButton.setStrokeWidth(4);
        paintStrokeButton.setColor(GameConst.MACE_COLOR);
        Paint back1 = new Paint();
        back1.setStyle(Paint.Style.FILL);
        back1.setColor(Color.argb(100, 50, 50, 50));
        Paint back2 = new Paint();
        back2.setStyle(Paint.Style.FILL);
        back2.setColor(Color.argb(75,50,50,50));

        ArrayList<Rect> main =  RectHandler.getGrid(8, 1, getBounds());
        String title = "options";
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, title, main.get(0), 0);



        ClickClose=main.get(7);
        canvas.drawRect(ClickClose, paintButton);
        canvas.drawRect(ClickClose, paintStrokeButton);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Close the options", ClickClose, 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT,canvas,"Coming soon options for game",main.get(2),0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            if(ClickClose.contains((int)event.getX(),(int)event.getY()))
            {
                ((Activity)getContext()).finish();

            }

        }
        return true;
    }


}
