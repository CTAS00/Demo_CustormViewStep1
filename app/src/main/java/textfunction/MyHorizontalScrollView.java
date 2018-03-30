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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by koudai_nick on 2018/2/8.
 */

public class MyHorizontalScrollView extends HorizontalScrollView {
    private static final String TAG = "MyHorizontalScrollView";

    private MyAdapter adapter;


    private LinearLayout mContainer;


    private int mChildWidth;// 子元素的宽
    private int mChildHeight; // 子元素的搞


    private int mCountOneScreen;// 计算每次要加载view的个数



    /**
     * 当前最后一张图片的index
     */
    private int mCurrentIndex;

    /**
     * 当前第一张图片的下标
     */
    private int mFristIndex;

    /**
     * 屏幕的宽度
     */
    private int mScreenWitdh;

    /**
     * 保存View与位置的键值对
     */
    private Map<View, Integer> mViewPos = new HashMap<View, Integer>();
    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获得屏幕宽度
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWitdh = outMetrics.widthPixels;
        Log.e(TAG, "mScreenWitdh() = " + mScreenWitdh);
    }


    public void initData(MyAdapter adapter){
        this.adapter = adapter;
        mContainer = (LinearLayout) getChildAt(0);

        // 获得适配器中的第一个view
        View view = adapter.getView(0,null,mContainer);
        mContainer.addView(view);

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

        initFirstScreenChildren(mCountOneScreen);


    }

    private void initFirstScreenChildren(int mCountOneScreen) {
        mContainer = (LinearLayout) getChildAt(0);
        mContainer.removeAllViews();


        for (int i = 0; i < mCountOneScreen; i++)
        {
            View view = adapter.getView(i, null, mContainer);
            mContainer.addView(view);
            mCurrentIndex = i;
        }

    }

    /**
     * 加载下一张图片
     */
    protected void loadNextImg(){
        if(mCurrentIndex == adapter.getCount() -1){
            return;
        }
        //移除第一张图片，且将水平滚动位置置0
        Log.e(TAG, "loadNextImg()one = " + mContainer.getChildCount());
        // 然后就能将getScrollX的首个布局显示 定位到第一个布局上面去了

        // 移动view到坐标点
        scrollTo(0, 0);
        mViewPos.remove(mContainer.getChildAt(0));
        mContainer.removeViewAt(0);

        // 并没有到最后一个 所以拿下一张的图片出来
        View view = adapter.getView(++mCurrentIndex,null, mContainer);
        mContainer.addView(view);

        mFristIndex++; // 当前图片第一个显示的位置
        Log.e(TAG, "loadNextImg()two = " + mContainer.getChildCount());

    }

    /**
     * 加载上一张图片
     */
    protected void loadPreImg(){
        // 当前已经是第一张了
        if(mFristIndex ==0){
            return ;
        }
        // 定位到当前的index
        int index = mCurrentIndex - mCountOneScreen;
        if(index>=0){
            // 移除所达到的最后一个项目
            View oldView= mContainer.getChildAt(mContainer.getChildCount()-1);
            mContainer.removeView(oldView);


            // 获得前面的view
            View view = adapter.getView(index,null,mContainer);
            mContainer.addView(view,0);
            //水平滚动位置向左移动view的宽度个像素  这个是偏移量  并不是坐标
            // 将视图绘制到指定区域
            // 移动view到坐标点(-226,0)
            scrollTo(mChildWidth, 0);
            //当前位置--，当前第一个显示的下标--
            mCurrentIndex--;
            mFristIndex--;
        }
    }
    /**
     * getScrollX 就是幕布在窗口左边界时候的值了,初始化内容显示的位置就是原点(0.0)了
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                int scrollX = getScrollX();
                Log.e(TAG, "getScrollX() = " + scrollX);

                if(scrollX>=mChildWidth){
                    loadNextImg();
                }
                if(scrollX==0){
                    loadPreImg();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
