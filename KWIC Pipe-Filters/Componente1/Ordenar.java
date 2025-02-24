import java.io.*;
import java.util.PriorityQueue;

public class Ordenar implements Runnable {
    private PipedInputStream entrada;
    private PipedOutputStream salida;
    private PriorityQueue<String> colaOrdenada;

    public Ordenar(PipedInputStream entrada, PipedOutputStream salida) {
        this.entrada = entrada;
        this.salida = salida;
        this.colaOrdenada = new PriorityQueue<>(String.CASE_INSENSITIVE_ORDER);
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(entrada));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(salida));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[Ordenar] Recibida rotación: " + line);

                // Agregar a la cola ordenada y escribir el primer elemento ordenado
                colaOrdenada.add(line);
                String menor = colaOrdenada.poll(); // Obtener el menor ordenado
                if (menor != null) {
                    System.out.println("[Ordenar] Enviando ordenado: " + menor);
                    writer.write(menor + "\n");
                    writer.flush();
                }
            }

            // Asegurar que se envían todas las líneas restantes
            while (!colaOrdenada.isEmpty()) {
                writer.write(colaOrdenada.poll() + "\n");
                writer.flush();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
