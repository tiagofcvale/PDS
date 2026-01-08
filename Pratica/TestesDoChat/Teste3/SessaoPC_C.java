package Teste3;

// NMEC: 125913
// NOME: Tiago Vale

public class SessaoPC_C {
    public static void main(String[] args) {
        System.out.println("\n--- Part C: Component Communication ---");

        ControlUnit controlUnit = new ControlUnit();
        
        GPU graphics = new GPU(controlUnit);
        Fan cooler = new Fan(controlUnit);
        
        // Registrar componentes no mediador
        controlUnit.setGpu(graphics);
        controlUnit.setFan(cooler);

        System.out.println("Simulating GPU heat spike...");
        graphics.generateHeat(); // Deve disparar evento no mediador
    }
}