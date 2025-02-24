import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Salida implements Runnable {
    private final Pipe inputQueue;
    private final String outputFilePath = "KWIC Pipe-Filters/KWIC5/Salida.txt"; // Ruta del archivo de salida

    public Salida(Pipe inputQueue) {
        this.inputQueue = inputQueue;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            while (true) {
                String rotation = inputQueue.take(); // Tomar rotación de la cola anterior
                if (rotation.equals("EOF")) {
                    System.out.println("[Salida] Fin de archivo. Resultados guardados en: " + outputFilePath);
                    break; // Finalizar cuando se reciba el marcador EOF
                }
                writer.write(rotation);
                writer.newLine(); // Agregar salto de línea entre cada rotación
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
