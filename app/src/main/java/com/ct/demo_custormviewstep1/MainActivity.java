package com.ct.demo_custormviewstep1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import image.ImageActivity;
import mycustormview.one.step1.CustormView_OneActivity;
import mycustormview.one.step2.CustormView_TwoActivity;
import mycustormview.one.step3_circle.CustormViewStep3Activity;
import mycustormview.one.step4_volume.CustormView_FourActivity;
import mycustormview.one.step5_viewgroup1.CustormView_FiveActivity;
import mycustormview.one.step6_attribute.CustormView_SixActivity;
import mycustormview.one.step7_qqlistview.CustormView_SevenActivity;
import mycustormview.one.step8_MyViewGroup.CustormView_EightActivity;
import rxjava.rxAndroid;
import textfunction.FunActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 基础的自定义view  textview 实现点击自动生成4位的随机数
     * @param v
     */
    public void custormview_step1(View v){
        gotoActivity(CustormView_OneActivity.class);

    }


    public void custormview_step2(View v){
        gotoActivity(CustormView_TwoActivity.class);

    }

    public void custormview_step3(View v){
        gotoActivity(CustormViewStep3Activity.class);
    }

    public void  custormview_step4(View v){
        gotoActivity(CustormView_FourActivity.class);
    }

    public void  custormview_step5(View v){
        gotoActivity(CustormView_FiveActivity.class);
    }
    public void  custormview_step6(View v){
        gotoActivity(CustormView_SixActivity.class);
    }

    public void custormview_step7(View v){
        gotoActivity(CustormView_SevenActivity.class);
    }

    public void custormview_step8(View v){
        gotoActivity(CustormView_EightActivity.class);
    }

    public void custormview_step10(View v){
        gotoActivity(FunActivity.class);
    }
    public void custormview_step11(View v){
        gotoActivity(ImageActivity.class);
    }

    public void custormview_step12(View v){
        gotoActivity(rxAndroid.class);
    }






    private void gotoActivity(Class activity){
        Intent intent = new Intent(MainActivity.this,activity);
        startActivity(intent);

    }
}
