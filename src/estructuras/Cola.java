package estructuras;

import java.util.LinkedList;
import java.util.List;

public class Cola {
    private LinkedList<String> cola;

    public Cola() {
        cola = new LinkedList<>();
    }

    public void encolar(String id) {
        cola.addLast(id);
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public List<String> listar() {
        return new LinkedList<>(cola);
    }
}
