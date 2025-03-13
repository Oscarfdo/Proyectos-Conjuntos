/*
 Sofia Alejandra Vargas Flores & Oscar Fernando Hernandez Lopez
 12/Marzo/2025
 Clase CircularShift
 Generar los desplazamientos circulares de las oraciones
*/

package Eventos;

public class CircularShift implements EventListener {
    private MasterControl eventManager;

    public CircularShift(MasterControl eventManager) {
        this.eventManager = eventManager;
        eventManager.subscribe("line_read", this);
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType().equals("line_read")) {
            String line = (String) event.getData();
            String[] words = line.split(" ");

            for (int i = 0; i < words.length; i++) {
                String shift = String.join(" ", words);
                System.out.println("[CircularShifter] Generado desplazamiento: " + shift);
                eventManager.publish(new Event("shift_generated", shift));
                
                // Rotar las palabras
                String firstWord = words[0];
                System.arraycopy(words, 1, words, 0, words.length - 1);
                words[words.length - 1] = firstWord;
            }
        }
    }
}
