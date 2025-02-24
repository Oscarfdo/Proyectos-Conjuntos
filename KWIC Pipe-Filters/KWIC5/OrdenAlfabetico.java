
package KWIC5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdenAlfabetico implements Runnable {
    private final Pipe inputQueue;
    private final Pipe outputQueue;

    public OrdenAlfabetico(Pipe inputQueue, Pipe outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        List<String> rotations = new ArrayList<>();
        try {
            while (true) {
                String rotation = inputQueue.take(); // Tomar rotación de la cola anterior
                if (rotation.equals("EOF")) {
                    System.out.println("[OrdenAlfabetico] Ordenando rotaciones");
                    Collections.sort(rotations, String.CASE_INSENSITIVE_ORDER); // Ordenar rotaciones
                    for (String sortedRotation : rotations) {
                        System.out.println("[OrdenAlfabetico] Rotación ordenada: " + sortedRotation);
                        outputQueue.put(sortedRotation); // Enviar rotación ordenada al siguiente filtro
                    }
                    outputQueue.put("EOF"); // Marcar el final del archivo
                    System.out.println("[OrdenAlfabetico] Fin de archivo");
                    break;
                }
                System.out.println("[OrdenAlfabetico] Agregando rotación a la lista: " + rotation);
                rotations.add(rotation);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}