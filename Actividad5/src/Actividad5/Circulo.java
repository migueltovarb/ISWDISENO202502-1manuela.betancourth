package Actividad5;

import java.util.Scanner;

public class Circulo {
    protected double radio;

    public Circulo() {
        this.radio = 0;
    }

    public void leerRadio() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el radio del círculo: ");
        this.radio = scanner.nextDouble();
    }

    public double area() {
        return Math.PI * radio * radio;
    }

    public double circunferencia() {
        return 2 * Math.PI * radio;
    }

    public void mostrar() {
        System.out.println("Radio: " + radio);
        System.out.println("Área del círculo: " + area());
        System.out.println("Circunferencia: " + circunferencia());
    }
}