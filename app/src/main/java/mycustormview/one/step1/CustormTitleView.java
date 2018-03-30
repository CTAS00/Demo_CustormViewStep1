package mycustormview.one.step1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.ct.demo_custormviewstep1.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by koudai_nick on 2017/12/11.
 * 功能：随机生成一个4位数的view
 * 总结:
 * 1.理解绘制文本时候的基线
 * 2.绘制文本是需要知道的要素:
 *   1.文本的大小，用来确定基线的位置
 *   2.文本的文字，用来确定文本的大小。
 *   3.资源需要及时进行回收 即:recycle
 */

public class CustormTitleView extends View implements View.OnClickListener {

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;

    // 绘制文本的范围
    private Rect mBounds;
    private Paint mPaint;


    public CustormTitleView(Context context) {
        this(context,null);
    }

    public CustormTitleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustormTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.CustomTitleView,defStyleAttr,0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.CustomTitleView_titleOneText:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleOneTextColor:
                    // 默认颜色设置为黑色
                    mTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_titleOneTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;

            }

        }
        // 资源的回收
        a.recycle();

        // 获得绘制文本的宽和高
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBounds = new Rect();
        // 获得文案的大小
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBounds);
        this.setOnClickListener(this);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width,height;
        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        } else {
            // 内容包裹
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBounds);
            int desired = getPaddingLeft() + mBounds.width() + getPaddingRight();
            width = desired;
        }


        if(heigthMode == MeasureSpec.EXACTLY){
            height = heightSize;
        } else {
            // 内容包裹
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBounds);
            int desired = getPaddingLeft() + mBounds.height() + getPaddingRight();
            height = desired;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        // 绘制背景
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(Color.RED);
        // text ,基线的位置
        canvas.drawText(mTitleText, getMeasuredWidth() / 2 - mBounds.width() / 2, getMeasuredHeight() / 2 + mBounds.height() / 2, mPaint);
    }


    @Override
    public void onClick(View view) {
        mTitleText = randomText();
        // 重新绘制
        postInvalidate();

    }
    // 获得随机数
    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while(set.size()<4){
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuilder sb = new StringBuilder();
        for (Integer integer : set) {
            sb.append(integer);
        }
        return sb.toString();

    }
}
