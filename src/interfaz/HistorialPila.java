package interfaz;

import estructuras.Pila;
import modelo.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistorialPila extends JFrame {

    private Pila pila;

    public HistorialPila(Pila pilaHistorial) {
        this.pila = pilaHistorial;

        setTitle("Historial de Empleados Eliminados");
        setSize(900, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{
                "ID", "Nombre Completo", "Sexo", "Edad", "Teléfono",
                "Puesto", "Departamento", "Horas", "Costo/Hora", "Sueldo"
        }, 0);

        for (Empleado emp : pila.listar()) {
            modelo.addRow(new Object[]{
                    emp.getId(),
                    emp.getNombreCompleto(),
                    emp.getSexo(),
                    emp.getEdad(),
                    emp.getTelefono(),
                    emp.getPuesto(),
                    emp.getDepartamento(),
                    emp.getHorasTrabajadas(),
                    emp.getCostoHora(),
                    emp.getSueldo()
            });
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);
    }
}
