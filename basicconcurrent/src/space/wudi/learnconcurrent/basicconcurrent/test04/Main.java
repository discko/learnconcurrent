package space.wudi.learnconcurrent.basicconcurrent.test04;

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
