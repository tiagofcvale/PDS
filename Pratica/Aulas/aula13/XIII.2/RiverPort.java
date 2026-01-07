import java.util.Iterator;

public class RiverPort implements Port {
    private final SeaPort seaPort;
    private final RiverLogger logger;

    private RiverPort(RiverLogger logger) {
        this.seaPort = new SeaPort();
        this.logger = logger;
    }

    public static RiverPort create(RiverLogger logger) {
        return new RiverPort(logger);
    }

    @Override
    public Iterator<String> iterator() {
        logger.log("iterator called");
        return seaPort.iterator();
    }

    @Override
    public boolean add(String ref, Ship p) {
        boolean accepted = false;
        if (p instanceof CargoShip) {
            CargoShip cs = (CargoShip) p;
            accepted = cs.cargo() <= 10;
        } else if (p instanceof PassengerShip) {
            PassengerShip ps = (PassengerShip) p;
            accepted = ps.passengers() <= 10;
        } else if (p instanceof ShipOfSmallShips) {
            ShipOfSmallShips sss = (ShipOfSmallShips) p;
            accepted = sss.ships().stream().allMatch(ship -> ship.passengers() <= 10);
        }
        if (accepted) {
            boolean result = seaPort.add(ref, p);
            logger.log("add: " + ref + " - " + p + " result: " + result);
            return result;
        } else {
            logger.log("add rejected: " + ref + " - " + p);
            return false;
        }
    }

    @Override
    public boolean exists(String ref) {
        boolean result = seaPort.exists(ref);
        logger.log("exists: " + ref + " result: " + result);
        return result;
    }

    @Override
    public Ship remove(String ref) {
        Ship removed = seaPort.remove(ref);
        logger.log("remove: " + ref + " result: " + removed);
        return removed;
    }

    public RiverLogger getLogger() {
        return logger;
    }

    public void visit(Ship ship){
        // Implementação opcional
    }
}
