public class EmStock implements IState {
    @Override
    public void licitar(Produto p) {
        System.out.println("Produto em stock não pode ser licitado");
    }

    @Override
    public void passarParaLeilao(Produto p) {
        p.setEstado(new EmLeilao());
        p.setInicioLeilao(System.currentTimeMillis() / 1000);
    }

    @Override
    public String toString() {
        return "EmStock";
    }
}
