package Actividad3;

public class Libro extends PublicacionesDemo {
    private int numeroPaginas;
    private int anioPublicacion;

    public Libro(String titulo, float precio, int numeroPaginas, int anioPublicacion) {
        super(titulo, precio);
        this.numeroPaginas = numeroPaginas;
        this.anioPublicacion = anioPublicacion;
    }

    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println("Número de páginas: " + numeroPaginas);
        System.out.println("Año de publicación: " + anioPublicacion);
    }
}