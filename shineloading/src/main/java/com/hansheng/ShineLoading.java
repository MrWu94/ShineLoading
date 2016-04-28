package com.hansheng;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hansheng on 2016/4/27.
 */
public class ShineLoading extends View {

    private Paint paint;
    private Path path;
    private static int cw = 400, ch = 300;
    private int x = (cw / 2) + 5;
    private int y = (ch / 2) + 22;
    private int radius = 90;
    private int speed = 2;
    private int rotation = 0;
    private int angleStart = 270;
    private int angleEnd = 90;
    private int hue = 220;
    private int thickness = 18;
    private int blur = 25;

    private int particles[];//亮点数组
    private int particleMax = 100;//亮点个数


    private void updateCircle() {
        if (rotation < 360) {
            rotation += speed;
        } else {
            rotation = 0;
        }
    }

    private void clear(Canvas canvas) {


    }


    public ShineLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    /*
    * 获取a到b之间的整数值
    * */
    private float rand(int a, int b) {
        return (float) ((Math.random() * (b - a + 1)) + a);
    }

    /*
    * 角度变弧度
    * */
    private int dToR(int degress) {
        return (int) (degress * (Math.PI / 180));
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        renderCircle(canvas);
        renderCircleBorder(canvas);
    }

    private void renderCircle(Canvas canvas) {
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        //绘制弧线区域
        RectF oval = new RectF();                     //RectF对象
        oval.left = 20;                              //左边
        oval.top = 20;                               //上边
        oval.right = 400;                             //右边
        oval.bottom = 400;                            //下边
        paint.setShader(new LinearGradient(0, 0, 400, 400, Color.BLUE, Color.YELLOW, Shader.TileMode.REPEAT));
        canvas.translate(x, y);
        canvas.rotate(rotation);
        canvas.drawArc(oval, //弧线所使用的矩形区域大小
                0,//开始的角度
                160, //扫过的角度
                false,//是否使用中心
                paint);
        canvas.restore();
        invalidate();
    }

    private void renderCircleBorder(Canvas canvas){
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        //绘制弧线区域
        RectF oval = new RectF();                     //RectF对象
        oval.left = 20;                              //左边
        oval.top = 20;                               //上边
        oval.right = 400;                             //右边
        oval.bottom = 400;                            //下边
        paint.setShader(new LinearGradient(0,0,400,400,Color.BLUE,Color.YELLOW, Shader.TileMode.REPEAT));
        canvas.translate(x,y);
        canvas.rotate(rotation);
        canvas.drawArc(oval,0,160,false,paint);
        canvas.restore();
        invalidate();
    }

    private void renderCircleFlare(Canvas canvas){
        //绘制弧线区域
        RectF oval = new RectF();                     //RectF对象
        oval.left = 20;                              //左边
        oval.top = 20;                               //上边
        oval.right = 400;                             //右边
        oval.bottom = 400;                            //下边
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(rotation+185);
        canvas.drawArc(oval,0,160,false,paint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
