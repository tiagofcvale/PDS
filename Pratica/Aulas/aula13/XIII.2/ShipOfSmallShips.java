import java.util.ArrayList;
import java.util.List;

public class ShipOfSmallShips implements Ship{

    private List<PassengerShip> ships;
    private String code;
    private String name;

    public ShipOfSmallShips(String code, String name) {
        this.code = code;
        this.name = name;
        ships = new ArrayList<>(); 
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String name() {
        return name;
    }
    
    public void addPassengerShip(PassengerShip ship) {
        this.ships.add(ship);
    }

    public void removePassengerShip(PassengerShip ship) {
        this.ships.remove(ship);
    }

    @Override
    public String toString() {
        int total = ships.size();
        StringBuilder sb = new StringBuilder();
        sb.append(name+" with ").append(ships.size())
          .append(" ships. Total passagers capacity : ").append(total).append("\n");
        for (PassengerShip ps : ships) {
            sb.append("\t"+ps.toString()).append("\n");
        }
        // Remove last newline if there is at least one ship
        if (!ships.isEmpty()) sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public void accept(RiverPort porto) {
        porto.visit(this);
    }

    public List<PassengerShip> ships(){
        return ships;
    }
    
}
