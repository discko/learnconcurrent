package space.wudi.learnconcurrent.basicconcurrent.test07;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
@SuppressWarnings("all")
public class Main0702 {
    private final int a;
    private int b;
    private static final AtomicInteger unexpected = new AtomicInteger(0);
    private static final AtomicInteger expected = new AtomicInteger(0);

    Main0702() throws InterruptedException {
        new Thread(()->doSomething()).start();
        a = 5;
    }
    private void doSomething(){
        b = a;
        if(b != 5){
            unexpected.incrementAndGet();
        }else{
            expected.incrementAndGet();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        long time = System.currentTimeMillis();
//        for(int i=0;i<1_000_000;i++){
            new Main0702();
//        }
        System.out.printf("expected = %d, unexpected = %d. Duration = %d\n",
                expected.get(),
                unexpected.get(),
                System.currentTimeMillis() - time);
    }
}
