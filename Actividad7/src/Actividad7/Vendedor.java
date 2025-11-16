package Actividad7;
import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Empleado {
    private String matriculaCoche;
    private String marcaCoche;
    private String modeloCoche;
    private String telefonoMovil;
    private String areaVenta;
    private List<String> listaClientes;
    private double porcentajeComision;

    public Vendedor(String nombre, String apellidos, String dni, String direccion, String telefono, double salario,
                   String matriculaCoche, String marcaCoche, String modeloCoche, String telefonoMovil,
                   String areaVenta, double porcentajeComision) {
        super(nombre, apellidos, dni, direccion, telefono, salario);
        this.matriculaCoche = matriculaCoche;
        this.marcaCoche = marcaCoche;
        this.modeloCoche = modeloCoche;
        this.telefonoMovil = telefonoMovil;
        this.areaVenta = areaVenta;
        this.porcentajeComision = porcentajeComision;
        this.listaClientes = new ArrayList<>();
    }

    @Override
    public void incrementarSalario() {
        this.salario *= 1.10; // Incremento del 10% anual
    }

    public void darAltaCliente(String cliente) {
        listaClientes.add(cliente);
    }

    public void darBajaCliente(String cliente) {
        listaClientes.remove(cliente);
    }

    public void cambiarCoche(String matricula, String marca, String modelo) {
        this.matriculaCoche = matricula;
        this.marcaCoche = marca;
        this.modeloCoche = modelo;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Puesto: Vendedor\n" +
               "Coche: " + matriculaCoche + " " + marcaCoche + " " + modeloCoche + "\n" +
               "Teléfono móvil: " + telefonoMovil + "\n" +
               "Área de venta: " + areaVenta + "\n" +
               "Porcentaje de comisión: " + porcentajeComision + "%\n" +
               "Lista de clientes: " + listaClientes;
    }
}