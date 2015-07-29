package com.games.bitworxx.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.games.bitworxx.engine.characters.GameConst;
import com.games.bitworxx.engine.characters.Spider;

/**
 * Created by WEIS on 29.07.2015.
 */
public class Spiders extends View {
    Spider MySpider=null;

    public Spiders(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Rect bounds = new Rect(0,0,getWidth(),getHeight());

        if(MySpider==null&&getBack()!=null)
        {
            MySpider = new Spider(RandomRange.getRandom(GameConst.MyChar.Size, getRight() - GameConst.MyChar.Size),getBack().getMaceRect(0).BaseRect.bottom+RandomRange.getRandom(getHeight()/20,getHeight()/10),null,false);
            //MySpider.Net=new Rect(MySpider.X,getBack().getMaceRect(0).BaseRect.bottom,MySpider.X+MySpider.Size,getBack().getMaceRect(0).BaseRect.bottom+GameConst.MyChar.Size);;
            MySpider.OWN_DOWN = 40;
        }

            if(MySpider!=null){
        MySpider.Size= GameConst.MyChar.Size;



        MySpider.onDraw(canvas);}
    }

    public void onMove(int move)
    {
        if(MySpider!=null) {
            MySpider.X -= move;
            if (MySpider.X + MySpider.Size < 0 && getBack() != null) {
                MySpider.X = RandomRange.getRandom(getRight() + GameConst.MyChar.Size, (getRight() + getWidth()) - GameConst.MyChar.Size);
                MySpider.Y = getBack().getMaceRect(1).BaseRect.bottom+RandomRange.getRandom(getHeight()/20,getHeight()/10);
                MySpider.OWN_DOWN = 40;
               // MySpider.Net=new Rect(MySpider.X,getBack().getMaceRect(1).BaseRect.bottom,MySpider.X+MySpider.Size,getBack().getMaceRect(0).BaseRect.bottom+GameConst.MyChar.Size);;
            }
        }
    }

    public ScrollBack getBack ()
    {
        return ((GameActivity)getContext()).getBack();
    }
}
