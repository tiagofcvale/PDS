public class RobotAttacker implements Robot{

    private int kicks;
    private int moves;
    private int id;
    private String name;

    private BaseStation baseStation;

    public RobotAttacker(int id, String name) {
        this.id = id;
        this.name = name; 
    }

    public RobotAttacker(int id, String name, BaseStation baseStation) {
        this.id = id;
        this.name = name; 
        this.baseStation = baseStation;
    }

    @Override
    public void kick() {
        System.out.println("Attacker" + name+ " is Kicking!");
        this.kicks++;
        baseStation.sendMessage("kick", this);
    }

    @Override
    public void move() {
        System.out.println("Attacker" + name+ " is Moving!");
        this.moves++;
        baseStation.sendMessage("move", this);
    }

    @Override
    public int getKicks() {
        return kicks;
    }

    public void setKicks(int kicks) {
        this.kicks = kicks;
    }

    @Override
    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RobotAttacker [kicks=" + kicks + ", moves=" + moves + ", id=" + id + ", name=" + name + "]";
    }

    @Override
    public void accept(SavePlays savePlays) {
        savePlays.visit(this, "Attacker");
    }

    public BaseStation getBaseStation() {
        return baseStation;
    }

    public void setBaseStation(BaseStation baseStation) {
        this.baseStation = baseStation;
    }

    @Override
    public void receiveMessage(String message, Robot sender) {
        System.out.println("Robot "+this.getName()+" received the play: "+message+" from "+sender.getName());
    }

}
