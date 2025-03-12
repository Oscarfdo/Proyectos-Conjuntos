/*
 Sofia Alejandra Vargas Flores & Oscar Fernando Hernandez Lopez
 12/Marzo/2025
 Clase Event
 CLase que representa un evento con un tipo y datos asociados
 */
package Eventos;

public class Event {
    private String type;
    private Object data;

    public Event(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}