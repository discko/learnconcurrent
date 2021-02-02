package space.wudi.learnconcurrent.basicconcurrent.test02;

import java.util.concurrent.TimeUnit;

/**
 * try to prevent thread cache WITH keyword volatile
 */
public class Main0201 {
    @SuppressWarnings("all")
    static class MyAction{
        volatile boolean flag1 = true;
        boolean flag2 = true;
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
        Thread t1 = new Thread(a1::fun1, "Thread-1");
        Thread t2 = new Thread(a2::fun2, "Thread-2");
        t1.start();
        t2.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a1.flag1 = false;
        System.out.println("change a1.flag to " + a1.flag1);
        a2.flag2 = false;
        System.out.println("change a2.flag to " + a2.flag2);
    }
}
