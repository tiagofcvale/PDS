package Teste3;

public class GPU {
    private ControlUnit cUnit;
    
    public GPU(ControlUnit cUnit) {
        this.cUnit = cUnit;
    }

    public void generateHeat() {
        cUnit.notify(this);
    }

    public void receive(String msg) {
        System.out.println(msg);
    }
}
