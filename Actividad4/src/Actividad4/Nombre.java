package Actividad4;

import java.util.Scanner;

public class Nombre {
    protected String nombre;
    protected String primerApellido;
    protected String segundoApellido;

    public Nombre() {
        this.nombre = "";
        this.primerApellido = "";
        this.segundoApellido = "";
    }

    public void leerNombre() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre: ");
        this.nombre = scanner.nextLine();
        System.out.print("Ingrese el primer apellido: ");
        this.primerApellido = scanner.nextLine();
        System.out.print("Ingrese el segundo apellido: ");
        this.segundoApellido = scanner.nextLine();
    }

    public void mostrar() {
        System.out.println("Nombre completo: " + nombre + " " + primerApellido + " " + segundoApellido);
    }
}