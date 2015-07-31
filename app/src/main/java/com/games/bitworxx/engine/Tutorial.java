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

import com.games.bitworxx.engine.characters.GameConst;

import java.util.ArrayList;

/**
 * Created by WEIS on 30.07.2015.
 */
public class Tutorial extends View {
    Rect ClickClose;
    private Rect ClickSelect1;
    private Rect ClickSelect2;

    private int Page=1;
    private int Pages=5;
    public Tutorial(Context context, AttributeSet attrs) {
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
        ColorTapet.drawOnRect2(canvas, getBounds(), GameConst.SIZE, false, 50, 150, 150);
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
        String title = "tutorial";
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, title, main.get(0), 0);



        ClickClose=main.get(7);
        canvas.drawRect(ClickClose, paintButton);
        canvas.drawRect(ClickClose, paintStrokeButton);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Close the tutorial", ClickClose, 0);


        ArrayList<Rect> select = RectHandler.getGrid(1,7,main.get(5));

        ArrayList<Rect> select2 = RectHandler.getGrid(1,7,main.get(6));

        ClickSelect1=select.get(1);
        ClickSelect2=select.get(5);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, String.valueOf(Page)+"/"+String.valueOf(Pages), select.get(3), 3);

        ClickSelect1=new Rect(ClickSelect1.left,ClickSelect1.top+ClickSelect1.height()/2,ClickSelect1.right,ClickSelect1.bottom);
        ClickSelect2=new Rect(ClickSelect2.left,ClickSelect2.top+ClickSelect2.height()/2,ClickSelect2.right,ClickSelect2.bottom);

        canvas.drawRect(ClickSelect1,paintButton);
        canvas.drawRect(ClickSelect1,paintStrokeButton);

        canvas.drawRect(ClickSelect2,paintButton);
        canvas.drawRect(ClickSelect2, paintStrokeButton);
        canvas.drawPath(TriangleHelper.getTriangleLeft(RectHandler.getToRectF(ClickSelect1)), paintStrokeButton);
        canvas.drawPath(TriangleHelper.getTriangleRight(RectHandler.getToRectF(ClickSelect2)), paintStrokeButton);


        if(Page==1)
            drawStep1(canvas, main);
        if(Page==2)
            drawStep2(canvas, main);
        if(Page==3)
            drawStep3(canvas, main);
        if(Page==4)
            drawStep4(canvas, main);
        if(Page==5)
            drawStep5(canvas, main);
    }


    public void drawStep1(Canvas canvas,ArrayList<Rect> main)
    {
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," Gameplay Step 1", main.get(1), 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," Select in Main Menu a flyer", main.get(2), 1);

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," and then click play to start", main.get(5), 1);


        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.step1);


        Rect rc = new Rect(0,0,icon.getWidth(),icon.getHeight());

        Rect in = new Rect(main.get(1).left,main.get(2).bottom,main.get(1).right,main.get(4).bottom);
        in.top-=in.height()/4;
        in.bottom-=in.height()/8;
        int w = in.height()*2;
        in = new Rect((int)in.exactCenterX()-w/2,(int)in.exactCenterY()-in.height()/2,(int)in.exactCenterX()+w/2,(int)in.exactCenterY()+in.height()/2);

        canvas.drawBitmap(icon,rc,in,null);
    }

    public void drawStep2(Canvas canvas,ArrayList<Rect> main)
    {
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," Gameplay Step 2", main.get(1), 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," the flyer go up and forward", main.get(2), 1);

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," you have to control him with back and down", main.get(5), 1);


        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.step2);


        Rect rc = new Rect(0,0,icon.getWidth(),icon.getHeight());

        Rect in = new Rect(main.get(1).left,main.get(2).bottom,main.get(1).right,main.get(4).bottom);
        in.top-=in.height()/4;
        in.bottom-=in.height()/8;
        int w = in.height()*2;
        in = new Rect((int)in.exactCenterX()-w/2,(int)in.exactCenterY()-in.height()/2,(int)in.exactCenterX()+w/2,(int)in.exactCenterY()+in.height()/2);

        canvas.drawBitmap(icon,rc,in,null);
    }

    public void drawStep3(Canvas canvas,ArrayList<Rect> main)
    {
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," Gameplay Step 3", main.get(1), 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," the flyer go up and forward", main.get(2), 1);

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," use heropower to fly through spiders", main.get(5), 1);


        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.step3);


        Rect rc = new Rect(0,0,icon.getWidth(),icon.getHeight());

        Rect in = new Rect(main.get(1).left,main.get(2).bottom,main.get(1).right,main.get(4).bottom);
        in.top-=in.height()/4;
        in.bottom-=in.height()/8;
        int w = in.height()*2;
        in = new Rect((int)in.exactCenterX()-w/2,(int)in.exactCenterY()-in.height()/2,(int)in.exactCenterX()+w/2,(int)in.exactCenterY()+in.height()/2);

        canvas.drawBitmap(icon,rc,in,null);
    }

    public void drawStep4(Canvas canvas,ArrayList<Rect> main)
    {
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," Gameplay Step 4", main.get(1), 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," danger dont touch a block", main.get(2), 1);

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," danger dont touch a spider - yam yam", main.get(5), 1);


        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.step4);


        Rect rc = new Rect(0,0,icon.getWidth(),icon.getHeight());

        Rect in = new Rect(main.get(1).left,main.get(2).bottom,main.get(1).right,main.get(4).bottom);
        in.top-=in.height()/4;
        in.bottom-=in.height()/8;
        int w = in.height()*2;
        in = new Rect((int)in.exactCenterX()-w/2,(int)in.exactCenterY()-in.height()/2,(int)in.exactCenterX()+w/2,(int)in.exactCenterY()+in.height()/2);

        canvas.drawBitmap(icon,rc,in,null);
    }

    public void drawStep5(Canvas canvas,ArrayList<Rect> main)
    {
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," Gameplay Step 5", main.get(1), 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," score to the max count of rooms", main.get(2), 1);

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas," unlock with high scores the next flyer", main.get(5), 1);


        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.step5);


        Rect rc = new Rect(0,0,icon.getWidth(),icon.getHeight());

        Rect in = new Rect(main.get(1).left,main.get(2).bottom,main.get(1).right,main.get(4).bottom);
        in.top-=in.height()/4;
        in.bottom-=in.height()/8;
        int w = in.height()*2;
        in = new Rect((int)in.exactCenterX()-w/2,(int)in.exactCenterY()-in.height()/2,(int)in.exactCenterX()+w/2,(int)in.exactCenterY()+in.height()/2);

        canvas.drawBitmap(icon,rc,in,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            if(ClickClose.contains((int)event.getX(),(int)event.getY()))
            {
                ((Activity)getContext()).finish();

            }
            if(ClickSelect1.contains((int)event.getX(),(int)event.getY()))
            {
                Page--;
                if(Page==0)
                    Page=Pages;
                invalidate();

            }
            if(ClickSelect2.contains((int)event.getX(),(int)event.getY()))
            {
                Page++;
                if(Page>Pages)
                    Page=1;
                invalidate();

            }
        }
        return true;
    }


}
