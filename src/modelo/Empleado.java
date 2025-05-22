package modelo;

public class Empleado {
    private String id;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String sexo;
    private int edad;
    private String direccion;
    private String telefono;
    private String puesto;
    private String departamento;
    private int horasTrabajadas;
    private double costoHora;
    private double sueldo;

    public Empleado(String id, String nombre, String apPaterno, String apMaterno,
                    String sexo, int edad, String direccion, String telefono,
                    String puesto, String departamento, int horasTrabajadas, double costoHora) {
        this.id = id;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.sexo = sexo;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.puesto = puesto;
        this.departamento = departamento;
        this.horasTrabajadas = horasTrabajadas;
        this.costoHora = costoHora;
        this.sueldo = horasTrabajadas * costoHora;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public String getNombreCompleto() {
        return nombre + " " + apPaterno + " " + apMaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getPuesto() {
        return puesto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public double getSueldo() {
        return sueldo;
    }

    // Setters (para actualizar datos)
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    // Persistencia
    public String toArchivo() {
        return String.join(";",
                id, nombre, apPaterno, apMaterno, sexo, String.valueOf(edad),
                direccion, telefono, puesto, departamento,
                String.valueOf(horasTrabajadas), String.valueOf(costoHora), String.valueOf(sueldo)
        );
    }

    public static Empleado desdeLinea(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 13) return null;

        return new Empleado(
                partes[0], partes[1], partes[2], partes[3],
                partes[4], Integer.parseInt(partes[5]), partes[6], partes[7],
                partes[8], partes[9], Integer.parseInt(partes[10]),
                Double.parseDouble(partes[11])
        );
    }
}
