// NMEC:
// NOME: 

public class Sessao1PartC {public static void main(String[] args) throws Exception {
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
