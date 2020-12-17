package space.wudi.learnconcurrent.basicconcurrent.test02;

import java.util.concurrent.TimeUnit;

public class Main {
//    @SuppressWarnings()
    static class MyAction{
        boolean flag = true;
        void fun(){
            System.out.println(Thread.currentThread().getName()+" loop started");
            while (flag) {
            };
            System.out.println(Thread.currentThread().getName()+" loop stopped");
        }
    }

    public static void main(String[] args) {
        MyAction a = new MyAction();
        Thread t = new Thread(a::fun);
        t.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.flag = false;
        System.out.println("change flag to " + a.flag);
    }
}
