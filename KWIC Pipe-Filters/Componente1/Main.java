import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Crear tubos para conectar los filtros
        PipedOutputStream salida1 = new PipedOutputStream();
        PipedInputStream entrada1 = new PipedInputStream(salida1);

        PipedOutputStream salida2 = new PipedOutputStream();
        PipedInputStream entrada2 = new PipedInputStream(salida2);

        PipedOutputStream salida3 = new PipedOutputStream();
        PipedInputStream entrada3 = new PipedInputStream(salida3);

        PipedOutputStream salida4 = new PipedOutputStream();
        PipedInputStream entrada4 = new PipedInputStream(salida4);

        // Crear los filtros y ejecutarlos en hilos
        new Thread(new Lector(salida1)).start();
        new Thread(new Rotacion(entrada1, salida2)).start();
        new Thread(new Ordenar(entrada2, salida3)).start();
        new Thread(new Imprimir(entrada3)).start();
    }
}
