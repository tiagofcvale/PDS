// NMEC: 125913
// NOME: Tiago Vale

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
