package Actividad10;
import java.util.Scanner;

public class Auto implements IVehiculo {
    private int gasolina;

    public Auto(int gasolinaInicial) {
        this.gasolina = gasolinaInicial;
    }

    @Override
    public void conducir() {
        if (gasolina > 0) {
            System.out.println("El carro está conduciendo.");
        } else {
            System.out.println("No hay gasolina suficiente para conducir.");
        }
    }

    @Override
    public boolean retanquear(int cantidadGasolina) {
        this.gasolina += cantidadGasolina;
        return true;
    }

    public int getGasolina() {
        return gasolina;
    }
}