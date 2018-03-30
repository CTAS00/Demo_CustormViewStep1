package mycustormview.one.step8_MyViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by koudai_nick on 2018/1/19.
 */

public class MyButton extends Button {

    private static final String TAG = "CTAS_MyButton";
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"dispatchTouchEvent MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"dispatchTouchEvent MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"dispatchTouchEvent MotionEvent.ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
    private int code = 0;

    // 说明button 消耗了这个事件  说明已经处理了这个点击事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"onTouchEvent MotionEvent.ACTION_DOWN");
                if(code == 0){
                    code += 1;
                } else {
                    code = 0;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"onTouchEvent MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"onTouchEvent MotionEvent.ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }
}
