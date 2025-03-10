package Eventos;

public class Output implements EventListener {
    public Output(MasterControl eventManager) {
        eventManager.subscribe("sorted_shift", this);
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType().equals("sorted_shift")) {
            System.out.println(event.getData());
        }
    }
}
