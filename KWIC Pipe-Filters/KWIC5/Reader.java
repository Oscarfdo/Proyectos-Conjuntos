

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Reader implements Runnable {
    private final String filePath;
    private final Pipe outputQueue;

    public Reader(String filePath, Pipe outputQueue) {
        this.filePath = filePath;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String[] lines = br.lines().toArray(String[]::new);
            for(String line : lines){
                 // Depuración
                String[] sentences = line.split("(?<=[.,])\\s+");

                for (String sentence : sentences) {
                    System.out.println("[Reader] Enviando: " + sentence.trim()); // Depuración
               outputQueue.put(sentence.trim()); // Enviar cada oración individualmente
                }
            }
            outputQueue.put("EOF"); // Marcar el final del archivo
            System.out.println("[Reader] Fin de archivo");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
