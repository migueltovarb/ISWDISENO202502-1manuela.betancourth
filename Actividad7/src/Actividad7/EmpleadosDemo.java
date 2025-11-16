package Actividad7;
public class EmpleadosDemo {
    public static void main(String[] args) {
        // Crear empleados
    	Secretario secretario = new Secretario(
    	        "Lucia", "Martinez", "12345678Z", "Av. Libertad 10",
    	        "600123456", 21000, "Oficina 2", "910203040"
    	);

    	Vendedor vendedor = new Vendedor(
    	        "Andres", "Lopez", "23456789X", "Calle Central 15",
    	        "600234567", 19000,
    	        "DEF456", "Renault", "Sandero",
    	        "611222333", "Barcelona", 6.5
    	);

    	JefeZona jefe = new JefeZona(
    	        "Sofia", "Ramirez", "34567890Y", "Paseo del Sol 8",
    	        "600345678", 36000,
    	        "Oficina Principal", "GHI890",
    	        "Audi", "A4"
    	);
        // Asignar supervisor al vendedor
        vendedor.cambiarSupervisor(jefe);

        // Asignar secretario al jefe
        jefe.cambiarSecretario(secretario);

        // Dar de alta vendedor al jefe
        jefe.darAltaVendedor(vendedor);

        // Mostrar información inicial
        System.out.println("=== INFORMACIÓN INICIAL ===");
        System.out.println(secretario);
        System.out.println("\n" + vendedor);
        System.out.println("\n" + jefe);

        // Incrementar salarios
        System.out.println("\n=== INCREMENTANDO SALARIOS ===");
        secretario.incrementarSalario();
        vendedor.incrementarSalario();
        jefe.incrementarSalario();

        System.out.println("Salario secretario después de incremento: " + secretario.salario);
        System.out.println("Salario vendedor después de incremento: " + vendedor.salario);
        System.out.println("Salario jefe después de incremento: " + jefe.salario);

        // Operaciones con vendedor
        System.out.println("\n=== OPERACIONES CON VENDEDOR ===");
        vendedor.darAltaCliente("Cliente 1");
        vendedor.darAltaCliente("Cliente 2");
        System.out.println("Clientes del vendedor: " + vendedor.toString());

        vendedor.darBajaCliente("Cliente 1");
        System.out.println("Después de dar baja cliente: " + vendedor.toString());

        vendedor.cambiarCoche("DEF456", "Honda", "Civic");
        System.out.println("Después de cambiar coche: " + vendedor.toString());
    }
}