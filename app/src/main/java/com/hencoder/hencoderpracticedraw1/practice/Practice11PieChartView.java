package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {
    private Paint mPaint;
    private Path mPath;
    RectF rectF = new RectF(100, 50, 500, 450);
    RectF maxRectF = new RectF(100 - 10, 50 - 10, 500 - 10, 450 - 10);
    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //先画扇形

        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(rectF, -69, 45, true, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF, -22, 15, true, mPaint);
        mPaint.setColor(Color.GRAY);
        canvas.drawArc(rectF, -5, 15, true, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawArc(rectF, 12, 45, true, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF, 59, 100, true, mPaint);


//        paint.setColor(Color.RED);
//        canvas.drawArc(rectF, 175, 140, true, paint);

        /**
         * 可以先定死最大扇形放在左上角，中心线在45度方向上。
         */

        mPaint.setColor(Color.RED);
        canvas.drawArc(maxRectF, -201, 132, true, mPaint);


        drawLineAndTxt(canvas);
    }
    private int centerX, centerY, radius;
    private static final int BOUND_LINE_LENGTH = 40;

    private void drawLineAndTxt(Canvas canvas) {
        updateDimen(canvas, rectF);
        canvas.translate(centerX, centerY);

        drawLine(canvas, -69, 45, "KitKat", Color.WHITE, false);
        drawLine(canvas, -22, 15, "Froyo", Color.WHITE, false);
        drawLine(canvas, -5, 15, "mallow", Color.WHITE, false);
        drawLine(canvas, 12, 45, "Ice Cream", Color.WHITE, false);
        drawLine(canvas, 59, 100, "Bean", Color.WHITE, false);

        drawLine(canvas, 159, 132, "G", Color.WHITE, true);


    }

    private void updateDimen(Canvas canvas, RectF rect) {
        centerX = (int) ((rect.right - rect.left) / 2 + rect.left);
        centerY = (int) ((rect.bottom - rect.top) / 2 + rect.top);
        radius = (int) ((rect.right - rect.left) / 2);
    }
    private void drawLine(Canvas canvas, int start, int angles, String txt, int color, boolean isLast) {
        mPaint.setColor(color);
        mPaint.setStrokeWidth(2);
        float stopX, stopY, circleX, circleY;


        stopX = (float) ((radius + BOUND_LINE_LENGTH) * Math.cos((2 * start + angles) / 2 * Math.PI / 180));
        stopY = (float) ((radius + BOUND_LINE_LENGTH) * Math.sin((2 * start + angles) / 2 * Math.PI / 180));

        circleX = (float) ((radius) * Math.cos((2 * start + angles) / 2 * Math.PI / 180));
        circleY = (float) ((radius) * Math.sin((2 * start + angles) / 2 * Math.PI / 180));

        if(isLast) {
            stopX -= 20;
            stopY -= 20;
            circleX -= 20;
            circleY -= 20;
        }

        int OUTER_LINE_LENGTH = 100;

        if(Math.abs(start) < 45 || Math.abs(start - 180) < 45 || Math.abs(start - 360) < 45) {
            OUTER_LINE_LENGTH -= 50;
        }

        mPaint.setStyle(Paint.Style.STROKE);
        mPath.moveTo(circleX, circleY);
        mPath.lineTo(stopX, stopY);
        if(stopX > 0) {
            mPath.rLineTo(OUTER_LINE_LENGTH, 0);
            canvas.drawPath(mPath, mPaint);
        } else {
            mPath.rLineTo(-OUTER_LINE_LENGTH, 0);
            canvas.drawPath(mPath, mPaint);
        }
        mPaint.setTextSize(30);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
        Rect rect = new Rect();
        mPaint.getTextBounds(txt, 0, txt.length(), rect);
        // 绘制字体
        if(stopX > 0) {
            mPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(txt, stopX + OUTER_LINE_LENGTH + 10, stopY - (rect.top - rect.bottom) / 2, mPaint);
        } else {
            mPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(txt, stopX - OUTER_LINE_LENGTH - 10, stopY - (rect.top - rect.bottom) / 2, mPaint);
        }


    }
}
