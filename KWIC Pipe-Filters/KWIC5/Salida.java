package KWIC5;

public class Salida implements Runnable {
    private final Pipe inputQueue;

    public Salida(Pipe inputQueue) {
        this.inputQueue = inputQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String rotation = inputQueue.take(); // Tomar rotación de la cola anterior
                if (rotation.equals("EOF")) {
                    System.out.println("[Salida] Fin de archivo");
                    break; // Finalizar cuando se reciba el marcador EOF
                }
                System.out.println("[Salida] Rotación final: ");
                System.out.println(rotation); // Mostrar rotación
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
