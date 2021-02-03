package space.wudi.learnconcurrent.basicconcurrent.test02;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * try to prevent thread cache WITHOUT keyword volatile
 */
public class Main0204 {
    @SuppressWarnings("all")
    static class MyAction{
        /*volatile */boolean flag = true;
        Lock lock = new ReentrantLock();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder("123");
        LinkedList<Integer> list = new LinkedList<>();
        LinkedBlockingDeque<Integer> lbq = new LinkedBlockingDeque<>();
        AtomicInteger integer = new AtomicInteger(1);
        int[] src = new int[16];
        int[] tgt = new int[16];
        int a=0;
        void fun() {
            System.out.println(Thread.currentThread().getName()+" fun loop started");

            while (flag) {
//                lock.lock(); lock.unlock();
//                Thread.yield();
//                stringBuffer.append("a");
//                stringBuilder.append("a");
//                list.add(1);
//                lbq.add(1);
//                integer.addAndGet(1);
//                a++;
//                System.out.println("running");
//                System.arraycopy(src, 0, tgt, 0, src.length);
//                stringBuilder.append('a');
//                stringBuilder.setCharAt(0, 'a');
            }
            System.out.println(Thread.currentThread().getName()+" fun loop stopped");
        }

        void funWithTryCatch(){
            System.out.println(Thread.currentThread().getName()+" funWithTryCatch loop started");

            while (flag) {
                try{
                    // do nothing -> cannot stop
//                    throw new Exception();    // throw a exception -> stopped
//                    a++;                      // a++ -> cannot stop
                    list.add(1);                // some
                }catch(Exception e){
//                    a++;
                    list.add(1);
//                }finally {
//                    a++;
//                    list.add(1);
                }
            }
            System.out.println(Thread.currentThread().getName()+" funWithTryCatch loop stopped");
        }

    }

    public static void main(String[] args) {
        MyAction a = new MyAction();
//        Thread t = new Thread(a::fun, "Thread-1");
        Thread t = new Thread(a::funWithTryCatch, "Thread-1");
        t.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.flag = false;
//        System.out.println("change a1.flag to " + a1.flag1);

    }
}
