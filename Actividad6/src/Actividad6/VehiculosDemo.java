package Actividad6;
public class VehiculosDemo {
    public static void main(String[] args) {
        // Crear coche
        Coche coche = new Coche("CMS125", 4);
        System.out.println("Coche creado: " + coche.toString());
        System.out.println("Número de puertas: " + coche.getNumeroPuertas());

        // Acelerar coche
        coche.acelerar(50);
        System.out.println("Después de acelerar: " + coche.toString());

        // Crear camión
        Camion camion = new Camion("AZU007");
        System.out.println("\nCamión creado: " + camion.toString());

        // Acelerar camión sin remolque
        camion.acelerar(80);
        System.out.println("Después de acelerar: " + camion.toString());

        // Poner remolque
        Remolque remolque = new Remolque(2000);
        camion.ponRemolque(remolque);
        System.out.println("Con remolque: " + camion.toString());

        // Intentar acelerar demasiado
        camion.acelerar(30); // Debería mostrar mensaje de velocidad excesiva

        // Acelerar dentro del límite
        camion.acelerar(10);
        System.out.println("Después de acelerar dentro del límite: " + camion.toString());

        // Quitar remolque
        camion.quitaRemolque();
        System.out.println("Sin remolque: " + camion.toString());

        // Ahora puede acelerar normalmente
        camion.acelerar(50);
        System.out.println("Después de quitar remolque y acelerar: " + camion.toString());
    }
}