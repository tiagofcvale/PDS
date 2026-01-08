package Aula05.V2;

public class PlasticBag implements Portion {
    private final String name;
    private final Temperature temp;

    public PlasticBag(String name, Temperature temp) {
        this.name = name;
        this.temp = temp;
    }

    @Override
    public Temperature getTemperature() {
        return temp;
    }

    @Override
    public State getState() {
        return State.SOLID;
    }

    @Override
    public String toString() {
        return name + ": Temperature " + temp + ", State Solid";
    }
}
