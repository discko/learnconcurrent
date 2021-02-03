package space.wudi.learnconcurrent.basicconcurrent.test07;

import java.util.concurrent.CountDownLatch;

/**
 * test reorder
 */
public class Main0701 {
    private static int a, b;
    private static int x, y;

    public static void main(String[] args) throws InterruptedException {
        for(long i = 0;;i++){
            a = 0; b = 0; x = 0; y = 0;
            CountDownLatch cdl = new CountDownLatch(2);
            Thread t1 = new Thread(()->{ a = 1; x = b; cdl.countDown();});
            Thread t2 = new Thread(()->{ b = 1; y = a; cdl.countDown();});
            t1.start();t2.start();
            cdl.await();
            if(x == 0 && y == 0){
                System.out.println("impossible value x=0 && y=0 happens at "+i);
                break;
            }
        }
    }
}
