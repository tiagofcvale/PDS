package Aula05.V2;

public class TermicBottle implements Portion {
    private final String name;
    private final Temperature temp;

    public TermicBottle(String name, Temperature temp) {
        this.name = name;
        this.temp = temp;
    }

    @Override
    public Temperature getTemperature() {
        return temp;
    }

    @Override
    public State getState() {
        return State.LIQUID;
    }

    @Override
    public String toString() {
        return name + ": Temperature " + temp + ", State Liquid";
    }
}
