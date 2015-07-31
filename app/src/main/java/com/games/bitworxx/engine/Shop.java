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
 * Created by WEIS on 29.07.2015.
 */
public class Shop extends View {
    Rect ClickClose;
    Rect Buy1,Buy2,Buy3,Buy4,Buy5,Buy6;
    public Shop(Context context, AttributeSet attrs) {
        super(context, attrs);


    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        GameConst.FONT.setColor(GameConst.MACE_COLOR);
        ColorTapet.drawOnRect2(canvas, getBounds(), GameConst.SIZE, false, 100, 200, 100);
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

        Locust l = new Locust();
        Bee b = new Bee();
        Butter bb = new Butter();
        Spirit s = new Spirit();
        Lady ll = new Lady();

        l.IsAnimated=false;
        b.IsAnimated=false;
        bb.IsAnimated=false;
        s.IsAnimated=false;
        ll.IsAnimated=false;

        ArrayList<Rect> main =  RectHandler.getGrid(8, 1, getBounds());
        String title = "SHOP";
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, title, main.get(0), 0);


        Rect buy1 = main.get(1);
        ArrayList<Rect> rectsBuy1= RectHandler.getGrid(1,8,buy1);

        Rect buy2 = main.get(2);
        ArrayList<Rect> rectsBuy2= RectHandler.getGrid(1,8,buy2);

        Rect buy3 = main.get(3);
        ArrayList<Rect> rectsBuy3= RectHandler.getGrid(1,8,buy3);

        Rect buy4 = main.get(4);
        ArrayList<Rect> rectsBuy4= RectHandler.getGrid(1,8,buy4);

        Rect buy5 = main.get(5);
        ArrayList<Rect> rectsBuy5= RectHandler.getGrid(1,8,buy5);

        Rect buy6 = main.get(6);
        ArrayList<Rect> rectsBuy6= RectHandler.getGrid(1,8,buy6);

        l.X=rectsBuy1.get(1).left;
        l.Y=rectsBuy1.get(1).centerY();
        l.Size=rectsBuy1.get(1).width()/2;

        b.X=rectsBuy2.get(1).left;
        b.Y=rectsBuy2.get(1).centerY();
        b.Size=rectsBuy2.get(1).width()/2;

        bb.X=rectsBuy3.get(1).left;
        bb.Y=rectsBuy3.get(1).centerY();
        bb.Size=rectsBuy3.get(1).width()/2;

        s.X=rectsBuy4.get(1).left;
        s.Y=rectsBuy4.get(1).centerY();
        s.Size=rectsBuy4.get(1).width()/2;

        ll.X=rectsBuy5.get(1).left;
        ll.Y=rectsBuy5.get(1).centerY();
        ll.Size=rectsBuy5.get(1).width()/2;

        canvas.drawRect(main.get(1),back1);
        canvas.drawRect(main.get(2),back2);
        canvas.drawRect(main.get(3),back1);
        canvas.drawRect(main.get(4), back2);
        canvas.drawRect(main.get(5), back1);
        canvas.drawRect(main.get(6), back2);

        l.onDraw(canvas);
        b.onDraw(canvas);
        bb.onDraw(canvas);
        s.onDraw(canvas);
        ll.onDraw(canvas);
        GameConst.FONT.setColor(GameConst.EYE_COLOR);

        String buyStorePrice1="load price";
        String buyStorePrice2="load price";
        String buyStorePrice3="load price";
        String buyStorePrice4="load price";
        String buyStorePrice5="load price";
        String buyStorePrice6="load price";

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, l.Name, RectHandler.combineRects(rectsBuy1.get(2), rectsBuy1.get(5)), 1);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, b.Name, RectHandler.combineRects(rectsBuy2.get(2),           rectsBuy2.get(5)),   1);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, bb.Name, RectHandler.combineRects(rectsBuy3.get(2),          rectsBuy3.get(5)),   1);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT,canvas,s.Name,    RectHandler.combineRects(rectsBuy4.get(2),          rectsBuy4.get(5)),   1);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ll.Name, RectHandler.combineRects(rectsBuy5.get(2),          rectsBuy5.get(5)),   1);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "FLYER PACK", RectHandler.combineRects(rectsBuy6.get(2),      rectsBuy6.get(5)),   1);

        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "or 25 score  arty mcfly", RectHandler.combineRects(rectsBuy1.get(2), rectsBuy1.get(5)), 8);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "or 75 score  "+l.Name, RectHandler.combineRects(rectsBuy2.get(2), rectsBuy2.get(5)), 8);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "or 150 score "+b.Name, RectHandler.combineRects(rectsBuy3.get(2), rectsBuy3.get(5)), 8);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "or 250 score "+bb.Name, RectHandler.combineRects(rectsBuy4.get(2), rectsBuy4.get(5)), 8);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "or 500 score "+s.Name, RectHandler.combineRects(rectsBuy5.get(2), rectsBuy5.get(5)), 8);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "over 40% less then single buy", RectHandler.combineRects(rectsBuy6.get(2), rectsBuy6.get(5)), 10);


        Buy1=getCombinedRect(rectsBuy1,6,7);
        Buy2=getCombinedRect(rectsBuy2,6,7);
        Buy3=getCombinedRect(rectsBuy3,6,7);
        Buy4=getCombinedRect(rectsBuy4,6,7);
        Buy5=getCombinedRect(rectsBuy5,6,7);
        Buy6=getCombinedRect(rectsBuy6,6,7);

        String ss1="buy";
        String ss2="already unlocked";
