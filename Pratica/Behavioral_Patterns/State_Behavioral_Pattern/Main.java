package State_Behavioral_Pattern;

public class Main {
    public static void main(String[] args) {
        MaquinaMusica m = new MaquinaMusica();

        m.premirPlay();     // Sem moeda
        m.inserirMoeda();   // Muda para ComMoeda
        m.premirPlay();     // Toca e volta para SemMoeda
    }
}
