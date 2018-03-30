package mycustormview.one.step2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.ct.demo_custormviewstep1.R;

/**
 * Created by koudai_nick on 2017/12/11.
 *
 * 功能:最外层是一个矩形框,然后上面是一张图片,下面是一段文字
 *
 * 总结:图片和文字都是由一个个矩形去实现的。
 * 理解drawBitmap的作用:
 * 四个参数:
         Bitmap bitmap：要绘制的位图对象
         Rect src： 是对图片进行裁截，若是空null则显示整个图片
         RectF dst：是图片在Canvas画布中显示的区域
         Paint paint：画笔，这个不用多说
 第二个参数是对图形进行裁剪,第三个参数是让图片显示的位置
 *
 *
 */

public class CustormImageView extends View {


    private Bitmap mImage;
    private int mImageScale;
    private String mTitle;
    private int mTextColor;
    private int mTextSize;


    // 文本绘制所要的距离
    private Rect mBounds;

    // 绘制图片所要的距离
    private Rect rect;


    // 绘制的画笔
    private Paint mPaint;

    // 绘制的宽和高
    private int mWidth,mHeight;

    // 图片的格式问题
    private static final int   IMAGE_SCALE_FITXY = 0;

    private static final String TAG = CustormImageView.class.getSimpleName();


    public CustormImageView(Context context) {
        this(context, null);
    }

    public CustormImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustormImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取到AttributeSet的缘由

        int count = attrs.getAttributeCount();
        for(int i=0;i<count;i++){
            String attrName = attrs.getAttributeName(i);
            String attrVal = attrs.getAttributeValue(i);
            Log.e(TAG, "attrName = " + attrName + " , attrVal = " + attrVal);
        }



        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);
        int n = a.getIndexCount();
        Log.e(TAG, "当前自定义属性的个数 = " +n );
        Log.e(TAG, "当前所有属性的个数 = " +attrs.getAttributeCount());
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            // 当前属性的索引
            Log.e(TAG, "当前自定义属性 = " +attr );
            switch (attr) {
                case R.styleable.CustomImageView_image:
                    mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomImageView_imageScaleType:
                    mImageScale = a.getInt(attr, 0);
                    break;
                case R.styleable.CustomImageView_titleText:
                    mTitle = a.getString(attr);
                    break;
                case R.styleable.CustomImageView_titleTextColor:
                    mTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomImageView_titleTextSize:
                    mTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                            16, getResources().getDisplayMetrics()));
                    break;

            }
        }
        a.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mBounds = new Rect();
        rect = new Rect();
        mPaint.getTextBounds(mTitle,0,mTitle.length(),mBounds);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.e("CTAS","widthSize = " + widthSize);
        if(widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize;
        } else {
            int desireByImg = getPaddingLeft()+getPaddingRight() + mImage.getWidth();
            int desireByText = getPaddingLeft() + getPaddingRight() + mBounds.width();
            if(widthMode == MeasureSpec.AT_MOST){ // wrap_content
                int desire = Math.max(desireByImg,desireByText);
                // 要是设置的超过了屏幕的话 就可以这么处理了
                mWidth = Math.min(widthSize,desire);
            }
        }

        if(heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        } else {
            int desireByImg = getPaddingLeft()+getPaddingRight() + mImage.getHeight();
            int desireByText = getPaddingLeft() + getPaddingRight() + mBounds.height();
            if(heightMode == MeasureSpec.AT_MOST){
                int desire = Math.max(desireByImg,desireByText);
                mHeight = Math.min(heightSize,desire);
            }
        }
        setMeasuredDimension(mWidth,mHeight);
    }
    // 代理主要控制的访问
    @Override
    protected void onDraw(Canvas canvas) {
        //现在最外层画一个框
        /**
         * 边框
         */
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);

        // 先画文字  在图片的下面
        /**
         * 当前设置的宽度小于字体需要的宽度,将字体改为xxx...
         */
        mPaint.setColor(Color.RED);
        if (mBounds.width() > mWidth)
        {
            TextPaint paint = new TextPaint(mPaint); // 装饰设计模式
            String msg = TextUtils.ellipsize(mTitle, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);

        } else
        {
            //正常情况，将字体居中
            canvas.drawText(mTitle, mWidth / 2 - mBounds.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
        }
        // 然后用剩下的区域绘制图片
        rect.left = getPaddingLeft();
        rect.top = getPaddingTop();
        rect.right = mWidth -getPaddingRight();
        rect.bottom = mHeight - getPaddingBottom()-mBounds.height();

        if(mImageScale ==IMAGE_SCALE_FITXY){
            // 充斥满
            canvas.drawBitmap(mImage,null,rect,mPaint);
        } else {
            // 让图形居中显示
            rect.left =mWidth/2 - mImage.getWidth()/2;
            rect.top = mHeight/2 - mImage.getHeight()/2;

            rect.right = mWidth/2 + mImage.getWidth()/2;
            rect.bottom = mHeight/2 + mImage.getHeight()/2;
            canvas.drawBitmap(mImage,null,rect,mPaint);
        }

//        super.onDraw(canvas);
    }
}
