package com.hansheng.indicator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 2016/4/28.
 */
public class BallBeatIndicator extends BaseIndicatorController {

    public static final float SCALE=1.0f;

    public static final int ALPHA=255;

    private float[] scaleFloats=new float[]{SCALE,
            SCALE,
            SCALE,SCALE,SCALE};

    int[] alphas=new int[]{ALPHA,
            ALPHA,
            ALPHA,ALPHA,ALPHA};

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing=8;
        float radius=(getWidth()-circleSpacing*2)/6;
        float x = getWidth()/ 2-(radius*2+circleSpacing);
        float y=getHeight() / 2;
        for (int i = 0; i < 5; i++) {
            canvas.save();
            float translateX=x+(radius*2)*i+circleSpacing*i;
            canvas.translate(translateX, y);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            paint.setAlpha(alphas[i]);
            canvas.drawCircle(0, 0, radius, paint);
            canvas.restore();
        }
    }

    @Override
    public List<Animator> createAnimation() {
        List<Animator> animators=new ArrayList<>();
        int[] delays=new int[]{300,0,300,0,300};
        for (int i = 0; i < 5; i++) {
            final int index=i;
            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.75f,1);//缩放动画
            scaleAnim.setDuration(700);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            scaleAnim.start();
            ValueAnimator alphaAnim=ValueAnimator.ofInt(255,51,255);
            alphaAnim.setDuration(700);//动画时长
            alphaAnim.setRepeatCount(-1);//透明时长
            alphaAnim.setStartDelay(delays[i]);
            alphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    alphas[index] = (int) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            alphaAnim.start();//开始动画
            animators.add(scaleAnim);
            animators.add(alphaAnim);
        }
        return animators;
    }

}