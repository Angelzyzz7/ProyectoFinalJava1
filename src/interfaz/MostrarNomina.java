package interfaz;

import estructuras.ListaLigada;
import modelo.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MostrarNomina extends JFrame {

    private ListaLigada listaEmpleados;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField campoBuscar;

    public MostrarNomina(ListaLigada lista) {
        this.listaEmpleados = lista;

        setTitle("Nómina de Empleados");
        setSize(900, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblBuscar = new JLabel("Buscar (Nombre o Apellidos): ");
        campoBuscar = new JTextField();
        campoBuscar.addCaretListener(e -> filtrar());

        JPanel panelBuscar = new JPanel(new BorderLayout());
        panelBuscar.add(lblBuscar, BorderLayout.WEST);
        panelBuscar.add(campoBuscar, BorderLayout.CENTER);

        panelSuperior.add(panelBuscar, BorderLayout.NORTH);
        add(panelSuperior, BorderLayout.NORTH);

        // Configurar tabla
        modeloTabla = new DefaultTableModel(new String[]{
                "ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Sexo",
                "Edad", "Dirección", "Teléfono", "Puesto", "Departamento",
                "Horas", "Costo/Hora", "Sueldo"
        }, 0);

        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        cargarTabla();
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        for (Empleado emp : listaEmpleados.listar()) {
            modeloTabla.addRow(new Object[]{
                    emp.getId(),
                    emp.getNombreCompleto().split(" ")[0], // nombre
                    emp.getNombreCompleto().split(" ").length > 1 ? emp.getNombreCompleto().split(" ")[1] : "",
                    emp.getNombreCompleto().split(" ").length > 2 ? emp.getNombreCompleto().split(" ")[2] : "",
                    emp.getSexo(),
                    emp.getEdad(),
                    emp.getDireccion(),
                    emp.getTelefono(),
                    emp.getPuesto(),
                    emp.getDepartamento(),
                    emp.getHorasTrabajadas(),
                    emp.getCostoHora(),
                    emp.getSueldo()
            });
        }
    }

    private void filtrar() {
        String filtro = campoBuscar.getText().toLowerCase();
        modeloTabla.setRowCount(0); // Limpiar tabla

        for (Empleado emp : listaEmpleados.listar()) {
            String nombreCompleto = emp.getNombreCompleto().toLowerCase();
            if (nombreCompleto.contains(filtro)) {
                modeloTabla.addRow(new Object[]{
                        emp.getId(),
                        emp.getNombreCompleto().split(" ")[0],
                        emp.getNombreCompleto().split(" ").length > 1 ? emp.getNombreCompleto().split(" ")[1] : "",
                        emp.getNombreCompleto().split(" ").length > 2 ? emp.getNombreCompleto().split(" ")[2] : "",
                        emp.getSexo(),
                        emp.getEdad(),
                        emp.getDireccion(),
                        emp.getTelefono(),
                        emp.getPuesto(),
                        emp.getDepartamento(),
                        emp.getHorasTrabajadas(),
                        emp.getCostoHora(),
                        emp.getSueldo()
                });
            }
        }
    }
}
