//package pattern.login.newlogin.view;
//
//import android.databinding.DataBindingUtil;
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.support.v7.app.AppCompatActivity;
//
//import com.ct.demo_custormviewstep1.R;
//import com.ct.demo_custormviewstep1.databinding.ActivityViewMvvmBinding;
//
//import pattern.login.newlogin.VM.AMainVM;
//
///**
// * Created by koudai_nick on 2018/3/16.
// */
////
//public class MvvmActivity extends AppCompatActivity {
//    ActivityViewMvvmBinding binding;
//    private AMainVM vm;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_mvvm);
//        vm = new AMainVM();
//        binding.setVm(vm);
//        binding.setUser(vm.bindingUserModel);
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        binding.unbind();
//        super.onDestroy();
//    }
//}
