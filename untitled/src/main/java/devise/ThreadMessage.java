package devise;

import java.util.LinkedList;

/**
 * 模拟生产者和消费者
 */
public class ThreadMessage {

    public static void main(String[] args) {
        MessageQueue queue=new MessageQueue(2);
        //模拟发送消息
        for (int i = 0; i < 4; i++) {
            int a=i;
            new Thread(() -> {
                Message message=new Message(a,""+a);
                queue.put(message);
            },"t1").start();
        }

        //模拟接收消息


        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                queue.take();
            }
        },"t2").start();


    }
}
class MessageQueue{
    LinkedList<Message> messages=new LinkedList<>();
    //队列容量
    private int city;

    //设置队列容量
    public MessageQueue(int city){
        this.city=city;
    }
    public MessageQueue(){

    }
    //获取消息
    public Message take(){
        synchronized (messages){
            //是否有消息
            while (messages.isEmpty()){
                //没有消息的情况
                try {
                    messages.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //有消息的情况
            Message message = messages.removeFirst();
            System.out.println("已获取消息"+message.getObject());
            messages.notifyAll();
            return message;
        }
    }
    //存入消息
    public void put(Message m){
        //判断消息队列是否已经满了
        synchronized (messages){
            while (messages.size()==city){
                //满了
                try {
                    System.out.println("消息已满");
                    messages.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //未满
            messages.addLast(m);
            System.out.println(m+"消息已存入");
            messages.notifyAll();
        }

    }
}
//消息
class Message{
    //消息id
    private int id;
    //消息内容
    private Object object;
    public Message(int id,Object object){
        this.id=id;
        this.object=object;
    }
    //获取消息
    public Object getObject() {
        return object;
    }
    //获取id
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", object=" + object +
                '}';
    }
}