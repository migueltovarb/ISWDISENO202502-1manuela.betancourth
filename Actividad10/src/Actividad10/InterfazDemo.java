package Actividad10;
import java.util.Scanner;

public class InterfazDemo {
    public static void main(String[] args) {
        // Crear auto con 0 gasolina
        Auto auto = new Auto(0);
        Scanner scanner = new Scanner(System.in);

        // Solicitar cantidad de gasolina al usuario
        System.out.print("Ingrese la cantidad de gasolina para tanquear: ");
        int cantidadGasolina = scanner.nextInt();

        // Retanquear el auto
        boolean resultado = auto.retanquear(cantidadGasolina);
        System.out.println("¿Se pudo retanquear? " + resultado);
        System.out.println("Gasolina actual: " + auto.getGasolina());

        // Intentar conducir
        auto.conducir();

        scanner.close();
    }
}