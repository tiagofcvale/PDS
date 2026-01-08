package Teste2;

public class Encryption extends DocumentDecorator {

    public Encryption(IDocument document) {
        super(document);
    }

    @Override
    public void process() {
        // 1. Adiciona o comportamento extra do Decorator
        System.out.println("[Encryption] Decrypting stream...");
        // 2. Chama o comportamento do objeto que está lá dentro
        super.process(); 
    }
}