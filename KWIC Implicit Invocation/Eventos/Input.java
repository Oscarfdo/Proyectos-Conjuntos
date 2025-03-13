/*
 Sofia Alejandra Vargas Flores & Oscar Fernando Hernandez Lopez
 12/Marzo/2025
 Clase Input
 Leer el archivo de entrada y dividirlo en oraciones para procesarlas
 */
package Eventos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input {
    private MasterControl eventManager;
    private String filePath;

    public Input(MasterControl eventManager, String filePath) {
        this.eventManager = eventManager;
        this.filePath = filePath;
    }

    public void readInputFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("KWIC Implicit Invocation/Eventos/" + filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("[InputReader] Leyendo línea: " + line.trim());

                // Dividir la línea en oraciones y procesarlas directamente
                String[] sentences = line.split("(?<=[.,!?])\\s+");
                System.out.println("[InputReader] Líneas después del split: " + sentences.length);

                for (String sentence : sentences) {
                    System.out.println("[InputReader] Procesando oración: " + sentence.trim());
                    eventManager.publish(new Event("line_read", sentence.trim()));
                }
            }
            System.out.println("[InputReader] Fin de la lectura del archivo.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + filePath);
            e.printStackTrace();
        }
    }
}
