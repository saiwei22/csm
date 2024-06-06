package devise;

public class GuardedObject {

    String response;

    public void get(long timeout) {
        synchronized (this){
            long time = System.currentTimeMillis();
            while (response==null){
                try {
                    this.wait(timeout);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                long l = System.currentTimeMillis();
                long l1 = l-time;
                if(l1>timeout){
                    System.out.println("等待超时");
                    break;
                }
            }
            System.out.println("下载完成!");
        }
    }

    public void complete(){

        synchronized (this){
            this.response="文件";
            this.notifyAll();
        }

    }

}
