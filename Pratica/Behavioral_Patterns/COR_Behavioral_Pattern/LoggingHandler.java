package COR_Behavioral_Pattern;

public class LoggingHandler extends Handler{
    public void tratar(String pedido) {
        System.out.println("Log → Pedido processado: " + pedido);
        super.tratar(pedido);
    }
}
