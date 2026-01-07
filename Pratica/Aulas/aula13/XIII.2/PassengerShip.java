public class PassengerShip implements Ship{

    private String code;
    private String name;
    private int passengers;

    public PassengerShip(String code, String name, int passengers) {
        this.code = code;
        this.name = name;
        this.passengers = passengers;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "PassengerShip [code=" + code + ", name=" + name + ", passengers=" + passengers + "]";
    }

    public int passengers() {
        return passengers;
    }

    @Override
    public void accept(RiverPort porto) {
        porto.visit(this);
    }
    
}
