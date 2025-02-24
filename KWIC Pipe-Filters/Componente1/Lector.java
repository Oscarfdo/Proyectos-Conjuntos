import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Lector implements Runnable {
    //private static final String FILE_PATH = "/Users/kueven/Documents/Repositorios/MCC/Proyectos-Conjuntos/KWIC Pipe-Filters/componente1/Read.txt";
    private static final String FILE_PATH = "KWIC Pipe-Filters/componente1/Leer.txt";
    private PipedOutputStream salida;

    public Lector(PipedOutputStream salida) {
        this.salida = salida;
    }

    @Override
    public void run() {
        try {
            List<String> lines = Files.readAllLines(Path.of(FILE_PATH));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(salida));

            for (String line : lines) {
                System.out.println("[Lector] Línea leída: " + line); // Depuración
                String[] sentences = line.split("(?<=[.,])\\s*");
                for (String sentence : sentences) {
                    System.out.println("[Lector] Enviando: " + sentence.trim()); // Depuración
                    writer.write(sentence.trim() + "\n"); // Enviar línea por línea
                }
            }
            
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
