package Actividad9;
public abstract class TrianguloBase {
    public abstract double perimetro();

    public double calcularAreaConHipotenusa(int lado, int hipotenusa) {
        // Usando el teorema de Pitágoras: lado² + altura² = hipotenusa²
        // Área = (base * altura) / 2
        // Aquí asumimos que 'lado' es la base y calculamos la altura
        double altura = Math.sqrt(hipotenusa * hipotenusa - lado * lado);
        return (lado * altura) / 2;
    }
}