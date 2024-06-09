package devise;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 线程1输出a 线程2输出b 线程3 输出c 交替输出abc 5次
 *
 */
public class SyncWaitNotify {
    static ReentrantLock lock = new ReentrantLock();
    static Condition a=lock.newCondition();
    static Condition b=lock.newCondition();
    static Condition c=lock.newCondition();

    static  boolean a1=true;
    static  boolean b1=false;
    static  boolean c1=false;
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            lock.lock();
            int i=5;
            try{
                while (a1){
                    System.out.print("a");
                    i--;
                    a1=false;
                    b1=true;
                    b.signal();
                    if (i==0){
                        break;
                    }
                    a.await();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });
        Thread t2=new Thread(()->{
            lock.lock();
            int i=5;
            try{
                while (b1){
                    i--;
                    System.out.print("b");
                    b1=false;
                    c1=true;
                    c.signal();
                    if (i==0){
                        break;
                    }
                    b.await();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });
        Thread t3=new Thread(()->{
            lock.lock();
            int i=5;
            try{
                while (c1){
                    i--;
                    System.out.println("c");
                    c1=false;
                    a1=true;
                    a.signal();
                    if (i==0){
                        break;
                    }
                    c.await();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
