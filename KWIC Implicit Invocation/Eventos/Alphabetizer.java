/*
 Sofia Alejandra Vargas Flores & Oscar Fernando Hernandez Lopez
 12/Marzo/2025
 Clase Alphabetizer
 Ordenar los desplazamientos generados alfabéticamente
 */
package Eventos;

import java.util.Set;
import java.util.TreeSet;

public class Alphabetizer implements EventListener {
    private MasterControl eventManager;
    private Set<String> shifts = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

    public Alphabetizer(MasterControl eventManager) {
        this.eventManager = eventManager;
        eventManager.subscribe("shift_generated", this);
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType().equals("shift_generated")) {
            shifts.add((String) event.getData());  
            System.out.println("[Alphabetizer] Agregado desplazamiento a la lista");
        }
    }

    public void publishSortedShifts() {
        for (String shift : shifts) {
            eventManager.publish(new Event("sorted_shift", shift));
            System.out.println("[Alphabetizer] Publicado desplazamiento ordenado: " + shift);
        }
    }
}