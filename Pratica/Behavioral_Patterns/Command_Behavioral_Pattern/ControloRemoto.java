package Command_Behavioral_Pattern;

public class ControloRemoto { // INVOKER

    private Command comando;

    public void definirComando(Command c) {
        this.comando = c;
    }

    public void permirBotao(){
        this.comando.executar();
    }
}
