import java.util.ArrayList;
import java.util.List;

public class BaseStation {
    private List<Robot> robots = new ArrayList<>();

    public void addRobot(Robot robot) {
        this.robots.add(robot);
    }

    public void sendMessage(String message, Robot sender) {
        for (Robot robot : robots) {
            if (robot != sender) {
                robot.receiveMessage(message, sender);
            }
        }
    }
}
