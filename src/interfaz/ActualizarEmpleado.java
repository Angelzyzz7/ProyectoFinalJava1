package interfaz;

import base_datos.ArchivoEmpleados;
import estructuras.ListaLigada;
import estructuras.Cola;
import modelo.Empleado;

import javax.swing.*;
import java.awt.*;

public class ActualizarEmpleado extends JFrame {

    private ListaLigada lista;
    private Cola colaEdiciones;

    private JTextField campoIdBusqueda;
    private JPanel panelEdicion;
    private JTextField tfDireccion, tfTelefono, tfPuesto, tfDepartamento;

    private Empleado empleadoActual;

    public ActualizarEmpleado(ListaLigada lista, Cola cola) {
        this.lista = lista;
        this.colaEdiciones = cola;

        setTitle("Actualizar Empleado");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelBuscar = new JPanel(new FlowLayout());
        panelBuscar.add(new JLabel("ID del Empleado:"));
        campoIdBusqueda = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarEmpleado());
        panelBuscar.add(campoIdBusqueda);
        panelBuscar.add(btnBuscar);

        panel.add(panelBuscar, BorderLayout.NORTH);

        panelEdicion = new JPanel(new GridLayout(5, 2, 5, 5));
        panelEdicion.setBorder(BorderFactory.createTitledBorder("Editar Información"));
        panelEdicion.setVisible(false);

        tfDireccion = new JTextField();
        tfTelefono = new JTextField();
        tfPuesto = new JTextField();
        tfDepartamento = new JTextField();

        panelEdicion.add(new JLabel("Dirección:"));
        panelEdicion.add(tfDireccion);
        panelEdicion.add(new JLabel("Teléfono:"));
        panelEdicion.add(tfTelefono);
        panelEdicion.add(new JLabel("Puesto:"));
        panelEdicion.add(tfPuesto);
        panelEdicion.add(new JLabel("Departamento:"));
        panelEdicion.add(tfDepartamento);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizarEmpleado());
        panelEdicion.add(new JLabel(""));
        panelEdicion.add(btnActualizar);

        panel.add(panelEdicion, BorderLayout.CENTER);
        add(panel);
    }

    private void buscarEmpleado() {
        String id = campoIdBusqueda.getText().trim();
        Empleado emp = lista.buscarPorId(id);
        if (emp == null) {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
            panelEdicion.setVisible(false);
            return;
        }

        empleadoActual = emp;
        tfDireccion.setText(emp.getDireccion());
        tfTelefono.setText(emp.getTelefono());
        tfPuesto.setText(emp.getPuesto());
        tfDepartamento.setText(emp.getDepartamento());
        panelEdicion.setVisible(true);
    }

    private void actualizarEmpleado() {
        if (empleadoActual == null) return;

        empleadoActual.setDireccion(tfDireccion.getText().trim());
        empleadoActual.setTelefono(tfTelefono.getText().trim());
        empleadoActual.setPuesto(tfPuesto.getText().trim());
        empleadoActual.setDepartamento(tfDepartamento.getText().trim());

        ArchivoEmpleados.sobrescribirArchivo(lista);
        colaEdiciones.encolar(empleadoActual.getId());

        JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
        dispose();
    }
}
