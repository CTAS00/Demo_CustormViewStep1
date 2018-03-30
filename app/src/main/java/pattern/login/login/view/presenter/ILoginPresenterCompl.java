package pattern.login.login.view.presenter;

import pattern.login.login.view.model.ILoginModel;
import pattern.login.login.view.model.ILoginModelCompl;
import pattern.login.login.view.view.ILoginView;

/**
 * Created by koudai_nick on 2018/3/16.
 */

public class ILoginPresenterCompl implements ILoginPresenter {
    private ILoginView iLoginView;

    private ILoginModel iLoginModel;

    public ILoginPresenterCompl(ILoginView iLoginView){
        this.iLoginView = iLoginView;
        // 填充数据
        initModel();
    }
    private void initModel() {
        iLoginModel = new ILoginModelCompl();
    }
    @Override
    public void display(String text) {
        // 对数据进行操作
        if(iLoginModel.checkisvalid(text)){
            iLoginView.onLoginResult(text);
        }else{
            iLoginView.onLoginResult("数据输入异常");
        }
    }

}
