package com.example.scetchersearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
//import android.support.v7.app.AppConpatActivity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class ScetchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scetch);
    }

    public static class MyView extends View{
        Paint paint = new Paint();
        boolean isDrawing = false;
        Path path = new Path();

        MyView(Context context)
        {
            super(context);
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(8);
        }

        @Override
        public void onDraw(Canvas canvas)
        {
            canvas.drawARGB(255,255,255,0);
            canvas.drawPath(path, paint);
        }
        public boolean onTouchEvent(MotionEvent event){
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(event.getX(),event.getY());
                    isDrawing = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(isDrawing){
                        path.lineTo(event.getX(), event.getY());
                        invalidate();
                    }break;
                case MotionEvent.ACTION_UP:
                    isDrawing = false;
                    break;
                default:
                    break;
            }
            return true;
        }

    }

//    protected class Scetch extends View {
//        private Paint mPaints, mFramePaint;
//        private RectF mBigOval;
//        private float mStart, mSweep;
//
//        private static final float SWEEP_INC = 2;
//        private static final float START_INC = 30;
//
//        public Scetch(Context context) {
//            super(context);
//            mPaints = new Paint();
//            mPaints.setAntiAlias(true);
//            mPaints.setStyle(Paint.Style.FILL);
//            mPaints.setColor(0x88ff0000);
//
//            mFramePaint = new Paint();
//            mFramePaint.setAntiAlias(true);
//            mFramePaint.setStyle(Paint.Style.STROKE);
//            mFramePaint.setStrokeWidth(3);
//            mFramePaint.setColor(0x8800ff00);
//
//            mBigOval = new RectF(40, 10, 280, 250);
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            //path
//            Path path = new Path();
//
//            //text
//            Paint paint = new Paint();
//            paint.setAntiAlias(true);
//            paint.setTextSize(100);
//
//            //배경, 그림
//            canvas.drawColor(Color.YELLOW);
//            canvas.drawRect(mBigOval, mFramePaint);
//            canvas.drawArc(mBigOval, mStart, mSweep, false, mPaints);
//
//            //텍스트 그려주기
//            Typeface t = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
//            paint.setTypeface(t);
//            canvas.drawText("DEFUALT 폰트", 10, 500, paint);
//
//            //그림 그려주기
//            mSweep += SWEEP_INC;
//            if (mSweep > 360) {
//                mSweep -= 360;
//                mStart += START_INC;
//                if (mStart >= 360)
//                    mStart -= 360;
//            }
//
//            //path 따라 글 넣어주기
//            paint.setStyle(Paint.Style.STROKE);
//            path.moveTo(20, 700);
//            path.lineTo(300, 1000);
//            path.cubicTo(450, 120, 600, 1300, 900, 800);
//            paint.setColor(Color.BLUE);
//            canvas.drawPath(path, paint);
//            paint.setStyle(Paint.Style.FILL);
//            canvas.drawTextOnPath("tttttttttttttt", path, 0, 0, paint);
//
//            invalidate();
//        }
//
//    }
}