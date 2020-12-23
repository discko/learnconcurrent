package space.wudi.learnconcurrent.basicconcurrent.test05;

import org.openjdk.jol.info.ClassLayout;

/**
 * check lock state in object header
 */
@SuppressWarnings("all")
public class Main05 {
    static class MyRunnable {
        void fun1(Object locker){
            try{
                synchronized (locker){
                    printHeader(locker, 2);
                    Thread.sleep(500);
                    // re-entry
                    fun2(locker);
                    printHeader(locker, 4);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        void fun2(Object locker){
            synchronized (locker){
                printHeader(locker, 3);
            }
        }
    }

    public static void main(String[] args) {
        MyRunnable mr = new MyRunnable();
        Object locker = new Object();
        // in main thread
        printHeader(locker, 1);
        mr.fun1(locker);
        printHeader(locker, 5);
        // complete main thread and change to thread-1
        Thread t1 = new Thread(()->{
            mr.fun1(locker);
        },"Thread1");
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
        }
        printHeader(locker, 6);
        // complete single thread
        // start to threads to compete
        Thread t2 = new Thread(()->{
            mr.fun1(locker);
        },"Thread2");
        t2.start();
        try {
            Thread.sleep(500);
            // main thread compete with Thread2
            mr.fun1(locker);
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // after compete
        printHeader(locker, 7);
    }

    private static void printHeader(Object locker, int order){
        System.out.printf("%s@%s:\n%s\n",order, Thread.currentThread().getName(), ClassLayout.parseInstance(locker).toPrintable());
    }

}
