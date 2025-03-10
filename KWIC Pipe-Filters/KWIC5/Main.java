

public class Main {
    public static void main(String[] args) {
        // Ruta del archivo de entrada
        String filePath = "KWIC Pipe-Filters/KWIC5/Leer.txt";

        // Crear colas para conectar los filtros
        Pipe queue1 = new Pipe();
        Pipe queue2 = new Pipe();
        Pipe queue3 = new Pipe();

        // Crear instancias de los filtros
        Input inputFilter = new Input(filePath, queue1);
        CircularShift rotationFilter = new CircularShift(queue1, queue2);
        Alphabetizer sortingFilter = new Alphabetizer(queue2, queue3);
        Output outputFilter = new Output(queue3);

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
