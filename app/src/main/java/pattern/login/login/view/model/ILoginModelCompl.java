package pattern.login.login.view.model;

/**
 * Created by koudai_nick on 2018/3/16.
 *
 */
// 对数据要进行处理
public class ILoginModelCompl implements ILoginModel {



    @Override
    public boolean checkisvalid(String text) {
        if("登录".equals(text)){
            return true;
        }
        return false;
    }
}
