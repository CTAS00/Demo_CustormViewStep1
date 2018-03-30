package mycustormview.one.step1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.ct.demo_custormviewstep1.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by koudai_nick on 2018/2/1.
 */

public class CustormTitleTextView extends View implements View.OnClickListener {
    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;


    private Paint mPaint;
    private Rect mBounds;


    public CustormTitleTextView(Context context) {
        super(context);
    }

    public CustormTitleTextView(Context context, AttributeSet attrs) {
        // 当为0的时候 就不会去找这个style
        this(context, attrs,0);
    }

    public CustormTitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView,defStyleAttr,0);
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
        a.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        // 获得字体的大小属性
        mBounds = new Rect();
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBounds);

        this.setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("CTAS","onMeasure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        Log.e("CTAS","onDraw");
        mPaint.setColor(Color.RED);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawText(mTitleText,getMeasuredWidth()/2 - mBounds.width()/2,getMeasuredHeight()/2 + mBounds.height()/2,mPaint);
    }

    // 生成4个随机数  并且不重复
    private String makeRandom(){
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while(set.size()<4){
            int number = random.nextInt(10); // 0-10
            set.add(number);
        }
        StringBuilder sb = new StringBuilder();
        for (Integer integer : set) {
            sb.append(integer+"");
        }
        return sb.toString();
    }

    @Override
    public void onClick(View view) {
        mTitleText = makeRandom();
        invalidate();
    }
}
