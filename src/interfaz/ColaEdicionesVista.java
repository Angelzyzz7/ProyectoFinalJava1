package interfaz;

import estructuras.Cola;

import javax.swing.*;
import java.awt.*;

public class ColaEdicionesVista extends JFrame {

    private Cola cola;

    public ColaEdicionesVista(Cola cola) {
        this.cola = cola;

        setTitle("Cola de Ediciones Pendientes");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (String id : cola.listar()) {
            modelo.addElement("Empleado editado con ID: " + id);
        }

        JList<String> lista = new JList<>(modelo);
        JScrollPane scroll = new JScrollPane(lista);

        add(scroll, BorderLayout.CENTER);
    }
}
