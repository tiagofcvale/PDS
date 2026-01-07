public class Reservado implements IState{

    @Override
    public void registar(Livro l) {
        System.out.println("O estado atual do Livro não permite realizar esta operação");
    }

    @Override
    public void requisitar(Livro l) {
        System.out.println("O estado atual do Livro não permite realizar esta operação");
    }

    @Override
    public void devolver(Livro l) {
        l.setState(new Disponivel());
        System.out.println("O Livro "+l.getTitle()+" devolvido com sucesso!");
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
