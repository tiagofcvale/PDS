public class EmVenda implements IState {
    @Override
    public void licitar(Produto p) {
        System.out.println("Produto já vendido");
    }

    @Override
    public void passarParaLeilao(Produto p) {
        System.out.println("Produto já vendido não pode voltar ao leilão");
    }

    @Override
    public String toString() {
        return "EmVenda";
    }
}
