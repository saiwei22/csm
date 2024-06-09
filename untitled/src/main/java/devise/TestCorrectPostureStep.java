package devise;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 小明没烟，要抽烟才能干活
 * 小红饿了，要吃外卖才能干活。
 * 但是在同一个房间休息小明抽烟会有烟味，小红吃不下。
 * 所以分了两房间
 *
 */

public class TestCorrectPostureStep {

    static ReentrantLock lock=new ReentrantLock();
    static Condition c1=lock.newCondition();//房间c1
    static Condition c2=lock.newCondition();//房间c2
    static boolean cigarette =false;
    static boolean takeout =false;

    public static void main(String[] args){
        //小明
        Thread t1=new Thread(()->{
            lock.lock();
            try {
                while (!cigarette){
                    System.out.println("烟没到休息一会");
                    try {
                        c1.await(); //让小明到c1休息室休息
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("烟到了,开始干活");
            } finally {
                lock.unlock();
            }
        });
        //小红
        Thread t2 =new Thread(()->{
            lock.lock();
            try {
                while (!takeout){
                    System.out.println("外卖没到休息一会");
                    try {
                        c2.await();//让小红到c2休息室休息
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("外卖到了,开始干活");
            }finally {
                lock.unlock();
            }
        });

        //送外卖线程
        Thread t3=new Thread(()->{
            lock.lock();
            try {
                Thread.sleep(2000);
                cigarette=true;
                c1.signal();//唤醒小红让小明干活
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }
        });
        //送烟线程
        Thread t4=new Thread(()->{
            lock.lock();
            try {
                Thread.sleep(2000);
                takeout=true;
                c2.signal(); //唤醒小明让小明干活
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

}

