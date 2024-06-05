package devise;

public class test1 {
    static int i=0;
    public static void main(String[] args) throws Exception{
        Thread t1=new Thread(()->{
            for (int a=0; a<5000; a++){
                i++;
            }
            System.out.println("t1:"+i);
        });
        Thread t2=new Thread(()->{
            for (int a=0; a<5000; a++){
                i--;
            }
            System.out.println("t2:"+i);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
        Integer s=1;

    }
}
