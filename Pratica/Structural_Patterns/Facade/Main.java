package Facade;

public class Main {
    public static void main(String[] args) {
        HomeCinemaFacade cinema = new HomeCinemaFacade(
            new Amplificador(),
            new Projetor(),
            new Ecran(),
            new FilmePlayer()
        );

        cinema.verFilme("Matrix");
    }
}
