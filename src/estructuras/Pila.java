package estructuras;

import modelo.Empleado;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class Pila {
    private Stack<Empleado> pila;

    public Pila() {
        pila = new Stack<>();
    }

    public void push(Empleado e) {
        pila.push(e);
    }

    public Empleado pop() {
        return pila.isEmpty() ? null : pila.pop();
    }

    public boolean estaVacia() {
        return pila.isEmpty();
    }

    public List<Empleado> listar() {
        return new ArrayList<>(pila);
    }

}
