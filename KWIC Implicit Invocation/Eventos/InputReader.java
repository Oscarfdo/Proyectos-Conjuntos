package Eventos;

import java.util.List;

public class InputReader implements EventListener {
    private EventManager eventManager;

    public InputReader(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void readInput(List<String> lines) {
        for (String line : lines) {
            eventManager.publish(new Event("line_read", line));
        }
    }

    @Override
    public void onEvent(Event event) {
        // No necesita reaccionar a eventos en este caso
    }
}
