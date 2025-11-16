package Actividad11;
public class EntregablesDemo {
    public static void main(String[] args) {
        // Crear series
        Serie serie1 = new Serie("Breaking Bad", 5, "Drama", "Vince Gilligan");
        Serie serie2 = new Serie("The Office", 9, "Comedia", "Greg Daniels");

        // Crear videojuegos
        Videojuego juego1 = new Videojuego("The Witcher 3", 200, "RPG", "CD Projekt");
        Videojuego juego2 = new Videojuego("FIFA 2023", 50, "Deportes", "EA Sports");

        // Operaciones con series
        System.out.println("=== SERIES ===");
        System.out.println("Serie 1: " + serie1);
        System.out.println("Serie 2: " + serie2);

        serie1.entregar();
        System.out.println("Serie 1 entregada: " + serie1.isEntregado());

        serie1.devolver();
        System.out.println("Serie 1 devuelta: " + serie1.isEntregado());

        System.out.println("Comparación series (temporadas): " + serie1.compareTo(serie2));

        // Operaciones con videojuegos
        System.out.println("\n=== VIDEOJUEGOS ===");
        System.out.println("Juego 1: " + juego1);
        System.out.println("Juego 2: " + juego2);

        juego2.entregar();
        System.out.println("Juego 2 entregado: " + juego2.isEntregado());

        System.out.println("Comparación juegos (horas): " + juego1.compareTo(juego2));
    }
}