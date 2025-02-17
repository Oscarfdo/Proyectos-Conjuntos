package Eventos;

public class Salida implements EventListener {
    public Salida(EventManager eventManager) {
        eventManager.subscribe("sorted_shift", this);
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType().equals("sorted_shift")) {
            System.out.println(event.getData());
        }
    }
}
