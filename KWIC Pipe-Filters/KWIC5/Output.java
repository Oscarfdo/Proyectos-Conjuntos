import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Output implements Runnable {
    private final Pipe inputQueue;
    private final String outputFilePath;

    public Output(Pipe inputQueue) {
        this.inputQueue = inputQueue;

        // Pedir al usuario el nombre del archivo de salida
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo de salida (incluya la extensión .txt): ");
        this.outputFilePath = scanner.nextLine();
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
