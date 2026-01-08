package Teste2;

public abstract class DocumentDecorator implements IDocument {
    protected IDocument document;
    
    public DocumentDecorator(IDocument document) {
        this.document = document;
    }

    @Override
    public void process() {
        document.process();
    }

    @Override
    public String getDescrição() {
        return document.getDescrição(); // Delega a descrição original
    }
}