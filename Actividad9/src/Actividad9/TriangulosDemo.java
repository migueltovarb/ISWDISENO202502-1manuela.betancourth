package Actividad9;
public class TriangulosDemo {
    public static void main(String[] args) {
        // Crear triángulo escaleno
        Escaleno escaleno = new Escaleno(3, 4, 5);
        System.out.println("=== TRIÁNGULO ESCALENO ===");
        System.out.println(escaleno);
        System.out.println("Área con hipotenusa (lado=4, hipotenusa=5): " + escaleno.calcularAreaConHipotenusa(4, 5));

        // Crear triángulo acutángulo
        Acutangulo acutangulo = new Acutangulo(5, 6, 7);
        System.out.println("\n=== TRIÁNGULO ACUTÁNGULO ===");
        System.out.println(acutangulo);
        System.out.println("Área con hipotenusa (lado=6, hipotenusa=10): " + acutangulo.calcularAreaConHipotenusa(6, 10));
    }
}