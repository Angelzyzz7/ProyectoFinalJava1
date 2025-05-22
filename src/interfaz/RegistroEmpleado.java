package interfaz;

import base_datos.ArchivoEmpleados;
import estructuras.ListaLigada;
import modelo.Empleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistroEmpleado extends JFrame {

    private JTextField tfId, tfNombre, tfApPaterno, tfApMaterno, tfEdad, tfDireccion, tfTelefono;
    private JComboBox<String> cbSexo;
    private JTextField tfPuesto, tfDepartamento, tfHoras, tfCostoHora;

    private ListaLigada lista;

    public RegistroEmpleado(ListaLigada listaLigada) {
        this.lista = listaLigada;

        setTitle("Registrar Nuevo Empleado");
        setSize(500, 550);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JPanel panel = new JPanel(new GridLayout(13, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        tfId = new JTextField();
        tfNombre = new JTextField();
        tfApPaterno = new JTextField();
        tfApMaterno = new JTextField();
        cbSexo = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        tfEdad = new JTextField();
        tfDireccion = new JTextField();
        tfTelefono = new JTextField();
        tfPuesto = new JTextField();
        tfDepartamento = new JTextField();
        tfHoras = new JTextField();
        tfCostoHora = new JTextField();

        panel.add(new JLabel("ID (6 caracteres):"));
        panel.add(tfId);

        panel.add(new JLabel("Nombre:"));
        panel.add(tfNombre);

        panel.add(new JLabel("Apellido Paterno:"));
        panel.add(tfApPaterno);

        panel.add(new JLabel("Apellido Materno:"));
        panel.add(tfApMaterno);

        panel.add(new JLabel("Sexo:"));
        panel.add(cbSexo);

        panel.add(new JLabel("Edad:"));
        panel.add(tfEdad);

        panel.add(new JLabel("Dirección:"));
        panel.add(tfDireccion);

        panel.add(new JLabel("Teléfono:"));
        panel.add(tfTelefono);

        panel.add(new JLabel("Puesto:"));
        panel.add(tfPuesto);

        panel.add(new JLabel("Departamento:"));
        panel.add(tfDepartamento);

        panel.add(new JLabel("Horas trabajadas:"));
        panel.add(tfHoras);

        panel.add(new JLabel("Costo por hora:"));
        panel.add(tfCostoHora);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(this::registrarEmpleado);
        panel.add(btnRegistrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        add(panel);
    }

    private void registrarEmpleado(ActionEvent e) {
        String id = tfId.getText().trim();
        String nombre = tfNombre.getText().trim();
        String apP = tfApPaterno.getText().trim();
        String apM = tfApMaterno.getText().trim();
        String sexo = cbSexo.getSelectedItem().toString();
        String edadStr = tfEdad.getText().trim();
        String direccion = tfDireccion.getText().trim();
        String telefono = tfTelefono.getText().trim();
        String puesto = tfPuesto.getText().trim();
        String departamento = tfDepartamento.getText().trim();
        String horasStr = tfHoras.getText().trim();
        String costoStr = tfCostoHora.getText().trim();

        // Validaciones básicas
        if (id.length() != 6 || lista.buscarPorId(id) != null) {
            JOptionPane.showMessageDialog(this, "ID inválido o ya registrado.");
            return;
        }

        try {
            int edad = Integer.parseInt(edadStr);
            int horas = Integer.parseInt(horasStr);
            double costoHora = Double.parseDouble(costoStr);

            Empleado emp = new Empleado(id, nombre, apP, apM, sexo, edad, direccion, telefono, puesto, departamento, horas, costoHora);
            lista.insertarOrdenado(emp);
            ArchivoEmpleados.guardarEmpleado(emp);

            JOptionPane.showMessageDialog(this, "Empleado registrado exitosamente.");
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Edad, horas trabajadas y costo por hora deben ser numéricos.");
        }
    }
}
