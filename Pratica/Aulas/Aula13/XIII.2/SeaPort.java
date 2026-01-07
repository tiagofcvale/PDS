import java.util.Iterator;

import java.util.HashMap;
import java.util.Map;

public class SeaPort implements Port {

    private Map<String, Ship> ships = new HashMap<>();

    @Override
    public Iterator<String> iterator() {
        return ships.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .map(e -> "Ref: " + e.getKey() + " - " + e.getValue().toString())
            .iterator();
    }

    @Override
    public boolean add(String ref, Ship p) {
        if (exists(ref)) remove(ref);
        ships.put(ref, p);
        return true;
    }

    @Override
    public boolean exists(String ref) {
        return ships.containsKey(ref);
    }

    @Override
    public Ship remove(String ref) {
        return ships.remove(ref);
    }
}
