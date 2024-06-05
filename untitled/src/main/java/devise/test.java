package devise;

public class test {
    public static void main(String[] args) {
//        TowPhaseTermination t1 = new TowPhaseTermination();
//        t1.start();
//        try {
//            Thread.sleep(3500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        t1.stop();
        Runnable r=()->{
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted){
                    System.out.println("料理后事");
                    break;
                }
            }
        };
        Thread t1=new Thread(r);
        t1.start();
        System.out.println("打断前:"+t1.isInterrupted());
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t1.interrupt();
        System.out.println("打断后:"+t1.isInterrupted());

    }
}
