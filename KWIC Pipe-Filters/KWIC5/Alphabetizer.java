/*
 Sofia Alejandra Vargas Flores & Oscar Fernando Hernandez Lopez
 12/Marzo/2025
 Clase Alphabetizer
 Ordenar los desplazamientos generados alfabéticamente
 */
import java.util.ArrayList;
import java.util.List;

public class Alphabetizer implements Runnable {
    private final Pipe inputQueue;
    private final Pipe outputQueue;
    private final List<String> sortedLines = new ArrayList<>();  // Lista para mantener las líneas ordenadas

    public Alphabetizer(Pipe inputQueue, Pipe outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String line = inputQueue.take();  // Tomar una línea de la tubería de entrada
                if (line.equals("EOF")) {
                    for (String sortedLine : sortedLines) {
                        outputQueue.put(sortedLine);  // Enviar cada línea ordenada a la tubería de salida
                        System.out.println("[Alphabetizer] Línea ordenada enviada: " + sortedLine);
                    }
                    outputQueue.put("EOF");  // Indicar el fin del procesamiento
                    break;
                }
                insertSorted(line);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void insertSorted(String newLine) {
        int index = 0;
        while (index < sortedLines.size() && newLine.compareToIgnoreCase(sortedLines.get(index)) > 0) {
            index++;
        }
        sortedLines.add(index, newLine);
        System.out.println("[Alphabetizer] Línea insertada en la posición: " + index);
    }
}
