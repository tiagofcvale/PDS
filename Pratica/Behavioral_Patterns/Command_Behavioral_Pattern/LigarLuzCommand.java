package Command_Behavioral_Pattern;

public class LigarLuzCommand implements Command {

    private Luz luz ;
    public LigarLuzCommand(Luz luz) {this.luz = luz; }

    @Override
    public void executar() {
        luz.ligar();
    }
    
}
