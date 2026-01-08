package Teste3;

// NMEC: 125913
// NOME: Tiago Vale

public class SessaoPC_AB {
    public static void main(String[] args) {
        // Parte A: Builder & Abstract Factory
        System.out.println("\n--- Part A: Building Computers ---");

        // Director ou método que orquestra o Builder
        ComputerShop shop = new ComputerShop();
        
        // Criar usando componentes Intel
        ComputerBuilder intelBuilder = new SpecializedComputerBuilder(new IntelFactory());
        shop.constructGamingPC(intelBuilder);
        Computer pc1 = intelBuilder.getResult();
        pc1.boot();

        // Criar usando componentes AMD
        ComputerBuilder amdBuilder = new SpecializedComputerBuilder(new AMDFactory());
        shop.constructOfficePC(amdBuilder);
        Computer pc2 = amdBuilder.getResult();
        pc2.boot();

        // Parte B: Verificação de Custos
        System.out.println("\n--- Part B: Price Calculation ---");
        System.out.println("PC1 Total Price: " + pc1.getPrice() + "€");
        System.out.println("PC2 Total Price: " + pc2.getPrice() + "€");
    }
}