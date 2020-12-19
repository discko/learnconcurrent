package space.wudi.learnconcurrent.basicconcurrent.test01;

/**
 * if multi-threads access different method with same locker
 * all those method will be synchronized.
 * because lock marker is recorded at the object header,
 * and it only remembers lock status (what kind lock am I)
 * and thread id (who owns me)
 */
public class Main01 {
    static class MyAction {
        final Object locker;
        MyAction(Object locker){
            this.locker = locker;
        }
        void fun1() {
            synchronized (locker){
                System.out.println(Thread.currentThread().getName()+" in fun1 started");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" in fun1 ended");
            }
        }
        void fun2() {
            synchronized (locker){
                System.out.println(Thread.currentThread().getName()+" in fun2 started");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" in fun2 ended");
            }
        }

    }

    public static void main(String[] args) {
        Object locker = new Object();
        MyAction a1 = new MyAction(locker);
        MyAction a2 = new MyAction(locker);
        Thread t1 = new Thread(a1::fun1);
        Thread t2 = new Thread(a2::fun2);
        t1.start();
        t2.start();
    }
}
