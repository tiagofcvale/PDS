public class EmLeilao implements IState{

    @Override
    public void licitar(Produto p) {
        p.setEstado(null);
    }

    @Override
    public void passarParaLeilao(Produto p) {
        System.out.println("Não é possivel realizar esta operação");
    }

    @Override
    public String toString() {
        return "EmLeilao";
    }
    
}
