package devise;

public class TowPhaseTermination {
    public Thread thread;

    public void start(){
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    boolean interrupted = Thread.currentThread().isInterrupted();
                    if(interrupted){
                        System.out.println("线程打断,处理后事");
                        break;
                    }
                    try {
                        System.out.println("执行监控");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // throw new RuntimeException(e);  //向外抛出异常会导致线程终止
                        e.printStackTrace();  //内部处理异常线程继续执行
                        thread.interrupt();  //重置打断标记
                    }
                }
            }
        });
        thread.start();
    }

    public void stop(){
        thread.interrupt();
    }
}
