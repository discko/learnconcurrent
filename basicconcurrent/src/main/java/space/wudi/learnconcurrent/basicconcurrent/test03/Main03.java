package space.wudi.learnconcurrent.basicconcurrent.test03;

/**
 * use join() and interrupt()
 */
public class Main03 {

    static class MyRunnable implements Runnable{
        private final long time;
        private final Thread waitJoin;
        MyRunnable(long ms, Thread waitJoin){
            this.time = ms;
            this.waitJoin = waitJoin;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" started");
            try {
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName()+" time's up");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+" interrupted");
            }
            if(waitJoin != null){
                try {
                    waitJoin.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" stopped");
        }
    }

    public static void main(String[] args) {
        System.out.println("========Use join========");
        Thread t2 = useJoin();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=====Use Interrupt======");
        useInterrupt();

    }

    private static Thread useJoin(){
        Thread t1 = new Thread(new MyRunnable(2000, null), "Thread1");
        Thread t2 = new Thread(new MyRunnable(1000, t1), "Thread2");

        t1.start();
        t2.start();
        return t2;
    }

    private static void useInterrupt(){
        Thread t3 =  new Thread(new MyRunnable(2000, null), "Thread3");
        t3.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread time's up, call interrupt");
        t3.interrupt();
    }
}
