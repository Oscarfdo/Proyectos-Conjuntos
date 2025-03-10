package Eventos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Output implements EventListener {
    private String fileName;

    public Output(MasterControl eventManager) {
        // Pedir al usuario el nombre del archivo
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo (sin extensión): ");
        this.fileName = scanner.nextLine() + ".txt";

        eventManager.subscribe("sorted_shift", this);
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType().equals("sorted_shift")) {
            String data = event.getData().toString(); // Convierte a String
            System.out.println(data); // Sigue imprimiendo en la consola
            writeToFile(data);
        }
    }

    private void writeToFile(String data) {
        try (FileWriter writer = new FileWriter(fileName, true)) { // Modo append para no sobrescribir
            writer.write(data + "\n");
            System.out.println("Resultado guardado en " + fileName);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
