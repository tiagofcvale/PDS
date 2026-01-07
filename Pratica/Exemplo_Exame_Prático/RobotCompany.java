public class RobotCompany {
    public static Robot createRobot(RobotType type, int id, String name) {
        switch (type) {
            case DEFENDER:
                return new RobotDefender(id, name);
            case ATTACKER:
                return new RobotAttacker(id, name);
            case GOALIE:
                return new RobotGoalie(id, name);
            default:
                return null;
        }
    }

    public static Robot createRobot(RobotType type, int id, String name, BaseStation baseStation) {
        switch (type) {
            case DEFENDER:
                return new RobotDefender(id, name, baseStation);
            case ATTACKER:
                return new RobotAttacker(id, name, baseStation);
            case GOALIE:
                return new RobotGoalie(id, name, baseStation);
            default:
                return null;
        }
    }
}
