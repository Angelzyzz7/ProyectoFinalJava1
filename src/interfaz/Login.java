package interfaz;

import base_datos.ArchivoDB;
import base_datos.Seguridad;
import modelo.Administrador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private JTextField campoCorreo;
    private JPasswordField campoPassword;
    private JButton btnLogin, btnRegistro;

    public Login() {
        setTitle("Iniciar Sesión - SGN");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(130, 10, 200, 30);
        panel.add(lblTitulo);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(50, 60, 80, 25);
        panel.add(lblCorreo);

        campoCorreo = new JTextField();
        campoCorreo.setBounds(130, 60, 200, 25);
        panel.add(campoCorreo);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 100, 80, 25);
        panel.add(lblPassword);

        campoPassword = new JPasswordField();
        campoPassword.setBounds(130, 100, 200, 25);
        panel.add(campoPassword);

        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(130, 140, 120, 30);
        btnLogin.addActionListener(this::iniciarSesion);
        panel.add(btnLogin);

        btnRegistro = new JButton("Registrarse");
        btnRegistro.setBounds(260, 140, 110, 30);
        btnRegistro.addActionListener(e -> {
            RegistroAdmin ventanaRegistro = new RegistroAdmin();
            ventanaRegistro.setVisible(true);
            dispose();
        });
        panel.add(btnRegistro);

        add(panel);
    }

    private void iniciarSesion(ActionEvent e) {
        String correo = campoCorreo.getText().trim();
        String password = new String(campoPassword.getPassword()).trim();

        if (!correo.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Correo inválido.");
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese su contraseña.");
            return;
        }

        String hash = Seguridad.hashSHA256(password);
        Administrador admin = ArchivoDB.verificarLogin(correo, hash);

        if (admin != null) {
            JOptionPane.showMessageDialog(this, "¡Bienvenido, " + admin.getNombreCompleto() + "!");
            new MenuPrincipal().setVisible(true);  // ✅ Abre el menú principal
            dispose();                              // ❌ Cierra la ventana de login
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
