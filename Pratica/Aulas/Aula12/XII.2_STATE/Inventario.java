
public class Inventario implements IState {

    @Override
    public void registar(Livro l) {
        System.out.println("Livro "+l.getTitle()+" registado com sucesso!");
        l.setState(new Disponivel());
    }

    @Override
    public void requisitar(Livro l) {
        System.out.println("O estado atual do Livro não permite realizar esta operação");
    }

    @Override
    public void devolver(Livro l) {
        System.out.println("O estado atual do Livro não permite realizar esta operação");
    }

    @Override
    public void cancelarReserva(Livro l) {
        System.out.println("O estado atual do Livro não permite realizar esta operação");
    }

    @Override
    public void reservar(Livro l) {
        System.out.println("O estado atual do Livro não permite realizar esta operação");
    }

}
