package devise;

public class test3 {
    public static void main(String[] args) {
        GuardedObject guarded=new GuardedObject();
        Thread t1=new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"开始"+System.currentTimeMillis());
            guarded.get(2000);
            System.out.println(Thread.currentThread().getName()+"结束"+System.currentTimeMillis());
        },"t1");
        Thread t2=new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"开始"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            guarded.complete();
            System.out.println(Thread.currentThread().getName()+"结束"+System.currentTimeMillis());
        },"t2");
        t1.start();
        t2.start();
    }
}
