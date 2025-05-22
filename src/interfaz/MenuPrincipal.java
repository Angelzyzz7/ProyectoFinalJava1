package interfaz;

import base_datos.ArchivoEmpleados;
import estructuras.ListaLigada;
import estructuras.Pila;     // ✅ Agrega esta
import estructuras.Cola;     // ✅ Si estás usando cola

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {

    private ListaLigada listaEmpleados;
    private Pila pilaHistorial = new Pila();        // ← Aquí va
    private Cola colaEdiciones = new Cola();
    // ← También este (si usas cola)


    public MenuPrincipal() {
        setTitle("Menú Principal - SGN");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        listaEmpleados = ArchivoEmpleados.cargarEmpleados();
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel lblTitulo = new JLabel("Sistema de Gestión de Nómina", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lblTitulo);

        JButton btnRegistrar = new JButton("Registrar Empleado");
        btnRegistrar.addActionListener(this::abrirRegistroEmpleado);
        panel.add(btnRegistrar);

        JButton btnMostrar = new JButton("Mostrar Nómina");
        btnMostrar.addActionListener(e -> new MostrarNomina(listaEmpleados).setVisible(true));
        panel.add(btnMostrar);

        JButton btnActualizar = new JButton("Actualizar Empleado");
        btnActualizar.addActionListener(e -> new ActualizarEmpleado(listaEmpleados, colaEdiciones).setVisible(true));
        panel.add(btnActualizar);


        JButton btnEliminar = new JButton("Eliminar Empleado");
        btnEliminar.addActionListener(e -> new EliminarEmpleado(listaEmpleados, pilaHistorial).setVisible(true));
        panel.add(btnEliminar);


        JButton btnPila = new JButton("Ver Historial (Pila)");
        btnPila.addActionListener(e -> new HistorialPila(pilaHistorial).setVisible(true));
        panel.add(btnPila);


        JButton btnCola = new JButton("Ver Cola de Ediciones");
        btnCola.addActionListener(e -> new ColaEdicionesVista(colaEdiciones).setVisible(true));
        panel.add(btnCola);


        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.addActionListener(e -> {
            new Login().setVisible(true);
            dispose();
        });
        panel.add(btnSalir);

        add(panel);
    }

    private void abrirRegistroEmpleado(ActionEvent e) {
        new RegistroEmpleado(listaEmpleados).setVisible(true);
    }
}
