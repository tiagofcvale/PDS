package State_Behavioral_Pattern;

public class ComMoeda implements Estado {

    @Override
    public void inserirMoeda(MaquinaMusica m) {
        System.out.println("Moeda já inserida");
    }

    @Override
    public void premirPlay(MaquinaMusica m) {
        System.out.println("A tocar música");
        m.mudarEstado(new SemMoeda());
    }
    
}
