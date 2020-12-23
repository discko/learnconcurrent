package space.wudi.learnconcurrent.basicconcurrent.test02;

import java.util.ArrayList;

/**
 * volatile can prevent thread cache,
 * but cannot make variable atomic
 */
@SuppressWarnings("all")
public class Main0203 {
    private volatile int a = 0;
    private /*synchronized*/ void fun(){
        for(int i = 0;i<10000;i++){
            a++;
        }
    }

    public static void main(String[] args) {
        Main0203 m = new Main0203();
        ArrayList<Thread> threads = new ArrayList<>(10);
        for(int i=0;i<10;i++){
            threads.add(new Thread(m::fun));
        }
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(m.a);

    }
}
