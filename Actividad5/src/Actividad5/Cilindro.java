package Actividad5;

import java.util.Scanner;

public class Cilindro extends Circulo {
    protected double altura;

    public Cilindro() {
        super();
        this.altura = 0;
    }

    public void leerAltura() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la altura del cilindro: ");
        this.altura = scanner.nextDouble();
    }

    public double areaCilindro() {
        // Área lateral + 2 * área de las bases
        return 2 * Math.PI * radio * altura + 2 * area();
    }

    public double volumen() {
        return area() * altura;
    }

    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println("Altura: " + altura);
        System.out.println("Área del cilindro: " + areaCilindro());
        System.out.println("Volumen del cilindro: " + volumen());
    }
}