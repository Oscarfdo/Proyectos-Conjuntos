package KWIC5;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Pipe {
    private final BlockingQueue<String> queue;

    public Pipe() {
        this.queue = new LinkedBlockingQueue<>();
    }

    public void put(String data) throws InterruptedException {
        queue.put(data);
    }

    public String take() throws InterruptedException {
        return queue.take();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}