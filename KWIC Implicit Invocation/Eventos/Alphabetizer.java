package Eventos;

import java.util.ArrayList;
import java.util.List;

public class Alphabetizer implements EventListener {
    private EventManager eventManager;
    private List<String> shifts = new ArrayList<>();

    public Alphabetizer(EventManager eventManager) {
        this.eventManager = eventManager;
        eventManager.subscribe("shift_generated", this);
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType().equals("shift_generated")) {
            shifts.add((String) event.getData());
        }
    }

    public void sortAndPublish() {
        shifts.sort(String.CASE_INSENSITIVE_ORDER);
        for (String shift : shifts) {
            eventManager.publish(new Event("sorted_shift", shift));
        }
    }
}