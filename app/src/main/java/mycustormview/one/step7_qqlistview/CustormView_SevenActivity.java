package mycustormview.one.step7_qqlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ct.demo_custormviewstep1.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by koudai_nick on 2018/1/18.
 */

public class CustormView_SevenActivity extends AppCompatActivity {
    private qqlistview mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_seven);
        mListView = (qqlistview) findViewById(R.id.id_listview);
        // 不要直接Arrays.asList
        mDatas = new ArrayList<String>(Arrays.asList("HelloWorld", "Welcome", "Java", "Android", "Servlet", "Struts",
                "Hibernate", "Spring", "HTML5", "Javascript", "Lucene"));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        mListView.setAdapter(mAdapter);

//        mListView.setDelButtonClickListener(new DelButtonClickListener()
//        {
//            @Override
//            public void clickHappend(final int position)
//            {
//                Toast.makeText(MainActivity.this, position + " : " + mAdapter.getItem(position), 1).show();
//                mAdapter.remove(mAdapter.getItem(position));
//            }
//        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(CustormView_SevenActivity.this, position + " : " + mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
