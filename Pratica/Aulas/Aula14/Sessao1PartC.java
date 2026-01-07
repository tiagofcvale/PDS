// NMEC:
// NOME: 

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sessao1PartC {
    public static void main(String[] args) throws Exception {
        // Part C
        BaseStation baseStation = new BaseStation();

        // Part A
        System.out.println("\nPartA: Playing with robots:");

        Robot defender1 = RobotCompany.createRobot(RobotType.DEFENDER, 1, "defender1", baseStation);
        Robot attacker1 = RobotCompany.createRobot(RobotType.ATTACKER, 2, "attacker1", baseStation);
        Robot goalie = RobotCompany.createRobot(RobotType.GOALIE, 3, "goalie1", baseStation);

        defender1.move();
        defender1.kick();
        attacker1.kick();
        goalie.move();
        goalie.kick();
        defender1.move();

        Ball ball = Ball.getInstance();
        System.out.println("Ball color: " + ball.getColor());

        OldAttacker oldRobot = new OldAttacker("oldRobot1");
        oldRobot.moveOld();
        oldRobot.kickOld();
        Robot attacker2 = new NewAttacker(oldRobot, 4, baseStation);
        attacker2.move();
        attacker2.kick();

        // Part B
        System.out.println("\nPart B: Save number of plays from each robot:");
        SavePlays savePlays = new SavePlays("plays.txt");
        defender1.accept(savePlays);
        attacker1.accept(savePlays);
        goalie.accept(savePlays);
        attacker2.accept(savePlays);
        savePlays.close();

        // Part C
        System.out.println("\nPart C: Base Station:");
        baseStation.addRobot(attacker1);
        baseStation.addRobot(goalie);
        baseStation.addRobot(attacker2);
        baseStation.addRobot(defender1);
        defender1.move();
        attacker1.kick();
        goalie.move();
        attacker2.kick();
    }
}

class BaseStation {
    List<Robot> robots;

    public BaseStation() {
        this.robots = new ArrayList<>();
    }

    public void addRobot(Robot r){
        this.robots.add(r);
    }

    public void sendMessage(Robot sender, String message) {
        for (Robot robot : robots) {
            if (robot != sender) {
                robot.receiveMessage(message, sender);
            }
        }
    }
}

class SavePlays {
    private String file;
    private FileWriter fw;

