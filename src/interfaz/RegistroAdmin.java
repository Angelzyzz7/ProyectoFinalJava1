package interfaz;

import base_datos.ArchivoDB;
import base_datos.Seguridad;
import modelo.Administrador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistroAdmin extends JFrame {

    private JTextField campoNombre, campoCorreo;
    private JPasswordField campoPassword, campoConfirmar;
    private JButton btnRegistrar, btnVolver;

    public RegistroAdmin() {
        setTitle("Registro de Administrador");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Registrar Administrador");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(120, 10, 250, 30);
        panel.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre completo:");
        lblNombre.setBounds(50, 60, 130, 25);
        panel.add(lblNombre);

        campoNombre = new JTextField();
        campoNombre.setBounds(180, 60, 200, 25);
        panel.add(campoNombre);

        JLabel lblCorreo = new JLabel("Correo electrónico:");
        lblCorreo.setBounds(50, 100, 130, 25);
        panel.add(lblCorreo);

        campoCorreo = new JTextField();
        campoCorreo.setBounds(180, 100, 200, 25);
        panel.add(campoCorreo);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 140, 130, 25);
        panel.add(lblPassword);

        campoPassword = new JPasswordField();
        campoPassword.setBounds(180, 140, 200, 25);
        panel.add(campoPassword);

        JLabel lblConfirmar = new JLabel("Confirmar contraseña:");
        lblConfirmar.setBounds(50, 180, 150, 25);
        panel.add(lblConfirmar);

        campoConfirmar = new JPasswordField();
        campoConfirmar.setBounds(180, 180, 200, 25);
        panel.add(campoConfirmar);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(180, 230, 100, 30);
        btnRegistrar.addActionListener(this::registrar);
        panel.add(btnRegistrar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(290, 230, 90, 30);
        btnVolver.addActionListener(e -> {
            new Login().setVisible(true);
            dispose();
        });
        panel.add(btnVolver);

        add(panel);
    }

    private void registrar(ActionEvent e) {
        String nombre = campoNombre.getText().trim();
        String correo = campoCorreo.getText().trim();
        String password = new String(campoPassword.getPassword());
        String confirmar = new String(campoConfirmar.getPassword());

        // Validaciones
        if (nombre.isEmpty() || !nombre.matches("^[a-zA-ZÁÉÍÓÚáéíóúñÑ ]+$")) {
            JOptionPane.showMessageDialog(this, "Nombre inválido.");
            return;
        }

        if (!correo.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Correo inválido.");
            return;
        }

        if (ArchivoDB.existeCorreo(correo)) {
            JOptionPane.showMessageDialog(this, "El correo ya está registrado.");
            return;
        }

        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 8 caracteres, una letra y un número.");
            return;
        }

        if (!password.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
            return;
        }

        String hash = Seguridad.hashSHA256(password);
        Administrador admin = new Administrador(nombre, correo, hash);
        ArchivoDB.guardarAdministrador(admin);

        JOptionPane.showMessageDialog(this, "¡Administrador registrado con éxito!");
        new Login().setVisible(true);
        dispose();
    }
}
