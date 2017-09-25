package com.example.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hongjinde on 2017/9/20.
 */

public class MyView2 extends View {
    private Path mPath;
    private Path mPath2;
    private Paint mPaint;
    private float mGDYCenterX=300,mGDYCenterY=300;
    private float mMyCenterX=300,mMyCenterY=300;
    private float mRadius=100;

    private boolean mHasTouchMy=false;

    private MotionEvent mEventNow;


    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();

        mPath=new Path();
        mPath2=new Path();
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(mGDYCenterX,mGDYCenterY,mRadius,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(mMyCenterX,mMyCenterY,mRadius,mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.reset();
        mPath.reset();;
        mPath2.reset();

        mPath.lineTo(mMyCenterX,mMyCenterY);
        mPath.moveTo(mGDYCenterX,mGDYCenterY);
        canvas.drawPath(mPath, mPaint);


        double degress1=Math.toRadians(caculatorDegrees()-90);

        float startx= (float) (mGDYCenterX+mRadius*Math.cos(degress1));
        float starty= (float) (mGDYCenterY+mRadius*Math.sin(degress1));

        float endx= (float) (mMyCenterX+mRadius*Math.cos(degress1));
        float endy= (float) (mMyCenterY+mRadius*Math.sin(degress1));

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
//        canvas.drawCircle(startx,starty,20,mPaint);
//        canvas.drawCircle(endx,endy,20,mPaint);

        double degress2=Math.toRadians(caculatorDegrees()+90);

        float startx2= (float) (mGDYCenterX+mRadius*Math.cos(degress2));
        float starty2= (float) (mGDYCenterY+mRadius*Math.sin(degress2));

        float endx2= (float) (mMyCenterX+mRadius*Math.cos(degress2));
        float endy2= (float) (mMyCenterY+mRadius*Math.sin(degress2));

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
//        canvas.drawCircle(startx2,starty2,20,mPaint);
//        canvas.drawCircle(endx2,endy2,20,mPaint);

        float controllX=mGDYCenterX+(mMyCenterX-mGDYCenterX)/2;
        float controllY=mGDYCenterY+(mMyCenterY-mGDYCenterY)/2;

//        canvas.drawCircle(controllX,controllY,20,mPaint);

        mPath.moveTo(startx,starty);
        mPath.quadTo(controllX,controllY,endx,endy);

        mPath.lineTo(mMyCenterX,mMyCenterY);
        mPath.lineTo(mGDYCenterX,mGDYCenterY);
        mPath.close();

        mPath2.moveTo(startx2,starty2);
        mPath2.quadTo(controllX,controllY,endx2,endy2);

        mPath2.lineTo(mMyCenterX,mMyCenterY);
        mPath2.lineTo(mGDYCenterX,mGDYCenterY);
        mPath2.close();

        canvas.drawPath(mPath,mPaint);
        canvas.drawPath(mPath2,mPaint);



    }

    public boolean isInMy(){
        if(mEventNow!=null){
            float xNow=mEventNow.getX();
            float yNow=mEventNow.getY();

            return Math.sqrt((xNow-mGDYCenterX)*(xNow-mGDYCenterX)+(yNow-mGDYCenterY)*(yNow-mGDYCenterY))<=mRadius;
        }
        return  false;
    };

    //计算旋转角度
    private float caculatorDegrees() {
        //5个特殊点
        if (mGDYCenterY==mMyCenterY){
            if (mGDYCenterX>mMyCenterY){
                return 180;
            }else {
                return 0;
            }
        }else if (mGDYCenterX==mMyCenterX){
            if (mGDYCenterY>mMyCenterY){
                return 270;
            }
            if (mGDYCenterY<mMyCenterY){
                return 90;
            }
        }
        //其它点
        double tanValue=Math.abs((mGDYCenterY-mMyCenterY)/(mGDYCenterX-mMyCenterX));
        float degrees= (float) Math.toDegrees( Math.atan(tanValue));
        if (mGDYCenterY<mMyCenterY){
            if (mGDYCenterX<mMyCenterX){
                return degrees;
            }
            if (mGDYCenterX>mMyCenterX){
                return 180-degrees;
            }
        }
        if (mGDYCenterY>mMyCenterY){
            if (mGDYCenterX<mMyCenterX){
                return -degrees;
            }
            if (mGDYCenterX>mMyCenterX){
                return 180+degrees;
            }
        }
        return 0;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
         super.onTouchEvent(event);
        mEventNow=event;

        if(mHasTouchMy){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    mMyCenterX=event.getX();
                    mMyCenterY=event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                reset();
            }
            invalidate();
        }else{
            boolean inMy = isInMy();
            if (inMy) {
                mHasTouchMy=true;
            }
        }
        return true;
    }

    private void reset() {
        mHasTouchMy=false;
        mMyCenterY=mGDYCenterY;
        mMyCenterX=mGDYCenterX;
        invalidate();
    }
}