    public SavePlays(String file) {
        this.file = file;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visit(Robot robot, String className) {
        try {
            fw.write(className + " " +robot.getName()+ " has " +robot.getnMoves() + " and " + robot.getnKicks() + " kicks\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void close() {
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public FileWriter getFw() {
        return fw;
    }

    public void setFw(FileWriter fw) {
        this.fw = fw;
    }
}

class NewAttacker implements Robot{

    private OldAttacker oldAttacker;
    private int id;
    
    private int nMoves = 0;
    private int nKicks = 0;

    private BaseStation baseStation;
    
    public NewAttacker(OldAttacker oldAttacker, int id, BaseStation baseStation) {
        this.oldAttacker = oldAttacker;
        this.id = id;
        this.baseStation = baseStation;
    }

    @Override
    public void move() {
        baseStation.sendMessage(this, "move from "+ oldAttacker.getName());
        nMoves++;
        System.out.println(oldAttacker.getName()+" is moving");
    }

    @Override
    public void kick() {
        baseStation.sendMessage(this, "kick from "+ oldAttacker.getName());
        nKicks++;
        System.out.println(oldAttacker.getName()+" is kicking");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void accept(SavePlays savePlays) {
        savePlays.visit(this, "Attacker");
    }

    public OldAttacker getOldAttacker() {
        return oldAttacker;
    }

    public void setOldAttacker(OldAttacker oldAttacker) {
        this.oldAttacker = oldAttacker;
    }

    public int getnMoves() {
        return nMoves;
    }

    public void setnMoves(int nMoves) {
        this.nMoves = nMoves;
    }

    public int getnKicks() {
        return nKicks;
    }

    public void setnKicks(int nKicks) {
        this.nKicks = nKicks;
    }

    @Override
    public String getName() {
        return oldAttacker.getName();
    }

    @Override
    public void receiveMessage(String message, Robot sender) {
        System.out.println("Robot "+oldAttacker.getName()+" received the play: " + message);
    }

    public BaseStation getBaseStation() {
        return baseStation;
    }

    public void setBaseStation(BaseStation baseStation) {
        this.baseStation = baseStation;
    }
}

class OldAttacker {
    private String name;

    public OldAttacker(String name) {
        this.name = name;
    }

    public void moveOld(){
        System.out.println(name+" is moving");
    }

    public void kickOld(){
        System.out.println(name+" is kicking");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Ball {

    private static Ball ball;
    private String color;

    private Ball() {
        this.color = "White";
    }

    public static Ball getInstance() {
        if (ball == null) {
            ball = new Ball();
        }
        return ball;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

enum RobotType{
    DEFENDER, ATTACKER, GOALIE
}

interface Robot {
    public void move();
    public void kick();

    public String getName();
    public int getnKicks();
    public int getnMoves();

    public void accept(SavePlays savePlays);

    public void receiveMessage(String message, Robot sender);
}

class RobotCompany {
    public static Robot createRobot(RobotType type, int id, String name, BaseStation baseStation) {
        switch (type) {
            case DEFENDER:
                return new RobotDefender(id, name, baseStation);
            case ATTACKER:
                return new RobotAttacker(id, name, baseStation);
            case GOALIE:
                return new RobotGoalie(id, name, baseStation);
            default:
                System.out.println("Erro");
                return null;
        }
    }

}

class RobotDefender implements Robot{
    private int id;
    private String name;

    private int nMoves = 0;
    private int nKicks = 0;

    private BaseStation baseStation;

    public RobotDefender(int id, String name, BaseStation baseStation) {
        this.id = id;
        this.name = name;
        this.baseStation = baseStation;
    }

    @Override
    public void move() {
        nMoves++;
        baseStation.sendMessage(this, "move from "+ name);
        System.out.println(name+" is moving");
    }

    @Override
    public void kick() {
        nKicks++;
        baseStation.sendMessage(this, "move from "+ name);
        System.out.println(name+" is kicking");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void accept(SavePlays savePlays) {
        savePlays.visit(this, "Defender");
    }

    public int getnMoves() {
        return nMoves;
    }

    public void setnMoves(int nMoves) {
        this.nMoves = nMoves;
    }

    public int getnKicks() {
        return nKicks;
    }

    public void setnKicks(int nKicks) {
        this.nKicks = nKicks;
    }

    @Override
    public void receiveMessage(String message, Robot sender) {
        System.out.println("Robot "+name+" received the play: " + message);
    }

    public BaseStation getBaseStation() {
        return baseStation;
    }

    public void setBaseStation(BaseStation baseStation) {
        this.baseStation = baseStation;
    }
}

class RobotGoalie implements Robot{
    private int id;
    private String name;

    private int nMoves = 0;
    private int nKicks = 0;

    private BaseStation baseStation;

    public RobotGoalie(int id, String name, BaseStation baseStation) {
        this.id = id;
        this.name = name;
        this.baseStation = baseStation; 
    }

    @Override
    public void move() {
        nMoves++;
        baseStation.sendMessage(this, "move from "+ name);
        System.out.println(name+" is moving");
    }

    @Override
    public void kick() {
        nKicks++;
        baseStation.sendMessage(this, "kick from "+ name);
        System.out.println(name+" is kicking");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void accept(SavePlays savePlays) {
        savePlays.visit(this, "Goalie");
    }

    public int getnMoves() {
        return nMoves;
    }

    public void setnMoves(int nMoves) {
        this.nMoves = nMoves;
    }

    public int getnKicks() {
        return nKicks;
    }

    public void setnKicks(int nKicks) {
        this.nKicks = nKicks;
    }

    @Override
    public void receiveMessage(String message, Robot sender) {
        System.out.println("Robot "+name+" received the play: " + message);
    }

    public BaseStation getBaseStation() {
        return baseStation;
    }

    public void setBaseStation(BaseStation baseStation) {
        this.baseStation = baseStation;
    }
}

class RobotAttacker implements Robot{
    private int id;
    private String name;

    private int nMoves = 0;
    private int nKicks = 0;

    private BaseStation baseStation;

    public RobotAttacker(int id, String name, BaseStation baseStation) {
        this.id = id;
        this.name = name;
        this.baseStation = baseStation;
    }

    @Override
    public void move() {
        nMoves++;
        baseStation.sendMessage(this, "move from "+ name);
        System.out.println(name+" is moving");
    }

    @Override
    public void kick() {
        this.nKicks++;
        baseStation.sendMessage(this, "kick from "+ name);
        System.out.println(name+" is kicking");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void accept(SavePlays savePlays) {
        savePlays.visit(this, "Attacker");
    }

    public int getnMoves() {
        return nMoves;
    }

    public void setnMoves(int nMoves) {
        this.nMoves = nMoves;
    }

    public int getnKicks() {
        return nKicks;
    }

    public void setnKicks(int nKicks) {
        this.nKicks = nKicks;
    }

    @Override
    public void receiveMessage(String message, Robot sender) {
        System.out.println("Robot "+name+" received the play: " + message);
    }

    public BaseStation getBaseStation() {
        return baseStation;
    }

    public void setBaseStation(BaseStation baseStation) {
        this.baseStation = baseStation;
    }
}