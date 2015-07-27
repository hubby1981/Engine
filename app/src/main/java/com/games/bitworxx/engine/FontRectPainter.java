package com.games.bitworxx.engine;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.games.bitworxx.engine.characters.GameConst;

/**
 * Created by WEIS on 20.07.2015.
 */
public class FontRectPainter {



    public static void drawtextOnCanvas(Paint font,Canvas canvas,String text,Rect display,int offset)
    {

        float size1 =font.measureText(text);
        size1=display.width()/size1;
        size1=size1-(size1/8);
        font.setTextSize(font.getTextSize() * size1);

        if(offset==0) {
            canvas.drawText(text, display.left + 10, display.centerY() + font.getTextSize() / 3, font);
        }
        else
        {
            canvas.drawText(text, display.left + 10, display.top+(GameConst.FONT.getTextSize()*offset), font);
        }
    }

    public static void drawtextOnCanvasRight(Paint font,Canvas canvas,String text,Rect display,int offset)
    {

        float size1 =font.measureText(text);
        size1=display.width()/size1;
        size1=size1-(size1/8);
        font.setTextSize(font.getTextSize() * size1);

        if(offset==0) {
            canvas.drawText(text,( display.right- 10)-size1, display.centerY() + font.getTextSize() / 3, font);
        }
        else
        {
            canvas.drawText(text,( display.right- 10)-size1, display.top+(GameConst.FONT.getTextSize()*offset), font);
        }
    }

    public static void drawtextOnCanvas(Paint font,Canvas canvas,String text,Rect display,int offset,int padding)
    {

        display=new Rect(display.left+padding,display.top+padding,display.right-padding,display.bottom-padding);
        float size1 =font.measureText(text);
        size1=display.width()/size1;
        size1=size1-(size1/8);
        font.setTextSize(font.getTextSize() * size1);

        if(offset==0) {
            canvas.drawText(text, display.left + 10, display.centerY() + font.getTextSize() / 3, font);
        }
        else
        {
            canvas.drawText(text, display.left + 10, display.top+(GameConst.FONT.getTextSize()*offset), font);
        }
    }

    public static void drawtextOnCanvasCenter(Paint font,Canvas canvas,String text,Rect display,int offset)
    {

        float size1 =font.measureText(text);
        size1=display.width()/size1;
        size1=size1-(size1/8);
        font.setTextSize(font.getTextSize() * size1);

        if(offset==0) {
            canvas.drawText(text, display.centerX()-font.measureText(text)/2, display.centerY() + font.getTextSize() / 3, font);
        }
        else
        {
            canvas.drawText(text, display.centerX()-font.measureText(text)/2, display.top+(GameConst.FONT.getTextSize()*offset), font);
        }
    }

    public static void drawtextOnCanvasCenter(Paint font,Canvas canvas,String text,Rect display,int offset,int padding)
    {
        display=new Rect(display.left+padding,display.top+padding,display.right-padding,display.bottom-padding);
        float size1 =font.measureText(text);
        size1=display.width()/size1;
        size1=size1-(size1/8);
        font.setTextSize(font.getTextSize() * size1);


        if(offset==0) {
            canvas.drawText(text, display.centerX()-font.measureText(text)/2, display.centerY() + font.getTextSize() / 3, font);
        }
        else
        {
            canvas.drawText(text, display.centerX()-font.measureText(text)/2, display.top+(GameConst.FONT.getTextSize()*offset), font);
        }
    }

    public static void drawtextOnCanvasSimple(Paint font,Canvas canvas,String text,Rect display)
    {



        canvas.drawText(text, display.left + 10, display.centerY() + font.getTextSize() / 3, font);
    }

    public static void drawtextOnCanvasSimpleRight(Paint font,Canvas canvas,String text,Rect display)
    {

        float size = font.measureText(text);

        canvas.drawText(text, (display.right-10)-size, display.centerY() + font.getTextSize() / 3, font);
    }

    public static void drawtextOnCanvasSimple(Paint font,Canvas canvas,String text,Rect display,int offset)
    {



        canvas.drawText(text,display.left+10, display.top+(display.height()/2*offset), font);
    }


    public static void drawtextOnCanvasSimpleCenter(Paint font,Canvas canvas,String text,Rect display,int offset)
    {


        float size = font.measureText(text);
        canvas.drawText(text,display.centerX()-size/2, display.top+(display.height()/2*offset), font);
    }
}
