package Actividad5;
public class GeometriaDemo {
    public static void main(String[] args) {
        // Crear y mostrar círculo
        System.out.println("=== CÍRCULO ===");
        Circulo circulo = new Circulo();
        circulo.leerRadio();
        circulo.mostrar();

        System.out.println("\n=== CILINDRO ===");
        Cilindro cilindro = new Cilindro();
        cilindro.leerRadio();
        cilindro.leerAltura();
        cilindro.mostrar();

        System.out.println("\n=== CILINDRO HUECO ===");
        CilindroHueco cilindroHueco = new CilindroHueco();
        cilindroHueco.leerRadio();
        cilindroHueco.leerAltura();
        cilindroHueco.leerRadioInterno();
        cilindroHueco.mostrar();
    }
}