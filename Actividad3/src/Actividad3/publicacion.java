package Actividad3;

public class publicacion {
    protected String titulo;
    protected float precio;

    public publicacion(String titulo, float precio) {
        this.titulo = titulo;
        this.precio = precio;
    }

    public void mostrar() {
        System.out.println("Título: " + titulo);
        System.out.println("Precio: " + precio);
    }
}