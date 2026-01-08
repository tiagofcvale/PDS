package Teste3;

public class Fan {
    private ControlUnit cUnit;

    public Fan(ControlUnit cUnit) {
        this.cUnit = cUnit;
    }

    public void receive(String msg) {
        System.out.println(msg);
    }

    public void generateHeat() {
        cUnit.notify(this);
    }
}
