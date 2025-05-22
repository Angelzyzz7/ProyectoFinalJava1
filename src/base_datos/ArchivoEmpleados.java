package base_datos;

import estructuras.ListaLigada;
import modelo.Empleado;

import java.io.*;
import java.util.List;

public class ArchivoEmpleados {
    private static final String RUTA = "empleados.txt";

    public static ListaLigada cargarEmpleados() {
        ListaLigada lista = new ListaLigada();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Empleado emp = Empleado.desdeLinea(linea);
                if (emp != null) {
                    lista.insertarOrdenado(emp);
                }
            }
        } catch (IOException e) {
            System.out.println("Archivo empleados.txt no encontrado. Se creará uno nuevo al guardar.");
        }
        return lista;
    }

    public static void guardarEmpleado(Empleado emp) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA, true))) {
            bw.write(emp.toArchivo());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar empleado: " + e.getMessage());
        }
    }

    public static void sobrescribirArchivo(ListaLigada lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA))) {
            for (Empleado emp : lista.listar()) {
                bw.write(emp.toArchivo());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al sobrescribir empleados.txt: " + e.getMessage());
        }
    }
}
