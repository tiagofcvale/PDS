package Command_Behavioral_Pattern;

public class Main {
    public static void main(String[] args) {
        Luz luz = new Luz();

        Command ligar = new LigarLuzCommand(luz);
        Command desligar = new DesligarLuzCommand(luz);

        ControloRemoto remote = new ControloRemoto();

        remote.definirComando(ligar);
        remote.permirBotao();   // Luz ligada

        remote.definirComando(desligar);
        remote.permirBotao();   // Luz desligada
    }
}