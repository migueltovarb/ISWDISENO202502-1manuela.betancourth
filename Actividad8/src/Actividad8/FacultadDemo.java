package Actividad8;
public class FacultadDemo {
    public static void main(String[] args) {
        // Crear estudiante
        Estudiante estudiante = new Estudiante("Juan", "Pérez", "12345678A", "Soltero", "Ingeniería Informática");
        System.out.println("=== ESTUDIANTE ===");
        System.out.println(estudiante);

        // Cambiar estado civil y matricular en nuevo curso
        estudiante.cambiarEstadoCivil("Casado");
        estudiante.matricularNuevoCurso("Ingeniería de Software");
        System.out.println("\nDespués de cambios:");
        System.out.println(estudiante);

        // Crear profesor
        Profesor profesor = new Profesor("María", "García", "87654321B", "Casada", 2010, "A101", "Matemáticas");
        System.out.println("\n=== PROFESOR ===");
        System.out.println(profesor);

        // Cambiar departamento y despacho
        profesor.cambiarDepartamento("Arquitectura");
        profesor.reasignarDespacho("B202");
        System.out.println("\nDespués de cambios:");
        System.out.println(profesor);

        // Crear personal de servicio
        PersonalServicio personal = new PersonalServicio("Carlos", "López", "11223344C", "Soltero", 2015, "C303", "Biblioteca");
        System.out.println("\n=== PERSONAL DE SERVICIO ===");
        System.out.println(personal);

        // Trasladar a nueva sección
        personal.trasladarSeccion("Secretaría");
        System.out.println("\nDespués de traslado:");
        System.out.println(personal);
    }
}