package KWIC5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Reader implements Runnable {
    private final String filePath;
    private final BlockingQueue<String> outputQueue;

    public Reader(String filePath, BlockingQueue<String> outputQueue) {
        this.filePath = filePath;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                System.out.println("[Reader] Leyendo linea: " + line);
                outputQueue.put(line); // Enviar línea al siguiente filtro

            }
            outputQueue.put("EOF"); // Marcar el final del archivo
            System.out.println("[Reader] Fin de archivo");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
