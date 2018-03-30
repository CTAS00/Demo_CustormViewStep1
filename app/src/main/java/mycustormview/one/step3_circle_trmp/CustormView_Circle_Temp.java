package mycustormview.one.step3_circle_trmp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.ct.demo_custormviewstep1.R;

/**
 * Created by koudai_nick on 2017/12/13.
 */

public class CustormView_Circle_Temp extends View {

    // 自定义属性
    private int mFirstColor;  // 第一圈的颜色
    private int mSecondColor;  // 上面覆盖的颜色

    private int mCircleWidth;  //圆弧的宽度
    private int mSpeed;  // 画圆弧的速度


    private Paint mPaint;


    private boolean isNext; // 判断是第一圈还是第二圈

    private int mProgress;//当前的进度
    public CustormView_Circle_Temp(Context context) {
        this(context,null);
    }

    public CustormView_Circle_Temp(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustormView_Circle_Temp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.CustomProgressBar_firstColor:
                    mFirstColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CustomProgressBar_secondColor:
                    mSecondColor = a.getColor(attr, Color.RED);
                    break;
                case R.styleable.CustomProgressBar_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomProgressBar_speed:
                    mSpeed = a.getInt(attr, 20);// 默认20
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();

        // 开启一个线程去执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    mProgress ++;
                    Log.e("CTAS","当前的进度 = "+ mProgress);
                    if(mProgress == 360){
                        mProgress = 0;
                        if(isNext){
                            isNext = false;
                        } else {
                            isNext = true;
                        }
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        int center = getWidth()/2;
        int radius = center - mCircleWidth/2;
//        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setAntiAlias(true);

        // 使用float 精度更高一点
        //  public RectF(float left, float top, float right, float bottom) {
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius); // 用于定义的圆弧的形状和大小的

        if(!isNext){
            mPaint.setColor(mFirstColor);
            canvas.drawCircle(center,center,radius,mPaint);
            // 绘制圆弧
            mPaint.setColor(mSecondColor);
            canvas.drawArc(rectF,-90,mProgress,false,mPaint);
        } else {
            mPaint.setColor(mSecondColor);
            canvas.drawCircle(center,center,radius,mPaint);
            // 绘制圆弧
            mPaint.setColor(mFirstColor);
            canvas.drawArc(rectF,-90,mProgress,false,mPaint);

        }



    }
}
