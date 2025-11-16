package Actividad5;

import java.util.Scanner;

public class CilindroHueco extends Cilindro {
    private double radioInterno;

    public CilindroHueco() {
        super();
        this.radioInterno = 0;
    }

    public void leerRadioInterno() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el radio interno del cilindro hueco: ");
        this.radioInterno = scanner.nextDouble();
    }

    public double areaCilindroHueco() {
        // Área lateral externa + área lateral interna + área de las bases externas - área de las bases internas
        double areaLateralExterna = 2 * Math.PI * radio * altura;
        double areaLateralInterna = 2 * Math.PI * radioInterno * altura;
        double areaBasesExternas = 2 * Math.PI * radio * radio;
        double areaBasesInternas = 2 * Math.PI * radioInterno * radioInterno;
        return areaLateralExterna + areaLateralInterna + areaBasesExternas - areaBasesInternas;
    }

    public double volumenHueco() {
        return Math.PI * (radio * radio - radioInterno * radioInterno) * altura;
    }

    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println("Radio interno: " + radioInterno);
        System.out.println("Área del cilindro hueco: " + areaCilindroHueco());
        System.out.println("Volumen del cilindro hueco: " + volumenHueco());
    }
}