package Actividad7;
import java.util.ArrayList;
import java.util.List;

public class JefeZona extends Empleado {
    private String despacho;
    private Secretario secretario;
    private List<Vendedor> vendedores;
    private String matriculaCoche;
    private String marcaCoche;
    private String modeloCoche;

    public JefeZona(String nombre, String apellidos, String dni, String direccion, String telefono, double salario,
                   String despacho, String matriculaCoche, String marcaCoche, String modeloCoche) {
        super(nombre, apellidos, dni, direccion, telefono, salario);
        this.despacho = despacho;
        this.secretario = null;
        this.vendedores = new ArrayList<>();
        this.matriculaCoche = matriculaCoche;
        this.marcaCoche = marcaCoche;
        this.modeloCoche = modeloCoche;
    }

    @Override
    public void incrementarSalario() {
        this.salario *= 1.20; // Incremento del 20% anual
    }

    public void cambiarSecretario(Secretario secretario) {
        this.secretario = secretario;
    }

    public void cambiarCoche(String matricula, String marca, String modelo) {
        this.matriculaCoche = matricula;
        this.marcaCoche = marca;
        this.modeloCoche = modelo;
    }

    public void darAltaVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void darBajaVendedor(Vendedor vendedor) {
        vendedores.remove(vendedor);
    }

    @Override
    public String toString() {
        String sec = (secretario != null) ? secretario.nombre + " " + secretario.apellidos : "Ninguno";
        return super.toString() + "\n" +
               "Puesto: Jefe de Zona\n" +
               "Despacho: " + despacho + "\n" +
               "Secretario: " + sec + "\n" +
               "Coche: " + matriculaCoche + " " + marcaCoche + " " + modeloCoche + "\n" +
               "Número de vendedores a cargo: " + vendedores.size();
    }
}