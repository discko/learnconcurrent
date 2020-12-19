package space.wudi.learnconcurrent.basicconcurrent.test02;

import java.util.concurrent.TimeUnit;

/**
 * try to use key word volatile to prevent thread cache
 */
public class Main02 {
    @SuppressWarnings("all")
    static class MyAction{
        boolean flag1 = true;
        volatile boolean flag2 = true;
        void fun1(){
            System.out.println(Thread.currentThread().getName()+" fun1 loop started");
            while (flag1) {
            }
            System.out.println(Thread.currentThread().getName()+" fun1 loop stopped");
        }

        void fun2(){
            System.out.println(Thread.currentThread().getName()+" fun2 loop started");
            while (flag2) {
            }
            System.out.println(Thread.currentThread().getName()+" fun2 loop stopped");
        }

    }

    public static void main(String[] args) {
        MyAction a1 = new MyAction();
        MyAction a2 = new MyAction();
        Thread t1 = new Thread(a1::fun1);
        Thread t2 = new Thread(a2::fun2);
        t1.start();
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a1.flag1 = false;
        System.out.println("change a1.flag to " + a1.flag1);
        a2.flag2 = false;
        System.out.println("change a2.flag to " + a2.flag1);
    }
}
