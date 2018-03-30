package rxjava;

import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by koudai_nick on 2018/3/28.
 */

public class rxJavaText {


    public static void main(String args[]){
        Observable.just("Hello","World")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);

                    }
                });

    }
}
