package textfunction;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ct.demo_custormviewstep1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koudai_nick on 2018/2/8.
 */

public class FunActivity extends AppCompatActivity {
    private LinearLayout id_gallery;

    private LayoutInflater mInflater;


    private List<Integer> mImgIds;

    private MyHorizontalScrollView scrollview;

    private HorizontalScrollView textscrollview;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_function);
        initData();
        initView();

    }

    private void initView() {
        id_gallery = (LinearLayout) findViewById(R.id.id_gallery);

        mInflater = LayoutInflater.from(this);

        scrollview = (MyHorizontalScrollView) findViewById(R.id.scrollview);

        MyAdapter adapter = new MyAdapter(this,mImgIds);
        scrollview.initData(adapter);


        textscrollview = (HorizontalScrollView) findViewById(R.id.textscrollview);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 获得的是偏移量
                Log.e("CTAS","scrollx = " +textscrollview.getScrollX() + "");
            }
        },2000);
    }

    private void initData()
    {
        mImgIds = new ArrayList<>();
        mImgIds.add(R.drawable.ic_launcher);
        mImgIds.add(R.drawable.ic_launcher);
        mImgIds.add(R.drawable.ic_launcher);
        mImgIds.add(R.drawable.ic_launcher);
        mImgIds.add(R.drawable.ic_launcher);
        mImgIds.add(R.drawable.ic_launcher);
        mImgIds.add(R.drawable.ic_launcher);
        mImgIds.add(R.drawable.ic_launcher);
        mImgIds.add(R.drawable.ic_launcher);
        mImgIds.add(R.drawable.ic_launcher);


//        mImgIds = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
//                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
//                R.drawable.ic_launcher, R.drawable.ic_launcher };
    }
}
