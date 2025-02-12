import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    // Ruta predeterminada del archivo (convertida en estática para acceso fácil)
    private static final String FILE_PATH = "/Users/kueven/Documents/Repositorios/MCC/Proyectos-Conjuntos/KWIC Implicit Invocation/Read.txt";

    // Método para leer el archivo y devolver las frases separadas por puntos y comas
    public static List<String> readInput() throws IOException {
        List<String> lines = Files.readAllLines(Path.of(FILE_PATH)); // Leer archivo
        List<String> processedLines = new ArrayList<>();

        for (String line : lines) {
            // Dividir por punto o coma (manteniéndolos en la frase)
            String[] sentences = line.split("(?<=[.,])\\s*");

            // Agregar cada frase como una nueva línea en la lista
            for (String sentence : sentences) {
                processedLines.add(sentence.trim()); // Agregamos la frase limpia
            }
        }

        return processedLines; // Devolver la lista con frases divididas
    }
}
