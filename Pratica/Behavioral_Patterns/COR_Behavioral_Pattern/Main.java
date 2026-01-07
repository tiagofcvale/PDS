package COR_Behavioral_Pattern;

public class Main {
    public static void main(String[] args) {
        Handler cadeia =
            new AutenticacaoHandler()
            .definirProximo(new AutorizacaoHandler())
            .definirProximo(new LoggingHandler());

        cadeia.tratar("pedido_normal");
    }
}
