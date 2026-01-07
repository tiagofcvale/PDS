public class NewAttacker implements Robot{
    private OldAttacker oldAttacker;
    private int id;
    private BaseStation baseStation;
    
    private int moves;
    private int kicks;

    public NewAttacker(OldAttacker oldAttacker, int id) {
        this.oldAttacker = oldAttacker;
        this.id = id;
    }

    public NewAttacker(OldAttacker oldAttacker, int id, BaseStation baseStation) {
        this.oldAttacker = oldAttacker;
        this.id = id;
        this.baseStation = baseStation;
    }

    @Override
    public void move() {
        System.out.println("OldRobot" + oldAttacker.getName()+ "is Moving!");
        moves++;
        baseStation.sendMessage("move", this);
    }

    @Override
    public void kick() {
        System.out.println("OldRobot" + oldAttacker.getName()+ "is Kicking!");
        kicks++;
        baseStation.sendMessage("kick", this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return oldAttacker.getName();
    }

    @Override
    public int getKicks() {
        return kicks;
    }

    @Override
    public int getMoves() {
        return moves;
    }

    @Override
    public void accept(SavePlays savePlays) {
        savePlays.visit(this, "New Attacker");
    }

    public OldAttacker getOldAttacker() {
        return oldAttacker;
    }

    public void setOldAttacker(OldAttacker oldAttacker) {
        this.oldAttacker = oldAttacker;
    }

    public BaseStation getBaseStation() {
        return baseStation;
    }

    public void setBaseStation(BaseStation baseStation) {
        this.baseStation = baseStation;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public void setKicks(int kicks) {
        this.kicks = kicks;
    }

    @Override
    public void receiveMessage(String message, Robot sender) {
        System.out.println("Robot "+this.getName()+" received the play: "+message+" from "+sender.getName());
    }

    
    
}
