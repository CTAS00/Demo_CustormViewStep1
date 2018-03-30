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

import com.ct.demo_custormviewstep1.R;

/**
 * Created by koudai_nick on 2018/2/2.
 */

public class CustorTextImageView extends View{
    private Bitmap mImage;
    private int mImageScale;

    private String mTitle;
    private int mTextColor;
    private int mTextSize;


    private Paint paint;
    private Rect mBounds;
    private Rect mImageBounds;


    private Rect mRealBounds;


    // 高度 和宽度
    private int mWidth,mHeight;


    // 图片的格式问题
    private static final int   IMAGE_SCALE_FITXY = 0;


    public CustorTextImageView(Context context) {
        super(context);
    }

    public CustorTextImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustorTextImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            // 当前属性的索引
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
        // 获得了字体的矩形
        paint = new Paint();
        mBounds = new Rect();
        mImageBounds = new Rect();
        mRealBounds = new Rect();

        paint.setTextSize(mTextSize);
        paint.getTextBounds(mTitle,0,mTitle.length(),mBounds);

    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        if(widthMode ==MeasureSpec.EXACTLY){
//            mWidth = widthSize;
//        } else {
//            int desireByImg = getPaddingLeft()+getPaddingRight()+mImage.getWidth();
//            int desireByText = getPaddingLeft()+getPaddingRight()+mBounds.width();
//            if(widthMode == MeasureSpec.AT_MOST){
//                int desire = Math.max(desireByImg,desireByText);
//                mWidth = Math.min(widthSize,desire);
//            }
//        }
//
//        // 还有高度的设置
//        if(heightMode == MeasureSpec.EXACTLY){
//            mHeight = heightSize;
//        } else {
//            int desireByImg = getPaddingTop() + getPaddingBottom() + mImage.getHeight();
//            int desireByText = getPaddingTop() + getPaddingBottom() + mBounds.height();
//            if(heightMode == MeasureSpec.AT_MOST){
//                int desire = Math.max(desireByImg,desireByText);
//                mHeight = Math.min(heightSize,desire);
//            }
//
//        }
//        setMeasuredDimension(mWidth,mHeight);
//
//    }

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

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        // 先画一个大框
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0,0,mWidth,mHeight,paint);
        Log.e("CTAS","字体的宽度 = " + mBounds.width() + "==== 容器的宽度 =" +mWidth);

        // 先绘制文字
        if(mBounds.width()>mWidth){
            // 缩小显示
            TextPaint painttt = new TextPaint(paint); // 装饰设计模式
            String msg = TextUtils.ellipsize(mTitle, painttt, (float) mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), painttt);
        } else {
            // 居中显示
            paint.setColor(Color.GREEN);

            canvas.drawText(mTitle, mWidth / 2 - mBounds.width() * 1.0f / 2 , mHeight - getPaddingBottom(), paint);
        }
//        - mBounds.width() * 1.0f / 2
        // 剩下的区域去绘制图片

        // 然后用剩下的区域绘制图片
        mImageBounds.left = getPaddingLeft();
        mImageBounds.top = getPaddingTop();
        mImageBounds.right = mWidth -getPaddingRight();
        mImageBounds.bottom = mHeight - getPaddingBottom()-mBounds.height();
//        mImageBounds.right = mWidth - getPaddingRight();
//        mImageBounds.left = mWidth - getPaddingLeft();
//        mImageBounds.top = mHeight - getPaddingTop();
//        mImageBounds.bottom = mHeight - getPaddingBottom() - mBounds.height();

       if(mImageScale ==IMAGE_SCALE_FITXY){
           // 充斥满 第二个参数是裁剪 填充
           canvas.drawBitmap(mImage,null,mImageBounds,paint);
       } else {
           //居中显示
//           int imageHeight = mHeight - mBounds.height() - getPaddingBottom();
//           mImageBounds.left = mWidth/2 - mImage.getWidth()/2;
//           mImageBounds.top = imageHeight/2 - mImage.getHeight()/2;
//
//           mImageBounds.right = mWidth/2 + mImage.getWidth()/2;
//           mImageBounds.bottom = imageHeight/2 + mImage.getWidth()/2;


           canvas.drawBitmap(mImage,null,mImageBounds,paint);
       }


    }
}
