package mycustormview.one.step6_attribute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ct.demo_custormviewstep1.R;

/**
 * Created by koudai_nick on 2017/12/15.
 * 总结:
 * 自定义控件,在代码里自定义的话就会调用第一个构造函数
 * 在xml文件里面构造的话就会调用第二个构造函数。
 * 第一种情况:
 * 当在xml里面没有设置自定义属性的时候,就可以用设置的默认属性,该属性是引用类型的。
 * 要是没有的话 就到style里面去找默认的属性。还是没有的话就没有了。
 * 所以优先级是xml>style>Theme中的默认Sytle>默认Style（通过obtainStyledAttributes的第四个参数指定）>在Theme中直接指定属性值
 *
 * xml 就是直接在布局里面写的自定义属性
 * style 就是在布局里面写的style属性
 * Theme中的默认style 就是构造函数里面传进来的style 就是当前Theme中的一个attribute，可以通过引用来实现。
 *
 * 当theme里面赋值的话就会生效，哪怕里面没有 <!--<item name="textOneAttr">attr one from style</item>-->
 * 默认style 就是obtainStyledAttributes 的最后一个参数 当前面的全都失效的话 这个属性就生效了。
 * 直接在Theme指定属性值: 就是说在Theme里面直接设置属性。
 *
 *
 * /////
 * //第三个参数是theme中的一个attribute,是指向style的一个引用。当在xml布局中或者style中没有为这个
   //属性值的时候，就会从这个theme中的style引用中去搜索该值。
 * TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, defStyleAttr, 0);
 */

public class CustormView_SixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_six);
    }
}
