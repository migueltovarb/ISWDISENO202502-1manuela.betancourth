package Actividad8;
public class Empleado extends Persona {
    protected int anoIncorporacion;
    protected String numeroDespacho;

    public Empleado(String nombre, String apellidos, String numeroIdentificacion, String estadoCivil,
                   int anoIncorporacion, String numeroDespacho) {
        super(nombre, apellidos, numeroIdentificacion, estadoCivil);
        this.anoIncorporacion = anoIncorporacion;
        this.numeroDespacho = numeroDespacho;
    }

    public void reasignarDespacho(String nuevoDespacho) {
        this.numeroDespacho = nuevoDespacho;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Año de incorporación: " + anoIncorporacion + "\n" +
               "Número de despacho: " + numeroDespacho;
    }
}