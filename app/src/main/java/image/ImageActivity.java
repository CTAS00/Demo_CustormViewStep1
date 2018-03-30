package image;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ct.demo_custormviewstep1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koudai_nick on 2018/3/6.
 */

public class ImageActivity extends AppCompatActivity {
    private PageAdapter pagerAdapter;
    List<String> imagesUrl;
    int current;
    private PhotoViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        initView();
        initData();
    }

    private void initView() {
        viewPager = (PhotoViewPager) findViewById(R.id.viewPager);
    }

    private void initData() {
        imagesUrl = new ArrayList<>();
        for(int i=0;i<5;i++){
            imagesUrl.add("ii" + i);
        }

        pagerAdapter = new PageAdapter(imagesUrl, getApplicationContext());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(current);
//        page.setText(current + 1 + "/" + imagesUrl.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current = position;
//                page.setText(current + 1 + "/" + imagesUrl.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
