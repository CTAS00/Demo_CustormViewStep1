package mycustormview.one.step3_circle;

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
 * Created by koudai_nick on 2017/12/12.
 * 功能: 圆环类似于进度条的效果
 * 分析:有两个颜色,本来的颜色和进度的颜色  然后圆弧的宽度  最后是速度控制
 * https://www.cnblogs.com/angeldevil/p/3479431.html#two.one
 *
 */

public class CustormView_circle extends View {

    /**
     * 第一圈的颜色
     */
    private int mFirstColor;
    /**
     * 第二圈的颜色
     */
    private int mSecondColor;
    /**
     * 圈的宽度
     */
    private int mCircleWidth;
    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 速度
     */
    private int mSpeed;


    private int mProgress;  // 当前的进度


    private boolean isNext ; // 是否进行下一步
    public CustormView_circle(Context context) {
        this(context,null);
    }

    public CustormView_circle(Context context, AttributeSet attrs) {
        //第三个参数是theme中的一个attribute,是指向style的一个引用。当在xml布局中或者style中没有为这个
        //属性值的时候，就会从这个theme中的style引用中去搜索该值。
        this(context, attrs,0);
    }

    public CustormView_circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 这边的第4个参数是在defStyleAttr为0或者说在theme中没有为defStyleAttr属性赋值时起作用。
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

        // 开启一个线程用来控制进度的更新
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    mProgress++;
                    Log.e("CTAS","当前的进度 = "+ mProgress);
                    if(mProgress == 360){
                        mProgress = 0;
                        if (!isNext)
                            isNext = true;
                        else
                            isNext = false;
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
    public void draw(Canvas canvas) {
        // 绘制最外层的圆弧
        int center = getWidth()/2;
        int radius = center - mCircleWidth/2;
        // 设置画笔的属性
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleWidth);
        // 根据进度画圆弧
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius); // 用于定义的圆弧的形状和大小的界限
        if(!isNext){
            mPaint.setColor(Color.BLACK);
            // 画出圆环  底部显示的圆环
            canvas.drawCircle(center,center,radius,mPaint);
            mPaint.setColor(Color.RED); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        } else {
            mPaint.setColor(Color.RED);
            // 画出圆环  底部显示的圆环
            canvas.drawCircle(center,center,radius,mPaint);
            mPaint.setColor(Color.BLACK); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }
    }
}
