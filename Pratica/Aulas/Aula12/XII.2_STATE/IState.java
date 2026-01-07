public interface IState {
    public void registar(Livro l);
    public void requisitar(Livro l);
    public void devolver(Livro l);
    public void cancelarReserva(Livro l);
    public void reservar(Livro l);
}
