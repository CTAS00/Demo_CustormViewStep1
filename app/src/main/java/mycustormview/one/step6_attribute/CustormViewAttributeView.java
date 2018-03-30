package mycustormview.one.step6_attribute;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ct.demo_custormviewstep1.R;

/**
 * Created by koudai_nick on 2017/12/15.
 * 问:TypedArray是什么？
 * 答: AttributeSet拥有这个自定义控件所有的属性,但是当xml文件中有引用的情况下,只会返回id。
 * TypedArray能帮我们简化这个操作。
 *
 *
 */


public class CustormViewAttributeView  extends View {
    // 数组存放的是属性的地址
    private static final int[] mAttr = {R.attr.textOneAttr, R.attr.textTwoAttr, R.attr.textThreeAttr};
    private static final int ATTR_ANDROID_TEXT = 0;
    private static final int ATTR_TESTATTR = 1;
    private static final int ATTR_TESTATTR_three = 2;
    private static final String TAG = CustormViewAttributeView.class.getSimpleName();

    private Paint mPaint;
    public CustormViewAttributeView(Context context) {
        this(context,null);
    }
    public CustormViewAttributeView(Context context, AttributeSet attrs) {
        // 默认的值 第三位参数 是默认的属性
//        R.attr.CustomViewDefaultStyle
        this(context, attrs, R.attr.CustomViewDefaultStyle);
    }
    // 第三个参数: 这个是当前Theme中的一个attribute，
    // 一级接着一级  层层相扣
    // 会有一个优先级处理  一般就是自己的自定义属性
    public CustormViewAttributeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 参数3 是属性 参数4是style   当xml上没有设置布局 并且defStyleAttr为0 或者说没有为这个属性赋值 所以DefaultThroughStyle可以启作用
        TypedArray ta = context.obtainStyledAttributes(attrs,mAttr,defStyleAttr,R.style.DefaultThroughStyle);
        String text = ta.getString(ATTR_ANDROID_TEXT);
        String  index = ta.getString(ATTR_TESTATTR);
        String thrid = ta.getString(ATTR_TESTATTR_three);
        Log.e(TAG, "text = " + text + " , textIndex = " + index + ",thrid =" + thrid +", allAttribute = " + ta.length());
        Log.e(TAG, "当前的view所有的属性 = " + attrs.getAttributeCount());
        ta.recycle();
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSzie = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        Log.e("CTAS","widthMode =" + widthMode + "======" + "widthSzie = " + widthSzie);
        setMeasuredDimension(widthSzie,heightSize);
    }

    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);

        mPaint.setColor(Color.RED);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
    }
}
