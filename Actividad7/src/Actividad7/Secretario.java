package Actividad7;
public class Secretario extends Empleado {
    private String despacho;
    private String numeroFax;

    public Secretario(String nombre, String apellidos, String dni, String direccion, String telefono, double salario, String despacho, String numeroFax) {
        super(nombre, apellidos, dni, direccion, telefono, salario);
        this.despacho = despacho;
        this.numeroFax = numeroFax;
    }

    @Override
    public void incrementarSalario() {
        this.salario *= 1.05; // Incremento del 5% anual
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Puesto: Secretario\n" +
               "Despacho: " + despacho + "\n" +
               "Número de fax: " + numeroFax;
    }
}