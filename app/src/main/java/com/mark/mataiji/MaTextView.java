package com.mark.mataiji;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: Mark.
 * Email: mark.ma@elico-crop.com.
 * Created time: 16-8-22.
 * Describe:
 */
public class MaTextView extends View {

    public MaTextView(Context context) {
        super(context);
    }

    public MaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MaTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MaTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    public void init() {
        initPaint();
    }

    private Paint mPaint;
    private int mWidth;
    private int mHeigh;
    private Path path0 = new Path();
    private Path path1 = new Path();

    public void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAlpha(255);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeigh = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeigh / 2);
        //画大背景颜色
        mPaint.setColor(0xffffff00);
        path0.addRect(-400, -400, 400, 400, Path.Direction.CW);
        canvas.drawPath(path0, mPaint);

        mPaint.setColor(0xffffffff);
        path0.rewind();
        path0.addCircle(0, 0, 200, Path.Direction.CW);
        canvas.drawPath(path0, mPaint);

        mPaint.setColor(0xff000000);
        path1.addCircle(0, 0, 200, Path.Direction.CW);

        path0.rewind();
        path0.addRect(0, -200, 200, 200, Path.Direction.CW);
        path1.op(path0, Path.Op.DIFFERENCE);

        path0.rewind();
        path0.addCircle(0, -100, 100, Path.Direction.CW);
        path1.op(path0, Path.Op.UNION);

        path0.rewind();
        path0.addCircle(0, 100, 100, Path.Direction.CW);
        path1.op(path0, Path.Op.DIFFERENCE);
        canvas.drawPath(path1, mPaint);

        //画黑色小圆
        path0.rewind();
        path0.addCircle(0, 100, 50, Path.Direction.CW);
        mPaint.setColor(0xff000000);
        canvas.drawPath(path0, mPaint);

        //画白色小圆
        path0.rewind();
        path0.addCircle(0, -100, 50, Path.Direction.CW);
        mPaint.setColor(0xffffffff);
        canvas.drawPath(path0, mPaint);
    }
}