int other=0;
        if(MainActivity.readBuy(l.getCode())==0&&l.isLocked()) {
            canvas.drawRect(Buy1, paintButton);
            canvas.drawRect(Buy1, paintStrokeButton);
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ss1, Buy1, 0);


            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ShopActivity.Price1 != null ? ShopActivity.Price1 : buyStorePrice1, RectHandler.combineRects(rectsBuy1.get(3), rectsBuy1.get(4)), 0);
            other++;
        }
        else
        {
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ss2, RectHandler.combineRects(rectsBuy1.get(2),      rectsBuy1.get(6)),   0);
            Buy1=null;
        }

        if(MainActivity.readBuy(b.getCode())==0 && b.isLocked()) {
            canvas.drawRect(Buy2, paintButton);
            canvas.drawRect(Buy2, paintStrokeButton);
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ss1, Buy2, 0);

            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas,ShopActivity.Price2!=null?ShopActivity.Price2: buyStorePrice2, RectHandler.combineRects(rectsBuy2.get(3), rectsBuy2.get(4)), 0);
            other++;

        }
        else
        {
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ss2, RectHandler.combineRects(rectsBuy2.get(2),      rectsBuy2.get(6)),   0);
            Buy2=null;

        }

        if(MainActivity.readBuy(bb.getCode())==0&& bb.isLocked()) {
            canvas.drawRect(Buy3, paintButton);
            canvas.drawRect(Buy3, paintStrokeButton);

            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ss1, Buy3, 0);
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ShopActivity.Price3!=null?ShopActivity.Price3:buyStorePrice3, RectHandler.combineRects(rectsBuy3.get(3),      rectsBuy3.get(4)),   0);
            other++;

        }
        else
        {
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ss2, RectHandler.combineRects(rectsBuy3.get(2),      rectsBuy3.get(6)),   0);
            Buy3=null;

        }

        if(MainActivity.readBuy(s.getCode())==0 && s.isLocked()) {
            canvas.drawRect(Buy4, paintButton);
            canvas.drawRect(Buy4, paintStrokeButton);
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas,ss1, Buy4, 0);
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas,ShopActivity.Price4!=null?ShopActivity.Price4: buyStorePrice4, RectHandler.combineRects(rectsBuy4.get(3), rectsBuy4.get(4)), 0);
            other++;

        }
        else
        {
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ss2, RectHandler.combineRects(rectsBuy4.get(2),      rectsBuy4.get(6)),   0);
            Buy4=null;

        }

        if(MainActivity.readBuy(ll.getCode())==0 && ll.isLocked()) {
            canvas.drawRect(Buy5, paintButton);
            canvas.drawRect(Buy5, paintStrokeButton);
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ss1, Buy5, 0);
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ShopActivity.Price5!=null?ShopActivity.Price5:buyStorePrice5, RectHandler.combineRects(rectsBuy5.get(3), rectsBuy5.get(4)), 0);
            other++;

        }
        else
        {
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ss2, RectHandler.combineRects(rectsBuy5.get(2),      rectsBuy5.get(6)),   0);
            Buy5=null;

        }

        if(MainActivity.readBuy(97)==0 && other>0) {
            canvas.drawRect(Buy6, paintButton);
            canvas.drawRect(Buy6, paintStrokeButton);
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, ss1, Buy6, 0);
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas,ShopActivity.Price6!=null?ShopActivity.Price6: buyStorePrice6, RectHandler.combineRects(rectsBuy6.get(3),      rectsBuy6.get(4)),   0);

        }
        else
        {
            FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT, canvas, "all flyer unlocked", RectHandler.combineRects(rectsBuy6.get(2),      rectsBuy6.get(6)),   0);
            Buy6=null;

        }







        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.paket);


        canvas.drawBitmap(icon,rectsBuy6.get(1).left-rectsBuy6.get(1).width()/4,rectsBuy6.get(1).top+rectsBuy6.get(1).height()/4,null);

        ClickClose=main.get(7);
        canvas.drawRect(ClickClose, paintButton);
        canvas.drawRect(ClickClose, paintStrokeButton);
        FontRectPainter.drawtextOnCanvasCenter(GameConst.FONT,canvas,"Close the shop",ClickClose,0);

    }

    public Rect getCombinedRect(ArrayList<Rect> rects,int x,int y)
    {
        Rect result= RectHandler.combineRects(rects.get(x),rects.get(y));

        return result=new Rect(result.left,result.top+result.height()/8,result.right-result.width()/8,result.bottom-result.height()/8);
    }

    public Rect getBounds()
    {
        return new Rect(0,0,getWidth(),getHeight());



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            if(ClickClose.contains((int)event.getX(),(int)event.getY()))
            {
                ((Activity)getContext()).finish();

            }
            else  if(Buy6.contains((int)event.getX(),(int)event.getY()))
            {
                ((ShopActivity)getContext()).buyPack();

            }
            else  if(Buy1.contains((int)event.getX(),(int)event.getY()))
            {
                ((ShopActivity)getContext()).buyChar1();

            }
            else  if(Buy2.contains((int) event.getX(), (int) event.getY()))
            {
                ((ShopActivity)getContext()).buyChar2();

            }
            else  if(Buy3.contains((int)event.getX(),(int)event.getY()))
            {
                ((ShopActivity)getContext()).buyChar3();

            }
            else  if(Buy4.contains((int)event.getX(),(int)event.getY()))
            {
                ((ShopActivity)getContext()).buyChar4();

            }
            else  if(Buy5.contains((int)event.getX(),(int)event.getY()))
            {
                ((ShopActivity)getContext()).buyChar5();

            }
        }
        return true;
    }
    }
