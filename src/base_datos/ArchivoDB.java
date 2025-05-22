package base_datos;

import modelo.Administrador;
import java.io.*;
import java.util.*;

public class ArchivoDB {
    private static final String RUTA_ADMIN = "administradores.txt";

    public static List<Administrador> cargarAdministradores() {
        List<Administrador> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ADMIN))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Administrador admin = Administrador.desdeLinea(linea);
                if (admin != null) lista.add(admin);
            }
        } catch (IOException e) {
            // Ignorado si no existe el archivo
        }
        return lista;
    }

    public static void guardarAdministrador(Administrador admin) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ADMIN, true))) {
            bw.write(admin.toArchivo());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar administrador: " + e.getMessage());
        }
    }

    public static boolean existeCorreo(String correo) {
        for (Administrador a : cargarAdministradores()) {
            if (a.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }

    public static Administrador verificarLogin(String correo, String hashPassword) {
        for (Administrador a : cargarAdministradores()) {
            if (a.getCorreo().equalsIgnoreCase(correo) && a.getPasswordHash().equals(hashPassword)) {
                return a;
            }
        }
        return null;
    }
}
