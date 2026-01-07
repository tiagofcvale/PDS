public class Disponivel implements IState{

    @Override
    public void registar(Livro l) {
        System.out.println("O estado atual do Livro não permite realizar esta operação");
    }

    @Override
    public void requisitar(Livro l) {
        l.setState(new Emprestado());
        System.out.println("Livro "+l.getTitle()+" requisitado com sucesso!");
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
        l.setState(new Reservado());
        System.out.println("Livro "+l.getTitle()+" reservado com sucesso!");
    }
    
}
