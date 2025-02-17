package KWIC5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        // Ruta del archivo de entrada
        String filePath = "C:\\Users\\HP 15\\OneDrive - Instituto Tecnológico de Culiacán\\ts\\Maestria\\Arquitectura\\Programas\\Conjunto\\Proyectos-Conjuntos\\KWIC Pipe-Filters\\Componente2\\Leer.txt";

        // Crear colas para conectar los filtros
        BlockingQueue<String> queue1 = new LinkedBlockingQueue<>();
        BlockingQueue<String> queue2 = new LinkedBlockingQueue<>();
        BlockingQueue<String> queue3 = new LinkedBlockingQueue<>();

        // Crear instancias de los filtros
        Reader inputFilter = new Reader(filePath, queue1);
        Rotacion rotationFilter = new Rotacion(queue1, queue2);
        OrdenAlfabetico sortingFilter = new OrdenAlfabetico(queue2, queue3);
        Salida outputFilter = new Salida(queue3);

        // Crear hilos para cada filtro
        Thread inputThread = new Thread(inputFilter);
        Thread rotationThread = new Thread(rotationFilter);
        Thread sortingThread = new Thread(sortingFilter);
        Thread outputThread = new Thread(outputFilter);

        // Iniciar los hilos
        inputThread.start();
        rotationThread.start();
        sortingThread.start();
        outputThread.start();

        // Esperar a que los hilos terminen
        try {
            inputThread.join();
            rotationThread.join();
            sortingThread.join();
            outputThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
