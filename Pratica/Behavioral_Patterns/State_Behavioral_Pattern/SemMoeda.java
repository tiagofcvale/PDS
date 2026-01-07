package State_Behavioral_Pattern;

public class SemMoeda implements Estado { // Cincrete State1

    @Override
    public void inserirMoeda(MaquinaMusica m) {
        System.out.println("Moeda inserida!");
        m.mudarEstado(new ComMoeda());
    }

    @Override
    public void premirPlay(MaquinaMusica m) {
        System.out.println("Insera uma moeda primeiro");
    }
    
}
