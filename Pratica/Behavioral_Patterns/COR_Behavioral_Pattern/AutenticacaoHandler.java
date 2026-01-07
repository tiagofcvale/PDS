package COR_Behavioral_Pattern;

public class AutenticacaoHandler extends Handler { // Concrete Handler
    public void tratar(String pedido) {
        if (pedido.equals("token_invalido")) {
            System.out.println("Erro: utilizador não autenticado");
            return;
        }
        System.out.println("Autenticação OK");
        super.tratar(pedido);
    }
}
