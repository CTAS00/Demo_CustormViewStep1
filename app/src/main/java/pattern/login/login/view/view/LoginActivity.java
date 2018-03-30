package pattern.login.login.view.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ct.demo_custormviewstep1.R;

import pattern.login.login.view.presenter.ILoginPresenter;
import pattern.login.login.view.presenter.ILoginPresenterCompl;

/**
 * Created by koudai_nick on 2018/3/16.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView {
    private TextView text;
    private Button btn;
    private ILoginPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_login);
        initView();
        initPresenter();
        initListener();
    }
    private void initListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.display("我是哈哈哈哈哈哈");
            }
        });
    }
    private void initPresenter() {
        presenter = new ILoginPresenterCompl(this);
    }
    private void initView() {
        text = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);
    }
    @Override
    public void onLoginResult(String text) {
        this.text.setText(text);
    }


}
