package Actividad2;

public class Circulo {
    private double radio;
    private String color;

    public Circulo(double radio, String color) {
        this.radio = radio;
        this.color = color;
    }

    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }

    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }

    public void mostrarInformacion() {
        System.out.println("Círculo de color " + color + " con radio " + radio);
        System.out.println("Área: " + calcularArea());
        System.out.println("Perímetro: " + calcularPerimetro());
    }

    //  Este es el método que hace que el programa se ejecute
    public static void main(String[] args) {
        Circulo c1 = new Circulo(5, "Rojo");
        c1.mostrarInformacion();
    }
}
