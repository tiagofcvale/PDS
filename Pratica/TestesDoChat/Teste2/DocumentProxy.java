package Teste2;

public class DocumentProxy implements IDocument{

    private IDocument documento;
    private User user;


    public DocumentProxy(IDocument documento, User user) {
        this.documento = documento;
        this.user = user;
    }


    @Override
    public void process() {
        if (user.getRole().equals("ADMIN")) {
            documento.process();
        }
    }


    @Override
    public String getDescrição() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDescrição'");
    }
    
}
