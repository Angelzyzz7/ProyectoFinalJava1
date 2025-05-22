package estructuras;

import modelo.Empleado;

public class Nodo {
    public Empleado empleado;
    public Nodo siguiente;

    public Nodo(Empleado empleado) {
        this.empleado = empleado;
        this.siguiente = null;
    }
}
