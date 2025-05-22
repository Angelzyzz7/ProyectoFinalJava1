package modelo;

public class Administrador {
    private String nombreCompleto;
    private String correo;
    private String passwordHash;

    public Administrador(String nombreCompleto, String correo, String passwordHash) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.passwordHash = passwordHash;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String toArchivo() {
        return nombreCompleto + ";" + correo + ";" + passwordHash;
    }

    public static Administrador desdeLinea(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 3) return null;
        return new Administrador(partes[0], partes[1], partes[2]);
    }
}
