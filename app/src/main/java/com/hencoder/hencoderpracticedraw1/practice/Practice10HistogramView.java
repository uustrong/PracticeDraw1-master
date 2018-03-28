package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {
    private Paint mPaint;
    private float startX = 50;
    private float startY = 250;
    private float endX = 500;
    private float endY = 15;
    private float[] startXFloat = {60, 120, 180, 240, 300, 360, 420};
    private float[] endXFloat = {110, 170, 230, 290, 350, 410, 470};
    private float[] startYFloat = {445, 240, 240, 150, 100, 50, 175};
    private String[] text = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);
        //画X轴
        canvas.drawLine(startX, startY, endX, startY, mPaint);
        //画Y轴
        canvas.drawLine(startX, startY, startX, endY, mPaint);

        //画长条矩形
        mPaint.setColor(Color.parseColor("#72b915"));
        for (int i = 0; i < startXFloat.length; i++) {
            canvas.drawRect(startXFloat[i], startYFloat[i], endXFloat[i], 250, mPaint);
        }

        //画文字
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(15);
        for (int i = 0; i < text.length; i++) {
            canvas.drawText(text[i], endXFloat[i] - 35, 260, mPaint);
        }

        //画直方图
        mPaint.setTextSize(45);
        canvas.drawText("直方图",250,350,mPaint);

    }
}
