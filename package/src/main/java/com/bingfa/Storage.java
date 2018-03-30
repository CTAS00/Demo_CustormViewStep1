package com.bingfa;

import java.util.LinkedList;

/**
 * Created by koudai_nick on 2018/3/1.
 */

public class Storage {

    // 仓库最大存储量
    private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();

//wait和notify都是 通过同一个对象去加锁和释放锁的  和synchronized

    // 还有的就是await()和signal(）这个方法  实现更加细致的线程操控 和lock相配

    public void produce(int num){
        synchronized (list){
            while(list.size() + num >MAX_SIZE){
                System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:"
                        + list.size() + "/t暂时不能执行生产任务!");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for(int i=0;i<num;i++){
                list.add(new Object());
            }
            System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + list.size());
            list.notifyAll();
        }
    }


    public void consume(int num){
        synchronized (list){

            while(num>list.size()){

                System.out.println("【要消费的产品数量】:" + num + "/t【库存量】:"
                        + list.size() + "/t暂时不能执行生产任务!");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 消费条件满足情况下，消费num个产品
            for (int i = 1; i <= num; ++i)
            {
                list.remove();
            }

            System.out.println("【已经消费产品数】:" + num + "/t【现仓储量为】:" + list.size());

            list.notifyAll();

        }

    }
}
