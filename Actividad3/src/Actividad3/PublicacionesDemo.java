package Actividad3;

import java.util.Scanner;

public class PublicacionesDemo {
    public PublicacionesDemo(String titulo, float precio) {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear instancia de Libro
        System.out.println("Ingrese los datos del libro:");
        System.out.print("Título: ");
        String tituloLibro = scanner.nextLine();
        System.out.print("Precio: ");
        float precioLibro = scanner.nextFloat();
        System.out.print("Número de páginas: ");
        int numeroPaginas = scanner.nextInt();
        System.out.print("Año de publicación: ");
        int anioPublicacion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Libro libro = new Libro(tituloLibro, precioLibro, numeroPaginas, anioPublicacion);

        // Crear instancia de Disco
        System.out.println("\nIngrese los datos del disco:");
        System.out.print("Título: ");
        String tituloDisco = scanner.nextLine();
        System.out.print("Precio: ");
        float precioDisco = scanner.nextFloat();
        System.out.print("Duración en minutos: ");
        float duracionMinutos = scanner.nextFloat();

        Disco disco = new Disco(tituloDisco, precioDisco, duracionMinutos);

        // Mostrar información
        System.out.println("\n=== Información del Libro ===");
        libro.mostrar();

        System.out.println("\n=== Información del Disco ===");
        disco.mostrar();

        scanner.close();
    }

	public void mostrar() {
		// TODO Auto-generated method stub
		
	}
}