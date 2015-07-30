package com.games.bitworxx.engine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.games.bitworxx.engine.characters.Bee;
import com.games.bitworxx.engine.characters.Butter;
import com.games.bitworxx.engine.characters.Fly;
import com.games.bitworxx.engine.characters.GameConst;
import com.games.bitworxx.engine.characters.Lady;
import com.games.bitworxx.engine.characters.Locust;
import com.games.bitworxx.engine.characters.Spider;
import com.games.bitworxx.engine.characters.Spirit;

import java.util.ArrayList;

/**
 * Created by WEIS on 22.07.2015.
 */
public class MainMenu extends View {

    private Spider Spider1;

    private int TheChar;
    private int MaxChar=6;
    private Rect ClickPlay;
    private Rect ClickHow;

    private Rect ClickSelect1;
    private Rect ClickSelect2;
    private Rect ClickBuy;
    private Rect ClickShop;
    private Rect ClickOpt;
    private Rect ClickHelp;

    private Rect ClickClose;

    public MainMenu(Context context, AttributeSet attrs) {
        super(context, attrs);     TheChar=   MainActivity.loadChar();        changeChar();
invalidate();
    }



private void changeChar()
{
    if(TheChar==1)
        GameConst.MyChar=new Fly();
    if(TheChar==2)
        GameConst.MyChar=new Locust();
    if(TheChar==3)
        GameConst.MyChar=new Bee();
    if(TheChar==4)
        GameConst.MyChar=new Butter();
    if(TheChar==5)
        GameConst.MyChar=new Spirit();
    if(TheChar==6)
        GameConst.MyChar=new Lady();
    MainActivity.saveChar(TheChar);
}
public Rect getBounds()
{
    return new Rect(0,0,getWidth(),getHeight());
}
   public void drawIt(Canvas canvas)
    {
        ColorTapet.drawOnRect2(canvas, getBounds(), GameConst.SIZE, false, 100,160,200);

        String title = "Tasty";
        String title2="Flyer";
        String best = "Best: "+String.valueOf(MainActivity.readBest());
        float oldSize= GameConst.FONT.getTextSize();
        GameConst.FONT.setColor(GameConst.MACE_COLOR);

        ArrayList<Rect> main =  RectHandler.getGrid(8, 1, getBounds());



        Rect net = new Rect(getCombined(main.get(3)).left,getCombined(main.get(1)).top,getCombined(main.get(3)).left+main.get(3).height()/3,getCombined(main.get(1)).top+main.get(3).height()/3);

        if(Spider1==null)
            Spider1=new Spider(getCombined(main.get(3)).left,getCombined(main.get(3)).top,net,true);

        Spider1.Size=main.get(3).height()/3;



        Paint paintButton = new Paint();
        paintButton.setColor(Color.argb(200, 180, 210, 180));

        paintButton.setStyle(Paint.Style.FILL_AND_STROKE);

        Paint paintStrokeButton=new Paint();
        paintStrokeButton.setStyle(Paint.Style.STROKE);

        paintStrokeButton.setStrokeWidth(4);
        paintStrokeButton.setColor(GameConst.MACE_COLOR);

        ClickPlay = getCombinedSmall(main.get(3));
        ClickHow = getCombinedSmall(main.get(3));
        ClickHow.left=ClickPlay.right+(getWidth()/100)*3;
        ClickHow.right=ClickHow.left+ClickPlay.width()+(getWidth()/100)*6;

        ClickPlay.left-=ClickPlay.width()/2;
        canvas.drawRect(ClickPlay, paintButton);
        canvas.drawRect(ClickPlay, paintStrokeButton);
        paintButton.setColor(Color.argb(200, 190, 100, 60));

        canvas.drawRect(ClickHow, paintButton);
        canvas.drawRect(ClickHow, paintStrokeButton);
        paintButton.setColor(Color.argb(200, 180, 210, 180));


        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, title, main.get(0), 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT,canvas,title2, main.get(1),0);

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, best, getCombined(main.get(2)), 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "SELECT Flyer", getCombined(main.get(4)), 0);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, GameConst.MyChar.Name, getCombined(main.get(5)), 1);
        ArrayList<Rect> select = RectHandler.getGrid(1,7,main.get(5));

        Rect charMini = RectHandler.combineRects(select.get(2),select.get(3));
        GameConst.MyChar.Size = charMini.height()/3;
        GameConst.MyChar.IsAnimated=false;
        GameConst.MyChar.X=(int)charMini.exactCenterX()+GameConst.MyChar.Size/2;
        GameConst.MyChar.Y=(int)charMini.exactCenterY()+GameConst.MyChar.Size/2;



        ClickSelect1=select.get(1);
        ClickSelect2=select.get(5);

        ClickSelect1=new Rect(ClickSelect1.left,ClickSelect1.top+ClickSelect1.height()/2,ClickSelect1.right,ClickSelect1.bottom);
        ClickSelect2=new Rect(ClickSelect2.left,ClickSelect2.top+ClickSelect2.height()/2,ClickSelect2.right,ClickSelect2.bottom);

        canvas.drawRect(ClickSelect1,paintButton);
        canvas.drawRect(ClickSelect1,paintStrokeButton);

        canvas.drawRect(ClickSelect2,paintButton);
        canvas.drawRect(ClickSelect2, paintStrokeButton);



        GameConst.MyChar.onDraw(canvas);
        if(GameConst.MyChar.isLocked())
        {
            ArrayList<Rect> chars = RectHandler.getGrid(4, 1, RectHandler.combineRects(select.get(2), select.get(4)));
            Rect charLocked = RectHandler.combineRects(chars.get(1),chars.get(3));

            //GameConst.FONT.setColor(GameConst.EYE_COLOR);

            Paint locked = new Paint();
            locked.setColor(Color.argb(128, 200, 0, 0));
            locked.setStyle(Paint.Style.STROKE);
            locked.setStrokeWidth(GameConst.MyChar.Size/4);
            //canvas.drawRect(charLocked, locked);
            //canvas.drawRect(charLocked, paintStrokeButton);

            float ap1 =GameConst.MyChar.X-(GameConst.MyChar.Size);
            float ap2=GameConst.MyChar.Y-(GameConst.MyChar.Size);
            float ap3 = GameConst.MyChar.X+(GameConst.MyChar.Size*2);
            float ap4 = GameConst.MyChar.Y+(GameConst.MyChar.Size*2);

            float aap=GameConst.MyChar.Size/2;
            ap1+=aap;
            ap2+=aap;
            ap3-=aap;
            ap4-=aap;

            canvas.drawArc(ap1, ap2, ap3, ap4, 0, 360, true, locked);

            //FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "LOCKED", charLocked, 0);
            canvas.drawLine(ap1+aap/2,ap2+aap/2,ap3-aap/2,ap4-aap/2,locked);
            GameConst.FONT.setColor(GameConst.MACE_COLOR);

            ClickBuy = new Rect(0,main.get(6).top,getWidth(),main.get(6).bottom);


            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas,GameConst.MyChar.getUnlockText(), ClickBuy, 0);
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas,"You can buy the flyer in shop to unlock it", new Rect(0,main.get(6).top,getWidth(),main.get(6).bottom), 3);


        }

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Play", ClickPlay, 0);
        GameConst.FONT.setColor(GameConst.EYE_COLOR);

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "TUTORIAL", ClickHow, 0);
        GameConst.FONT.setColor(GameConst.MACE_COLOR);


        Rect button = getCombinedMiddle(main.get(7));

        ArrayList<Rect> buttons = RectHandler.getGrid(1,3,new Rect(0,button.top,getWidth(),button.bottom));
        ClickShop =buttons.get(0);
        ClickOpt=buttons.get(1);
        ClickClose=buttons.get(2);

        ClickShop.left+=getWidth()/100;
        ClickShop.right-=getWidth()/100;

        ClickClose.left+=getWidth()/100;
        ClickClose.right-=getWidth()/100;
        paintButton.setColor(Color.argb(200, 60, 60, 180));

        canvas.drawRect(ClickShop, paintButton);
        paintButton.setColor(Color.argb(200, 180, 210, 180));
        GameConst.FONT.setColor(GameConst.EYE_COLOR);
        canvas.drawRect(ClickShop, paintStrokeButton);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "SHOP", ClickShop, 0);
        GameConst.FONT.setColor(GameConst.MACE_COLOR);

        canvas.drawRect( ClickOpt, paintButton);
        canvas.drawRect( ClickOpt, paintStrokeButton);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Options", ClickOpt, 0);
        canvas.drawRect( ClickClose, paintButton);
        canvas.drawRect( ClickClose, paintStrokeButton);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "Close", ClickClose, 0);

        GameConst.FONT.setColor(GameConst.EYE_COLOR);
        GameConst.FONT.setTextSize(oldSize);






        paintStrokeButton.setStyle(Paint.Style.FILL);
        canvas.drawPath(TriangleHelper.getTriangleLeft(RectHandler.getToRectF(ClickSelect1)), paintStrokeButton);
        canvas.drawPath(TriangleHelper.getTriangleRight(RectHandler.getToRectF(ClickSelect2)), paintStrokeButton);



        Spider1.onDraw(canvas);


    }

    private Rect getCombined(Rect old)
    {
        ArrayList<Rect> rects = RectHandler.getGrid(1,8,old);
        return RectHandler.combineRects(rects.get(1),rects.get(6));
    }
    private Rect getCombinedMiddle(Rect old)
    {
        ArrayList<Rect> rects = RectHandler.getGrid(1,8,old);
        Rect middle =  RectHandler.combineRects(rects.get(2), rects.get(5));

        rects = RectHandler.getGrid(3,1,middle);

        return RectHandler.combineRects(rects.get(0), rects.get(1));
    }
    private Rect getCombinedSmall(Rect old)
    {
        ArrayList<Rect> rects = RectHandler.getGrid(1, 8, old);
        return RectHandler.combineRects(rects.get(3),rects.get(4));
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        drawIt(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            if(ClickPlay.contains((int)event.getX(),(int)event.getY()))
            {
                if(GameConst.MyChar.isLocked())
                {
                    MainActivity.Shop.run();
                }else{
                MainActivity.Start.run();}
            }
            if(ClickHow.contains((int)event.getX(),(int)event.getY()))
            {

                    MainActivity.Tutorial.run();
            }
            if(ClickOpt.contains((int)event.getX(),(int)event.getY()))
            {

                MainActivity.Options.run();
            }
            if(ClickSelect1.contains((int)event.getX(),(int)event.getY()))
            {
                TheChar--;
                if(TheChar==0)TheChar=MaxChar;
                changeChar();
                invalidate();
            }
            if(ClickSelect2.contains((int)event.getX(),(int)event.getY()))
        {
            TheChar++;
            if(TheChar>MaxChar)TheChar=1;
            changeChar();
            invalidate();
        }

            if(ClickClose.contains((int)event.getX(),(int)event.getY()))
        {
            System.exit(0);
        }

            if(ClickShop.contains((int)event.getX(),(int)event.getY()))
            {
               MainActivity.Shop.run();
            }
        }
        return true;
    }





}
