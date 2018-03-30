package rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ct.demo_custormviewstep1.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by koudai_nick on 2018/3/28.
 */

public class rxAndroid extends AppCompatActivity {
    private static final String TAG="RXJAVA";
    // 观察者模式  Button是一个驿站(被观察者)  onClick是订阅的人(观察者)
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rxjava);
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                initRxJava();
//                initBackRxJava();


//                initNewRxJava();

                initBestRxJava();
            }
        });
    }

    private void initNewRxJava() {
//        Observable.from(new String[]{"This","is","RxJava"})
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        Log.e("CTASHAHA","被调用的次数");
//                        return s.toUpperCase();
//                    }
//                }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.e("CTASHAHA",s);
//            }
//        });
        // 传进来Action 在关联的时候会调用call方法并且把回调传进来
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext("嘻嘻");
                subscriber.onNext("哈哈");
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                Log.e("CTASHAHA",String.valueOf(o));
            }
        });
        // 传进去一个OnSubscribe  当被订阅的时候  call方法会被调用
        Observable.from(new String[]{"this","is","ct"})
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return null;
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });
    }
    private void initRxJava(){
        //创建观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG,"onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG,s);

            }
        };

        // 创建被观察者
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Wrold");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(observer);

    }

    /**
     * 有的时候只会用的几个
     * 可以使用action去处理
     */
    private void initSecondRxJava(){
        Observable.just("Hello","World").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
        // 完成以后的实现
        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {

            }
        };
        // onNext的处理
        Action1<String>  onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {


            }
        };
        // 处理出错的处理
        Action1<Throwable> onErrowAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        };
        // 被观察者    观察者就是看见变化的那一类
        Observable.just("Hello","World").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                // onNext的处理

            }
        });

    }

    /**事件的发起和消费都是在同一个线程中执行的。
     * 带有返回值的方法
     */
    private void initBackRxJava(){

        Student studentone = new Student();
        studentone.setName("张三");
        studentone.setAge(10);
        Student studenttwo = new Student();
        studenttwo.setName("李四");
        studenttwo.setAge(20);
        // 遍历很快
        Observable.just(studentone,studenttwo)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("CTAS",s);
            }
        });
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
            }
        });

        //创建一个被观察者
        Observable.create(new Observable.OnSubscribe<Object>() {

            @Override
            public void call(Subscriber<? super Object> subscriber) {
                // 这边做的处理 然后回调函数里面会去调用方法

            }
        }).subscribe(new Action1<Object>() {

            @Override
            public void call(Object o) {

            }
        });


        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {

            }
        }).subscribe();




//
//
//        Observable.just(studentone,studenttwo)
//                .map(new Func1<Student, List<Course>>() {
//
//                    @Override
//                    public List<Course> call(Student student) {
//                        return student.getCoursesList();
//                    }
//                }).subscribe(new Action1<List<Course>>() {
//            @Override
//            public void call(List<Course> courses) {
//                //遍历courses，输出cuouses的name
//                for (int i = 0; i < courses.size(); i++){
//                    Log.i(TAG, courses.get(i).getName());
//                }
//            }
//        });
//
//
//        Observable.just(studentone,studenttwo)
//                // 将student中的course转变成被观察者
//                .flatMap(new Func1<Student, Observable<Course>>() {
//                    @Override
//                    public Observable<Course> call(Student student) {
//                        return  Observable.from(student.getCoursesList());
//                    }
//                }).subscribe(new Action1<Course>() {
//            @Override
//            public void call(Course course) {
//                Log.i(TAG, course.getName());
//            }
//        });



