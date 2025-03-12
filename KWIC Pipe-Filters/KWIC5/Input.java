/*
 Sofia Alejandra Vargas Flores & Oscar Fernando Hernandez Lopez
 12/Marzo/2025
 Clase Input
 Leer el archivo de entrada y dividirlo en oraciones para procesarlas
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input implements Runnable {
    private final String filePath;
    private final Pipe outputQueue;

    public Input(String filePath, Pipe outputQueue) {
        this.filePath = filePath;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader("KWIC Pipe-Filters/KWIC5/" + filePath))) {
            String[] lines = br.lines().toArray(String[]::new);
            for (String line : lines) {
                // Depuración
                String[] sentences = line.split("(?<=[.,])\\s+");

                for (String sentence : sentences) {
                    System.out.println("[Input] Enviando: " + sentence.trim()); // Depuración
                    outputQueue.put(sentence.trim()); // Enviar cada oración individualmente
                }
            }
            outputQueue.put("EOF"); // Marcar el final del archivo
            System.out.println("[Input] Fin de archivo");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
