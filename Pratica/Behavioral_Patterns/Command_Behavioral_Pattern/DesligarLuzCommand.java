package Command_Behavioral_Pattern;

public class DesligarLuzCommand implements Command {
    private Luz luz ;
    public DesligarLuzCommand(Luz luz) {this.luz = luz; }

    @Override
    public void executar() {
        luz.desligar();
    }
}
