package Teste2;

// NMEC: 125913
// NOME: Tiago Vale

public class SessaoDocumentos_C {
    public static void main(String[] args) {
        System.out.println("\n--- Part C: Event Notification ---");

        // Documento que permite observadores
        DocumentManager manager = new DocumentManager("Exame_Final.pdf");
        
        manager.subscribe(new LogManager());
        manager.subscribe(new AnalyticsService());

        System.out.println("Executing manager process:");
        manager.executeProcess();
    }
}