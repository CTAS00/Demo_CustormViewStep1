package mycustormview.one.step4_volume;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.ct.demo_custormviewstep1.R;

/**
 * Created by koudai_nick on 2017/12/13.
 */

public class CustormVolumeView extends View {


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
     * 当前进度
     */
    private int mCurrentCount = 3;

    /**
     * 中间的图片
     */
    private Bitmap mImage;
    /**
     * 每个块块间的间隙
     */
    private int mSplitSize;
    /**
     * 个数
     */
    private int mCount;

    private Rect mRect;
    public CustormVolumeView(Context context) {
        this(context,null);
    }

    public CustormVolumeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustormVolumeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取到自定义属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomVolumControlBar, defStyleAttr, 0);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.CustomVolumControlBar_volum_firstColor:
                    mFirstColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CustomVolumControlBar_volum_secondColor:
                    mSecondColor = a.getColor(attr, Color.CYAN);
                    break;
                case R.styleable.CustomVolumControlBar_bg:
                    mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomVolumControlBar_volum_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomVolumControlBar_dotCount:
                    mCount = a.getInt(attr, 20);// 默认20
                    break;
                case R.styleable.CustomVolumControlBar_splitSize:
                    mSplitSize = a.getInt(attr, 20);
                    break;
            }
        }
        a.recycle();

        mPaint = new Paint();

        mRect = new Rect();

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        mPaint.setStrokeCap(Paint.Cap.ROUND); // 定义画圆弧的形状为圆头
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        int center = getWidth()/2;
        int radius = center - mCircleWidth/2;
         // 画小长块
        drawoval(canvas,center,radius);
        int realRadius = radius - mCircleWidth/2;// 获取到内部圆的真实半径
        mRect.left =(int) (realRadius - Math.sqrt(2) * 1.0f / 2 * realRadius) + mCircleWidth;
        mRect.top = (int) (realRadius - Math.sqrt(2) * 1.0f / 2 * realRadius) + mCircleWidth;
        mRect.right = (int) (mRect.left + Math.sqrt(2) * realRadius);  ;
        mRect.bottom =  (int) (mRect.left + Math.sqrt(2) * realRadius);
        // 要是图片很小的话
        if(mImage.getWidth() < Math.sqrt(2)* realRadius){
            // 居中显示
            mRect.left =mRect.left +  (int) (Math.sqrt(2) * realRadius / 2 -mImage.getWidth()/2);
            mRect.top =mRect.top +  (int) (Math.sqrt(2) * realRadius / 2 - mImage.getHeight()/2);
            mRect.right =mRect.left+ mImage.getWidth();
            mRect.bottom =mRect.top +  mImage.getWidth();

        }
        canvas.drawBitmap(mImage,null,mRect,mPaint);

    }

    private void drawoval(Canvas canvas, int center, int radius) {
        // 每一个的圆弧长度
        int itemsize = (360 - mCount*mSplitSize)/mCount;

        RectF rectf = new RectF(center - radius,center -radius ,center + radius,center + radius);
        mPaint.setColor(mFirstColor);
        for(int i=0;i<mCount;i++){
            canvas.drawArc(rectf,i*(itemsize+mSplitSize),itemsize,false,mPaint);
        }

        mPaint.setColor(mSecondColor);
        for(int i=0;i<mCurrentCount;i++){
            canvas.drawArc(rectf,i*(itemsize+mSplitSize),itemsize,false,mPaint);
        }

    }

    // 添加触摸监听
private int xDown;
    private int xUp;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                xDown = (int) event.getY();
                break;

            case MotionEvent.ACTION_UP:
                xUp = (int) event.getY();
                if(xUp>xDown){
                    // 下滑
                    down();

                } else {
                    // 上滑
                    up();
                }

                break;


        }


        return true;
    }

    /**
     * 当前数量+1
     */
    public void up()
    {
        mCurrentCount++;
        postInvalidate();
    }

    /**
     * 当前数量-1
     */
    public void down()
    {
        mCurrentCount--;
        postInvalidate();
    }
}
