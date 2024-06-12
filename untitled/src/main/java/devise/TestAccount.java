package devise;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAccount {
    static Account a=new Account(0);
    static AtomicInteger at=new AtomicInteger(a.getM());
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> list=new ArrayList<>();
        for(int i=0;i<10000;i++){
            Thread t=new Thread(()->{
                a.setM(at.addAndGet(1));
            });
            list.add(t);
        }
        list.forEach(Thread::start);
        Thread.sleep(1000);
        System.out.println(a.getM());

    }
}
class Account{
    private int m;
    public int getM() {
        return m;
    }

    public void setM(int m) {
//        AtomicInteger at=new AtomicInteger(this.m);
//        at.getAndAdd(m);
            this.m=m;

    }

    public Account(int m) {
        this.m = m;
    }
}