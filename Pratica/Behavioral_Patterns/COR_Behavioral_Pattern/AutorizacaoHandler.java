package COR_Behavioral_Pattern;

public class AutorizacaoHandler extends Handler {
    public void tratar(String pedido) {
        if (pedido.equals("acesso_negado")) {
            System.out.println("Erro: sem permissões");
            return;
        }
        System.out.println("Autorização OK");
        super.tratar(pedido);
    }
}
