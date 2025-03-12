/*
 Sofia Alejandra Vargas Flores & Oscar Fernando Hernandez Lopez
 12/Marzo/2025
 Clase Output
 Guarda los desplazamientos ordenados y genera un archivo de salida
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Output implements Runnable {
    private final Pipe inputQueue;
    private final String outputFilePath;

    public Output(Pipe inputQueue, String inputFileName) {
        this.inputQueue = inputQueue;
        // Generar el nombre del archivo de salida basado en el de entrada
        this.outputFilePath = inputFileName + "Output.txt";
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
