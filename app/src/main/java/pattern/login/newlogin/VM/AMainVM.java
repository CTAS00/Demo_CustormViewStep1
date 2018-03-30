//package pattern.login.newlogin.VM;
//
//
//import android.databinding.ObservableField;
//import android.os.AsyncTask;
//
//import pattern.login.newlogin.model.BindingUser;
//
///**
// * Created by koudai_nick on 2018/3/16.
// */
//
//public class AMainVM {
//
//    public ObservableField<BindingUser> bindingUser;
//
//    public BindingUser bindingUserModel;
//
//    public AMainVM() {
//        bindingUser = new ObservableField<>();
//    }
//
//
//    public void onClickInit() {
//        BindingUser bindingUserModel = new BindingUser();
//        bindingUserModel.setName("just test");
//        bindingUserModel.setClickCount(0);
//        bindingUserModel.setUrl("http://img0.imgtn.bdimg.com/it/u=2770060730,47109478&fm=21&gp=0.jpg");
////        bindingUserModel.url.set("http://img0.imgtn.bdimg.com/it/u=2770060730,47109478&fm=21&gp=0.jpg");
//        bindingUser.set(bindingUserModel);
//    }
//
//
////    @Override
////    public void onClickUser() {
////        // 每点一此加1
////        bindingUser.clickCount.set(bindingUser.clickCount.get() + 1);
////    }
////
////    @Override
////    public void onClickInit() {
////        BindingUser bindingUserModel = new BindingUser();
////        bindingUser.name.set("just test");
////        bindingUser.clickCount.set(0);
////        bindingUser.url.set("http://img0.imgtn.bdimg.com/it/u=2770060730,47109478&fm=21&gp=0.jpg");
////        bindingUser.set(bindingUserModel);
////    }
////
////    @Override
////    public void onClickTestUpdate() {
////        new AsyncTask<Void, Void, Void>() {
////
////            @Override
////            protected Void doInBackground(Void... params) {
////                bindingUser.name.set("update in thread");
////                return null;
////            }
////        }.execute();
////    }
//}
