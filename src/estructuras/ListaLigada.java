package estructuras;

import modelo.Empleado;
import java.util.ArrayList;
import java.util.List;

public class ListaLigada {
    private Nodo cabeza;

    public ListaLigada() {
        this.cabeza = null;
    }

    public void insertarOrdenado(Empleado empleado) {
        Nodo nuevo = new Nodo(empleado);
        if (cabeza == null || empleado.getNombreCompleto().compareToIgnoreCase(cabeza.empleado.getNombreCompleto()) < 0) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            return;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null &&
                empleado.getNombreCompleto().compareToIgnoreCase(actual.siguiente.empleado.getNombreCompleto()) > 0) {
            actual = actual.siguiente;
        }
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
    }

    public Empleado buscarPorId(String id) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.empleado.getId().equalsIgnoreCase(id)) {
                return actual.empleado;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean eliminarPorId(String id) {
        if (cabeza == null) return false;

        if (cabeza.empleado.getId().equalsIgnoreCase(id)) {
            cabeza = cabeza.siguiente;
            return true;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.empleado.getId().equalsIgnoreCase(id)) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }

    public List<Empleado> listar() {
        List<Empleado> lista = new ArrayList<>();
        Nodo actual = cabeza;
        while (actual != null) {
            lista.add(actual.empleado);
            actual = actual.siguiente;
        }
        return lista;
    }
}
