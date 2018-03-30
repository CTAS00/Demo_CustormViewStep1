package textfunction;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by koudai_nick on 2018/2/8.
 */

public class MyTextHorizontalScrollView extends HorizontalScrollView {
    private static final String TAG = "MyHorizontalScrollView";

    /**
     * 屏幕的宽度
     */
    private int mScreenWitdh;

    private MyAdapter myAdapter;


    private int mChildWidth,mChildHeight;

    private LinearLayout mContentView;

    private int mCountOneScreen; // 计算一开始就要加载的view的个数


    private int lastindex;
    private int mFristIndex;

    public MyTextHorizontalScrollView(Context context) {
        super(context);
    }

    public MyTextHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获得屏幕宽度
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWitdh = outMetrics.widthPixels;
        Log.e(TAG, "mScreenWitdh() = " + mScreenWitdh);
    }

    public void setAdapter(MyAdapter myAdapter){
        this.myAdapter = myAdapter;
        // 计算当前界面要先加载多少个界面

        mContentView = (LinearLayout) getChildAt(0);
        // 获得适配器中的view
        View view=myAdapter.getView(0,null,mContentView);

        // 强制计算当前view的宽和高


        if(mChildWidth==0&&mChildHeight==0){
            int w = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            view.measure(w, h);
            mChildHeight = view.getMeasuredHeight();
            mChildWidth = view.getMeasuredWidth();
            Log.e(TAG, view.getMeasuredWidth() + "," + view.getMeasuredHeight());

            // 计算每次加载多少个View
            mCountOneScreen = mScreenWitdh / mChildWidth+2;

            Log.e(TAG, "mCountOneScreen = " + mCountOneScreen
                    + " ,mChildWidth = " + mChildWidth);
        }

        if(myAdapter.getCount()<mCountOneScreen){
            mCountOneScreen =myAdapter.getCount();
        }
        initFirstScreen(mCountOneScreen);
    }

    private void initFirstScreen(int CountOneScreen) {
        if(mContentView.getChildCount()>0){
            mContentView.removeAllViews();
        }
        // 永远只会加载CountOneScreen这么多
        for(int i=0;i<CountOneScreen;i++){
            View view=myAdapter.getView(i,null,mContentView);
            mContentView.addView(view);
            lastindex = i; // 定位到最后一层
        }
    }


    // 关键点 就是内容显示的区域

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                // 滑动的时候的处理
                int scrollX = getScrollX();
                if(scrollX>=mChildWidth){
                    // 销毁第一个 并且显示下一个
                    loadNextImg();
                }
                // 当往左滑动的时候 并且初始化为0的时候
                if(scrollX==0){
                    // 销毁最后一个  添加第一个

                }
                break;
        }
        return super.onTouchEvent(ev);
    }
    // 功能 销毁第一个 显示最后一个
    private void loadNextImg() {
        // 当已经是最后一个的时候
        if(lastindex == myAdapter.getCount() -1){
            return ;
        }
        // 默认显示到第一个  就是让getScrollX的位置回到原始的位置
        // 原点就是初始化内容所显示的位置
        // 幕布的原点就是初始化的时候内容显示的位置
        // 一般就是  类似于将getScrollX 进行了处理
        scrollTo(0,0);
//        mContentView.removeView();
        mContentView.removeViewAt(0);
        View view = myAdapter.getView(++lastindex,null,mContentView);
        mContentView.addView(view);
        mFristIndex++; // 当前图片第一个显示的位置
    }

    private void loadPreImg(){
        if(mFristIndex == 0){
            return ;
        }
        // 定位到当前的index
        int index = lastindex - mCountOneScreen; // 定位到要展示的index
        if(index>=0){

            View oldView= mContentView.getChildAt(mContentView.getChildCount()-1);
            mContentView.removeView(oldView);

            // 获得前面的view
            View view = myAdapter.getView(index,null,mContentView);
            mContentView.addView(view,0);
            // 偏移量  往左偏移mChildWidth的距离  y值不变
            // 还是为getScrollView做处理
            scrollTo(mChildWidth, 0);
            //当前位置--，当前第一个显示的下标--
            lastindex--;
            mFristIndex--;
        }



    }
}
