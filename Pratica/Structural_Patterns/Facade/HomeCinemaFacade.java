package Facade;

public class HomeCinemaFacade { //FACADE 
    private Amplificador amp;
    private Projetor proj;
    private Ecran ecran;
    private FilmePlayer player;

    public HomeCinemaFacade(Amplificador a, Projetor p, Ecran e, FilmePlayer f) {
        this.amp = a;
        this.proj = p;
        this.ecran = e;
        this.player = f;
    }

    public void verFilme(String filme) {
        amp.ligar();
        proj.ligar();
        ecran.baixar();
        player.play(filme);
    }
}
