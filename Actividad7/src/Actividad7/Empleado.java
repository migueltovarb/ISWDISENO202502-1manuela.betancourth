package Actividad7;
public class Empleado {
    protected String nombre;
    protected String apellidos;
    protected String dni;
    protected String direccion;
    protected int anosAntiguedad;
    protected String telefono;
    protected double salario;
    protected Empleado supervisor;

    public Empleado(String nombre, String apellidos, String dni, String direccion, String telefono, double salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.salario = salario;
        this.anosAntiguedad = 0;
        this.supervisor = null;
    }

    public void cambiarSupervisor(Empleado supervisor) {
        this.supervisor = supervisor;
    }

    public void incrementarSalario() {
        // Método base, será sobreescrito por subclases
        this.salario *= 1.0; // Sin incremento por defecto
    }

    @Override
    public String toString() {
        String sup = (supervisor != null) ? supervisor.nombre + " " + supervisor.apellidos : "Ninguno";
        return "Nombre: " + nombre + " " + apellidos + "\n" +
               "DNI: " + dni + "\n" +
               "Dirección: " + direccion + "\n" +
               "Teléfono: " + telefono + "\n" +
               "Años de antigüedad: " + anosAntiguedad + "\n" +
               "Salario: " + salario + "\n" +
               "Supervisor: " + sup;
    }
}