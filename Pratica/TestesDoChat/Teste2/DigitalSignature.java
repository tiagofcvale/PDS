package Teste2;

public class DigitalSignature extends DocumentDecorator {

    public DigitalSignature(IDocument document) {
        super(document);
    }

    @Override
    public void process() {
        System.out.println("[Signature] Verifying digital certificate...");
        document.process();
    }
}
