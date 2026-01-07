package Adapter;

public class Main {
    public static void main(String[] args) {
        TomadaUK aparelhoUK = new TomadaUK();

        // O cliente só aceita TomadaEuropeia → usamos um Adapter
        TomadaEuropeia adaptado = new AdaptadorUKparaEU(aparelhoUK);

        adaptado.ligar(); // Funciona mesmo com interface diferente!
    }
}
