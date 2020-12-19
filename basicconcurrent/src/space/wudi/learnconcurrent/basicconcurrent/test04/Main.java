package space.wudi.learnconcurrent.basicconcurrent.test04;

import sun.misc.Contended;

import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.LockSupport;

/**
 * prove the existence of cache line
 */
@SuppressWarnings("all")
public class Main {
    void fun3(Object locker){
        synchronized (locker){
            System.out.println(1);
        }
    }
    synchronized void fun4(){
        System.out.println(2);
    }
}
