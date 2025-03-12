/*
 Sofia Alejandra Vargas Flores & Oscar Fernando Hernandez Lopez
 12/Marzo/2025
 Clase Pipe
 Clase que implementa un tubo de comunicación entre hilos
 */


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