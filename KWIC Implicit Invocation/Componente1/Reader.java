import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Reader {

    // Ruta predeterminada del archivo (estática para acceso fácil)
    private static final String FILE_PATH = "/Users/kueven/Documents/Repositorios/MCC/Proyectos-Conjuntos/KWIC Implicit Invocation/Read.txt";

    // Método para leer el archivo y devolver las líneas
    public static List<String> readInput() throws IOException {
        return Files.readAllLines(Path.of(FILE_PATH)); // Leer archivo y devolver lista
    }
}
