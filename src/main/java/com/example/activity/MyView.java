package com.example.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hongjinde on 2017/9/19.
 */

public class MyView extends View {
    private Paint mPaint;
    private Path mPath2;
    private Path mPath;
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mPath2= new Path();
        mPath=new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.moveTo(200,400);
        mPath.lineTo(300,200);
        mPath.lineTo(400,400);
        mPath.lineTo(500,200);
        mPath.lineTo(600,400);

        canvas.drawPath(mPath,mPaint    );

        mPaint.setColor(Color.RED);
        mPath2.moveTo(200,400);
//        2阶
//        mPath2.quadTo(200,200,300,100);
//        mPath2.quadTo(400,200,500,100);
//        mPath2.rQuadTo(100,-200,200,0);
//        mPath2.rQuadTo(100,-200,200,0);
//        mPath2.rQuadTo(100,-200,200,0);
//        mPath2.rQuadTo(100,-200,200,0);
//        3阶

        mPath2.cubicTo(300,200,400,400,500,200);
        mPath2.rCubicTo(100,-200,200,0,300,-200);


        canvas.drawPath(mPath2,mPaint);


    }
}
