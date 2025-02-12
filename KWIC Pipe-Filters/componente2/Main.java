package componente2;

import componente1.Reader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            // Leer las líneas del archivo usando el Reader
            List<String> lines = Reader.readInput();

            // Crear una instancia de Rotacion y generar las rotaciones
            Rotacion rotacion = new Rotacion();
            List<String> rotatedLines = rotacion.process(lines);

            // Imprimir las rotaciones generadas
            System.out.println("=== Rotaciones Generadas ===");
            for (String line : rotatedLines) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
