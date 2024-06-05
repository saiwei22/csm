package devise;

public class TowPhaseTermination {

    Thread monitor;

    public void start() {
        monitor=new Thread(()->{
            while (true){
            Thread thread = Thread.currentThread();
                if(thread.isInterrupted()){
                    System.out.println("料理后事");
                    break;
                }
//                try {
//                    Thread.sleep(1000);
//                    System.out.println("执行任务");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    monitor.interrupt();
//                }

        }
        });
        monitor.start();
    }
    public void stop(){
        monitor.interrupt();
    }
}
