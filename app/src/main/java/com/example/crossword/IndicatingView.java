package com.example.crossword;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class IndicatingView extends View {
    public static final int NOTEXECUTED = 0;
    public static final int SUCCESS = 1;
    public static final int FAILED = 2;
    public static final int LOADING = 3;
    public static final int LOADING_ANIM = 4;

    int state = NOTEXECUTED;

    public IndicatingView(Context context) { super(context); }

    public IndicatingView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public IndicatingView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        Paint paint;
        switch(state){
            case SUCCESS:
                paint = new Paint();
                paint.setColor(Color.GREEN);
                paint.setStrokeWidth(20f);
                canvas.drawLine(0,0,width/2,height,paint);
                canvas.drawLine(width/2,height,width,height/2,paint);
                break;
            case FAILED:
                paint = new Paint();
                paint.setColor(Color.RED);
                paint.setStrokeWidth(20f);
                canvas.drawLine(0,0,width, height,paint);
                canvas.drawLine(0,height,width,0,paint);
                break;
            case LOADING:
                paint =  new Paint();
                paint.setColor(Color.YELLOW);
                paint.setStrokeWidth(20f);
                canvas.drawLine(0,height,width/2, 0,paint);
                canvas.drawLine(width/2, 0, width, height, paint);
                canvas.drawLine(width,height,0, height,paint);
                break;
            default:
                break;
        }
    }
}
