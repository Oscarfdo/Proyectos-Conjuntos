/*
 Sofia Alejandra Vargas Flores & Oscar Fernando Hernandez Lopez
 12/Marzo/2025
 Clase Output
 Guarda los desplazamientos ordenados y genera un archivo de salida
 */
package Eventos;

import java.io.FileWriter;
import java.io.IOException;

public class Output implements EventListener {
    private String fileName;

    public Output(MasterControl eventManager, String fileName) {
        this.fileName = fileName; 
        eventManager.subscribe("sorted_shift", this);
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType().equals("sorted_shift")) {
            String data = event.getData().toString(); 
            System.out.println(data); 
            
            
            try (FileWriter writer = new FileWriter(fileName, true)) { 
                writer.write(data + "\n");
                System.out.println("Resultado guardado en " + fileName);
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
            }
        }
    }
}

