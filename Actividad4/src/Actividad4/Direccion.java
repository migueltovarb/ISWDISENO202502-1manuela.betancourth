package Actividad4;

import java.util.Scanner;

public class Direccion extends Nombre {
    private String calle;
    private String ciudad;
    private String provincia;
    private String codigoPostal;

    public Direccion() {
        super();
        this.calle = "";
        this.ciudad = "";
        this.provincia = "";
        this.codigoPostal = "";
    }

    public void nuevaDireccion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la calle: ");
        this.calle = scanner.nextLine();
        System.out.print("Ingrese la ciudad: ");
        this.ciudad = scanner.nextLine();
        System.out.print("Ingrese la provincia: ");
        this.provincia = scanner.nextLine();
        System.out.print("Ingrese el código postal: ");
        this.codigoPostal = scanner.nextLine();
    }

    public void nuevoNombre() {
        leerNombre();
    }

    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println("Dirección: " + calle + ", " + ciudad + ", " + provincia + ", " + codigoPostal);
    }
}