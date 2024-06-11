package devise;

public class TowPhaseTermination {

    Thread monitor;
    private volatile boolean stop;

    public void start() {
        monitor=new Thread(()->{
            while (true){
                if(stop){
                    System.out.println("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("执行任务");
                } catch (InterruptedException e) {

                }

        }
        });
        monitor.start();
    }
    public void stop(){
        stop=true;
        monitor.interrupt();
    }
}