//        Observable.just("Hello","Thread")
//                .subscribeOn(Schedulers.newThread())// 指定subscribe()发生在新的线程
//                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Log.e(TAG,Thread.currentThread().getName());
//                    }
//                });

    }

    class Student{
        String name;
        int age;
        List<Course> coursesList;

        public List<Course> getCoursesList() {
            return coursesList;
        }

        public void setCoursesList(List<Course> coursesList) {
            this.coursesList = coursesList;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class Course{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private void initBestRxJava(){
//        Observable.create(new Observable.OnSubscribe<Object>() {
//
//            @Override
//            public void call(Subscriber<? super Object> subscriber) {
//                // 定义操作的方案
//
//            }
//        }).subscribe(new Action1<Object>() {
//
//            @Override
//            public void call(Object o) {
//                // 定义操作的回调
//
//            }
//        });
        // .map 就是生成了一个新的Observable 和一个新的subscriber
        // from生成一个新的Observable
        // map也生成一个新的Observable
        // 调用一次map就是把用户生成的subscriber到上一个Observable里面去


        // 直接生成3个Observable  执行onNext的时候 会一路调用下一层的subscriber的onNext()
//        Observable.from(new String[]{"i","am","ct"})
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        return s.toUpperCase();
//                    }
//                })
//                .map(new Func1<String, String>() {
//
//                    @Override
//                    public String call(String s) {
//                        return s.toLowerCase();
//                    }
//                }).subscribe(new Action1<String>() {
//
//            @Override
//            public void call(String o) {
//                Log.e("CTASQQ",o);
//            }
//        });

        //每一个Observable都会有一个OnSubscribe和一个结果回掉
        // OnSubscribe定义事件执行的顺序
        // Subscriber定义事件回调的结果
//        Observable.create(new Observable.OnSubscribe<Object>() {
//
//            @Override
//            public void call(Subscriber<? super Object> subscriber) {
//                subscriber.onNext("哈哈");
//                subscriber.onNext("嘻嘻");
//                subscriber.onCompleted();
//
//            }
//        }).subscribe(new Subscriber<Object>() {
//
//            @Override
//            public void onCompleted() {
//                Log.e("CTASQQ","onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Object o) {
//                Log.e("CTASQQ",String.valueOf(o));
//
//            }
//        });



        //每一个其实都是OnSubscribe
        //流程 map生成一个新的Observable对象,
        //在subscribe的时候会生成一个新的Subscriber
        /**
         * 在subscribe的时候会生成一个新的Subscriber,
         * 然后会被顶层的Observable去调用,顶层会调用subscriber
         * 的执行顺序，然后会被用户所定义的subscriber所执行。
         */
//        Observable.create(new Observable.OnSubscribe<Object>() {
//            @Override
//            public void call(Subscriber<? super Object> subscriber) {
//                subscriber.onNext("JAHAHAHAHHAH");
//            }
//        }).map(new Func1<Object, Object>() {
//            @Override
//            public Object call(Object o) {
//                return String.valueOf(o) + "YYYYYYYY";
//            }
//        }).subscribe(new Action1<Object>() {
//            @Override
//            public void call(Object o) {
//                Log.e("CTASQQ",String.valueOf(o));
//            }
//        });
//
//
//
//        Observable.create(new Observable.OnSubscribe<Object>() {
//
//            @Override
//            public void call(Subscriber<? super Object> subscriber) {
//                subscriber.onNext("哈哈");
//                Log.e("CTASFF",Thread.currentThread().getName());
//
//            }
//        })
//                // 事件产生的线程
//          .subscribeOn(Schedulers.newThread())
//                // 事件消费的线程
//          .observeOn(Schedulers.io())
//                .subscribe(new Observer<Object>() {
//
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Object o) {
//                Log.e("CTASFF",Thread.currentThread().getName());
//            }
//        });
//
//
//        Observable.just("Hello", "Wrold")
//                .subscribeOn(Schedulers.newThread())//指定：在新的线程中发起
//                // 处理的是Subscriber所运行在的线程
//                .observeOn(Schedulers.io())         //指定：在io线程中处理
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        Log.e("CTASFF","Func1" + Thread.currentThread().getName());
//                        return "";       //处理数据
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())//指定：在主线程中处理
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Log.e("CTASFF","Action1" + Thread.currentThread().getName());
////                        show(s);                       //消费事件
//                    }
//                });



//        Observable.create(new Observable.OnSubscribe<Object>() {
//
//            @Override
//            public void call(Subscriber<? super Object> subscriber) {
//                subscriber.onNext("哈哈");
//                subscriber.onNext("嘻嘻");
//
//            }
//        }).subscribe(new Subscriber<Object>() {
//
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Object o) {
//
//            }
//        });


//        Observable.create(new Observable.OnSubscribe<Object>() {
//
//            @Override
//            public void call(Subscriber<? super Object> subscriber) {
//
//            }
//        }).map(new Func1<Object, Object>() {
//
//
//            @Override
//            public Object call(Object o) {
//                return null;
//            }
//        }).subscribe(new Subscriber<Object>() {
//
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Object o) {
//
//            }
//        });


        Observable.interval(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Toast.makeText(rxAndroid.this,"RxJava间隔3秒周期性操作,当前时间:"+System.currentTimeMillis(), Toast.LENGTH_LONG).show();
                    }
                });




    }
}
