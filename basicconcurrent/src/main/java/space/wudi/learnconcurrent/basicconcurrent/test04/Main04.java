package space.wudi.learnconcurrent.basicconcurrent.test04;

import org.openjdk.jol.info.ClassLayout;

/**
 * check bytecode of synchronized
 */
@SuppressWarnings("all")
public class Main04 {

    void fun3(Object locker){
        synchronized (locker){
            System.out.println(1);
        }
    }
    synchronized void fun4(){
        System.out.println(2);

    }

}
