package Eventos;

import java.util.ArrayList;
import java.util.List;

public class Rotacion implements EventListener {
    private EventManager eventManager;

    public Rotacion(EventManager eventManager) {
        this.eventManager = eventManager;
        eventManager.subscribe("line_read", this);
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType().equals("line_read")) {
            String line = (String) event.getData();
            List<String> shifts = generateShifts(line);
            for (String shift : shifts) {
                eventManager.publish(new Event("shift_generated", shift));
            }
        }
    }

    private List<String> generateShifts(String line) {
        List<String> shifts = new ArrayList<>();
        String[] words = line.split(" ");
        for (int i = 0; i < words.length; i++) {
            String shift = String.join(" ", words);
            shifts.add(shift);
            // Rotar las palabras
            String firstWord = words[0];
            System.arraycopy(words, 1, words, 0, words.length - 1);
            words[words.length - 1] = firstWord;
        }
        return shifts;
    }
}