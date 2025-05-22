package interfaz;

import base_datos.ArchivoEmpleados;
import estructuras.ListaLigada;
import estructuras.Pila;
import modelo.Empleado;

import javax.swing.*;
import java.awt.*;

public class EliminarEmpleado extends JFrame {

    private ListaLigada lista;
    private Pila pilaHistorial;
    private JTextField campoId;

    public EliminarEmpleado(ListaLigada lista, Pila pila) {
        this.lista = lista;
        this.pilaHistorial = pila;

        setTitle("Eliminar Empleado");
        setSize(400, 180);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel fila = new JPanel(new FlowLayout());
        fila.add(new JLabel("ID del Empleado a eliminar:"));
        campoId = new JTextField(10);
        fila.add(campoId);
        panel.add(fila);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarEmpleado());
        panel.add(btnEliminar);

        add(panel);
    }

    private void eliminarEmpleado() {
        String id = campoId.getText().trim();
        Empleado emp = lista.buscarPorId(id);

        if (emp == null) {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar al empleado: " + emp.getNombreCompleto() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean eliminado = lista.eliminarPorId(id);
            if (eliminado) {
                pilaHistorial.push(emp);
                ArchivoEmpleados.sobrescribirArchivo(lista);
                JOptionPane.showMessageDialog(this, "Empleado eliminado y agregado al historial.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.");
            }
        }
    }
}
