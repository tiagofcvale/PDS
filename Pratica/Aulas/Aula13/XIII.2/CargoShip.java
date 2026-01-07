public class CargoShip implements Ship{

    private String code;
    private String name;
    private double cargo;

    public CargoShip(String code, String name, double cargo) {
        this.code = code;
        this.name = name;
        this.cargo = cargo;
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
        return "CargoShip [code=" + code + ", name=" + name + ", cargo=" + cargo + "]";
    }

    public double cargo() {
        return cargo;
    }

    @Override
    public void accept(RiverPort porto) {
        porto.visit(this);
    }
    
}
