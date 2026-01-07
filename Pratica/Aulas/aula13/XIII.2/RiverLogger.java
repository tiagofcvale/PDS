import java.util.ArrayList;
import java.util.List;

public class RiverLogger {
    private final List<String> logs = new ArrayList<>();

    public void log(String msg) {
        logs.add(msg);
    }

    public List<String> getLogs() {
        return logs;
    }

    public void printLogs() {
        logs.forEach(System.out::println);
    }
}