// NMEC: 125913
// NOME: Tiago Vale

import java.io.FileWriter;
import java.io.IOException;

public class Sessao1PartAB {
    public static void main(String[] args) throws Exception {
        // Part A
        System.out.println("\nPartA: Playing with robots:");

        Robot defender1 = RobotCompany.createRobot(RobotType.DEFENDER, 1, "defender1");
        Robot attacker1 = RobotCompany.createRobot(RobotType.ATTACKER, 2, "attacker1");
        Robot goalie = RobotCompany.createRobot(RobotType.GOALIE, 3, "goalie1");

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
        Robot attacker2 = new NewAttacker(oldRobot, 4);
        attacker2.move();
        attacker2.kick();

        // Part B
        System.out.println("\nPartB: Save number of plays from each robot:");
        SavePlays savePlays = new SavePlays("plays.txt");
        defender1.accept(savePlays);
        attacker1.accept(savePlays);
        goalie.accept(savePlays);
        attacker2.accept(savePlays);
        savePlays.close();
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
    
    public NewAttacker(OldAttacker oldAttacker, int id) {
        this.oldAttacker = oldAttacker;
        this.id = id;
    }

    @Override
    public void move() {
        nMoves++;
        System.out.println(oldAttacker.getName()+" is moving");
    }

    @Override
    public void kick() {
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
}

class RobotCompany {
    public static Robot createRobot(RobotType type, int id, String name) {
        switch (type) {
            case DEFENDER:
                return new RobotDefender(id, name);
            case ATTACKER:
                return new RobotAttacker(id, name);
            case GOALIE:
                return new RobotGoalie(id, name);
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

    public RobotDefender(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void move() {
        nMoves++;
        System.out.println(name+" is moving");
    }

    @Override
    public void kick() {
        nKicks++;
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
}

class RobotGoalie implements Robot{
    private int id;
    private String name;

    private int nMoves = 0;
    private int nKicks = 0;

    public RobotGoalie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void move() {
        nMoves++;
        System.out.println(name+" is moving");
    }

    @Override
    public void kick() {
        nKicks++;
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
}

class RobotAttacker implements Robot{
    private int id;
    private String name;

    private int nMoves = 0;
    private int nKicks = 0;

    public RobotAttacker(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void move() {
        nMoves++;
        System.out.println(name+" is moving");
    }

    @Override
    public void kick() {
        this.nKicks++;
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
}