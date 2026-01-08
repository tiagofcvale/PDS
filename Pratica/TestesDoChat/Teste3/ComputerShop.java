package Teste3;

public class ComputerShop {
    // Receita para um PC de Gaming
    public void constructGamingPC(ComputerBuilder builder) {
        builder.addCPU();
        builder.addGPU();
        builder.addRAM();
    }

    // Receita para um PC de Escritório (ex: sem GPU dedicada)
    public void constructOfficePC(ComputerBuilder builder) {
        builder.addCPU();
        builder.addRAM();
        // Não chama addGPU ou usa uma GPU integrada simples
    }
}