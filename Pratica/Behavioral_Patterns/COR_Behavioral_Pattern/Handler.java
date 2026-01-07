package COR_Behavioral_Pattern;

public abstract class Handler {
    private Handler proximo;

    public Handler definirProximo(Handler h){
        this.proximo = h;
        return h;
    }

    public void tratar(String pedido) {
        if (proximo!=null) {
            proximo.tratar(pedido);
        }
    }
}
