public class Emprestado implements IState{

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
        System.out.println("O estado atual do Livro não permite realizar esta operação");
    }

    @Override
    public void cancelarReserva(Livro l) {
        l.setState(new Disponivel());
        System.out.println("A reserva do Livro "+l.getTitle()+" foi cancelada com sucesso!");
    }

    @Override
    public void reservar(Livro l) {
        System.out.println("O estado atual do Livro não permite realizar esta operação");
    }
    
}
