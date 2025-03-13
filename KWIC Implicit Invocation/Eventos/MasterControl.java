/*
 Sofia Alejandra Vargas Flores & Oscar Fernando Hernandez Lopez
 12/Marzo/2025
 Clase MasterControl
 COntrola la suscripción y publicación de eventos
 */
package Eventos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MasterControl {
    private Map<String, List<EventListener>> listeners = new HashMap<>();

    public void subscribe(String eventType, EventListener listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public void publish(Event event) {
        String eventType = event.getType();
        if (listeners.containsKey(eventType)) {
            for (EventListener listener : listeners.get(eventType)) {
                listener.onEvent(event);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Pedir al usuario el nombre del archivo de entrada (sin extensión)
        System.out.print("Ingrese el nombre del archivo (sin extensión): ");
        String fileName = scanner.nextLine().trim();
        
        // Construcción de nombres de archivos
        String inputFilePath = fileName + ".txt";
        String outputFilePath = fileName + "Output.txt";
        
        MasterControl masterControl = new MasterControl();
        
        // Pasar los nombres de archivo correctamente
        Input inputReader = new Input(masterControl, inputFilePath);
        CircularShift shifter = new CircularShift(masterControl);
        Alphabetizer alphabetizer = new Alphabetizer(masterControl);
        Output outputWriter = new Output(masterControl, outputFilePath);

        // Ejecutar flujo
        inputReader.readInputFromFile();
        alphabetizer.publishSortedShifts();
    }
}