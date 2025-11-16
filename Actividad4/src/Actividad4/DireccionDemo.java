package Actividad4;

public class DireccionDemo {
    public static void main(String[] args) {
        Direccion direccion = new Direccion();

        System.out.println("Ingrese los datos del nombre:");
        direccion.nuevoNombre();

        System.out.println("\nIngrese los datos de la dirección:");
        direccion.nuevaDireccion();

        System.out.println("\n=== Información completa ===");
        direccion.mostrar();
    }
}