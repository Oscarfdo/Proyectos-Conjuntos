package KWIC5;

import java.util.concurrent.BlockingQueue;

public class Rotacion implements Runnable {
    private final BlockingQueue<String> inputQueue;
    private final BlockingQueue<String> outputQueue;

    public Rotacion(BlockingQueue<String> inputQueue, BlockingQueue<String> outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String line = inputQueue.take(); // Tomar línea de la cola anterior
                if (line.equals("EOF")) {
                    outputQueue.put("EOF"); // Propagar el final del archivo
                    System.out.println("[Rotacion] Fin de archivo");
                    break;
                }
                System.out.println("[Rotacion] Rotando línea: " + line);
                String[] words = line.split(" ");
                for (int i = 0; i < words.length; i++) {
                    StringBuilder rotation = new StringBuilder();
                    for (int j = i; j < words.length; j++) {
                        rotation.append(words[j]).append(" ");
                    }
                    for (int j = 0; j < i; j++) {
                        rotation.append(words[j]).append(" ");
                    }
                    String rotationString = rotation.toString().trim();
                    System.out.println("[Rotacion] Rotación generada: " + rotationString);
                    outputQueue.put(rotationString); // Enviar rotación al siguiente filtro
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}