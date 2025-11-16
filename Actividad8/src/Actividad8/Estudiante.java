package Actividad8;
public class Estudiante extends Persona {
    private String curso;

    public Estudiante(String nombre, String apellidos, String numeroIdentificacion, String estadoCivil, String curso) {
        super(nombre, apellidos, numeroIdentificacion, estadoCivil);
        this.curso = curso;
    }

    public void matricularNuevoCurso(String nuevoCurso) {
        this.curso = nuevoCurso;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Tipo: Estudiante\n" +
               "Curso: " + curso;
    }
}