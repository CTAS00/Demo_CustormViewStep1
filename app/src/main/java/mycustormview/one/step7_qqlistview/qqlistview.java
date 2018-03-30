package mycustormview.one.step7_qqlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.ct.demo_custormviewstep1.R;

/**
 * Created by koudai_nick on 2018/1/18.
 */

public class qqlistview extends ListView {
    private int xDown;
    private int yDown;
    private int xMove;
    private int yMove;


    // 当前手指触摸的位置
    private int mCurrentViewPos;

    // 当前手指所点击的view
    private View mCurrentView;


    // 用户滑动的最小距离
    private int touchSlop;


    // 用户是否在滑动
    private boolean isSliding;

    //弹出层次
    private PopupWindow mPopupWindow;

    private LayoutInflater mInflater;


    // 删除按钮
    private Button mDelBtn;


    private int mPopupWindowHeight;
    private int mPopupWindowWidth;


    // 上下文
    private Context mContext;

    private static final String TAG = "CTAS_QQLISTVIEW";



    public qqlistview(Context context) {
        super(context);
    }

    public qqlistview(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public qqlistview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG,"qqlistview onCreate");
        mContext = context;
        mInflater = LayoutInflater.from(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();


        // 获取到删除button的样式  根布局失效
        View view=mInflater.inflate(R.layout.activity_view_seven_delete_btn,null);
        mDelBtn = view.findViewById(R.id.id_item_btn);
        // 添加到里面去  并且默认根布局是wrap、wrap
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);


        /**
         * 先调用下measure,否则拿不到宽和高
         */
        mPopupWindow.getContentView().measure(0, 0);
        mPopupWindowHeight = mPopupWindow.getContentView().getMeasuredHeight();
        mPopupWindowWidth = mPopupWindow.getContentView().getMeasuredWidth();


    }
    // 对于事件的传递 先是dispatchTouchEvent
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                xDown = x;
                yDown = y;
                /**
                 * 如果当前popupWindow显示，则直接隐藏，然后屏蔽ListView的touch事件的下传
                 */
                if (mPopupWindow.isShowing())
                {
                    dismissPopWindow();
                    // 屏蔽掉listview的touch事件的传递
                    return false;
                }
                // 根据触摸点的坐标计算出点击的是listview中的哪个item
                mCurrentViewPos = pointToPosition(xDown,yDown);
                Log.e(TAG,"mCurrentViewPos =" + mCurrentViewPos);
                mCurrentView = getChildAt(mCurrentViewPos);
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                yMove = y;
                int dx = xMove - xDown;
                int dy = yMove - yDown;
                // 判断是否从右往左的点击
//                && Math.abs(dy) < touchSlop
                // 符合滑动所要求的最小距离
                if(xMove<xDown&&Math.abs(dx) > touchSlop ){
                    // 是否从右往左的滑动
                    isSliding = true;
                }
                boolean isOne = xMove<xDown;
                boolean isTwo = Math.abs(dx) > touchSlop;
                boolean isThree = Math.abs(dy) < touchSlop;
                Log.e(TAG,"isSliding = " + isSliding + "one = " + isOne + "two = " + isTwo + "three = " + isThree);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG,"onInterceptTouchEvent =" + super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }
    // onTouchEvent的处理
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 只有滑动才会去响应这个效果
        // 只有滑动才会去响应
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"ACTION_DOWN = ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"ACTION_MOVE = ACTION_MOVE");
                // 当前view上的x与y轴
                int[] location = new int[2];
                mCurrentView.getLocationOnScreen(location);
                mPopupWindow.setAnimationStyle(R.style.popwindow_delete_btn_anim_style);
//                    mPopupWindow.setAnimationStyle(R.style.popwindow_delete_btn_anim_style);
//                    + mCurrentView.getWidth()
                mPopupWindow.showAtLocation(mCurrentView, Gravity.LEFT | Gravity.TOP,
                        location[0] +  mCurrentView.getWidth(), location[1] + mCurrentView.getHeight() / 2
                                - mPopupWindowHeight / 2);
//                    Log.e(TAG,"ACTION_MOVE" + "点击的item的x值" + location[0] + "点击的item的y值" + location[1]);
//                    Log.e(TAG,"ACTION_MOVE" + "mCurrentView.getWidth()" + mCurrentView.getWidth() + "mCurrentView.getHeight()" + mCurrentView.getHeight());
//                    Log.e(TAG,"ACTION_MOVE = 滑动成功");
                // 设置删除按钮的回调
                mDelBtn.setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Toast.makeText(mContext,"已经点击了删除按钮",Toast.LENGTH_SHORT).show();
//                            if (mListener != null)
//                            {
//                                mListener.clickHappend(mCurrentViewPos);
//                                mPopupWindow.dismiss();
//                            }
                    }
                });
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"ACTION_UP = ACTION_UP");
                isSliding = false;
                break;
        }

//        if(isSliding){
//            // 只有滑动才会去响应
//            switch(ev.getAction()){
//                case MotionEvent.ACTION_DOWN:
//                    Log.e(TAG,"ACTION_DOWN = ACTION_DOWN");
//                    break;
//
//                case MotionEvent.ACTION_MOVE:
//                    Log.e(TAG,"ACTION_MOVE = ACTION_MOVE");
//                    // 当前view上的x与y轴
//                    int[] location = new int[2];
//                    mCurrentView.getLocationOnScreen(location);
////                    mPopupWindow.setAnimationStyle(R.style.popwindow_delete_btn_anim_style);
////                    mPopupWindow.setAnimationStyle(R.style.popwindow_delete_btn_anim_style);
////                    + mCurrentView.getWidth()
//                    mPopupWindow.showAtLocation(mCurrentView, Gravity.LEFT | Gravity.TOP,
//                            location[0] +  mCurrentView.getWidth(), location[1] + mCurrentView.getHeight() / 2
//                                    - mPopupWindowHeight / 2);
////                    Log.e(TAG,"ACTION_MOVE" + "点击的item的x值" + location[0] + "点击的item的y值" + location[1]);
////                    Log.e(TAG,"ACTION_MOVE" + "mCurrentView.getWidth()" + mCurrentView.getWidth() + "mCurrentView.getHeight()" + mCurrentView.getHeight());
////                    Log.e(TAG,"ACTION_MOVE = 滑动成功");
//                    // 设置删除按钮的回调
//                    mDelBtn.setOnClickListener(new OnClickListener()
//                    {
//                        @Override
//                        public void onClick(View v)
//                        {
//                            Toast.makeText(mContext,"已经点击了删除按钮",Toast.LENGTH_SHORT).show();
////                            if (mListener != null)
////                            {
////                                mListener.clickHappend(mCurrentViewPos);
////                                mPopupWindow.dismiss();
////                            }
//                        }
//                    });
//                    break;
//                case MotionEvent.ACTION_UP:
//                    Log.e(TAG,"ACTION_DOWN = ACTION_DOWN");
//                    isSliding = false;
//                    break;
//            }
//                return true;
//        }
        // 说明消费了事件
        Log.e(TAG,"final = " + super.onTouchEvent(ev));
        return super.onTouchEvent(ev);
    }

    private void dismissPopWindow()
    {
        if (mPopupWindow != null && mPopupWindow.isShowing())
        {
            mPopupWindow.dismiss();
        }
    }
}
