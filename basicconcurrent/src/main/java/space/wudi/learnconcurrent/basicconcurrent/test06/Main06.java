package space.wudi.learnconcurrent.basicconcurrent.test06;

import java.util.concurrent.locks.*;

/**
 * ReentrantReadWriteLock and its degradation
 */
public class Main06 {

    private static long startTime = 0;
    private static int data = 0;
    public static void main(String[] args) {
        startTime= System.currentTimeMillis();
        ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
        new Thread(()->{
            rrwl.writeLock().lock();
            modify();
            rrwl.readLock().lock();
            System.out.println("gained read lock and unlocking write lock" + " time elapsed "+(System.currentTimeMillis() - startTime));
            rrwl.writeLock().unlock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            }
            System.out.println("read data = "+ data +" in "+Thread.currentThread().getName() + " time elapsed "+(System.currentTimeMillis() - startTime));
            rrwl.readLock().unlock();
        }, "ReadWriteThread").start();
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            rrwl.readLock().lock();
            System.out.println("read data = "+ data +" in "+Thread.currentThread().getName() + " time elapsed "+(System.currentTimeMillis() - startTime));
            rrwl.readLock().unlock();
        },"ReadThread1").start();
        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            rrwl.writeLock().lock();
            modify();
            rrwl.writeLock().unlock();
        }, "WriteThread").start();
        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {
            }
            rrwl.readLock().lock();
            System.out.println("read data = "+ data +" in "+Thread.currentThread().getName() + " time elapsed "+(System.currentTimeMillis() - startTime));
            rrwl.readLock().unlock();
        },"ReadThread2").start();
    }

    private static void modify(){
        data++;
        System.out.println("Modified data to "+ data +" in "+Thread.currentThread().getName() + " time elapsed "+(System.currentTimeMillis() - startTime));
    }
}
