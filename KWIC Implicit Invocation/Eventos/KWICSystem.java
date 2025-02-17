package Eventos;

import java.util.List;


public class KWICSystem {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();

        InputReader inputReader = new InputReader(eventManager);
        Rotacion shifter = new Rotacion(eventManager);
        Alphabetizer alphabetizer = new Alphabetizer(eventManager);
        Salida outputWriter = new Salida(eventManager);

        List<String> lines = List.of("Hola mundo", "Java es genial");
        inputReader.readInput(lines);
        alphabetizer.sortAndPublish();
    }
}
