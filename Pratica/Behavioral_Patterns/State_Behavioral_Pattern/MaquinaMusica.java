package State_Behavioral_Pattern;

public class MaquinaMusica {
    private Estado estado;

    public MaquinaMusica() {
        this.estado = new SemMoeda();
    }

    public void mudarEstado(Estado novo) {
        this.estado = novo;
    }

    public void inserirMoeda() { estado.inserirMoeda(this); }
    public void premirPlay() { estado.premirPlay(this); }
}
