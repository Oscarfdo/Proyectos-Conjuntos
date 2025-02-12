import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Obtener el contenido del archivo desde Reader
            List<String> lines = Reader.readInput();

            // Imprimir el contenido en Main
            System.out.println("Contenido leído:");
            for (String line : lines) {
                System.out.println();
                System.out.println(line);
            }

            Rotacion rotacion = new Rotacion();
            List<String> rotations = rotacion.process(lines);
            //imprimir las rotaciones
            System.out.println("Rotaciones:");
            for (String rotation : rotations) {
                System.out.println(rotation);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }


        
    }
}
