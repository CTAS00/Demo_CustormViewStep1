package mycustormview.one.step8_MyViewGroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.ct.demo_custormviewstep1.R;

import java.util.ArrayList;
import java.util.Arrays;

import mycustormview.one.step7_qqlistview.CustormView_SevenActivity;
import mycustormview.one.step7_qqlistview.qqlistview;

/**
 * Created by koudai_nick on 2018/1/19.
 */

public class CustormView_EightActivity extends AppCompatActivity {
    private Button MyButton;
    private MyLinearlayout MyLinearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_eight);
        MyButton = (Button) findViewById(R.id.MyButton);
        MyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        MyLinearlayout = (mycustormview.one.step8_MyViewGroup.MyLinearlayout) findViewById(R.id.MyLinearlayout);
        MyLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
