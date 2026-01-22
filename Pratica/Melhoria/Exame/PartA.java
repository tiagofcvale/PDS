package Exame;
import java.util.*;

public class PartA {
    public static void main(String[] args) {
        // Create units
        PlayerUnit a1 = AgentRegistry.create(Role.DUELIST,      1, "Jett", new Rifle());
        PlayerUnit a2 = AgentRegistry.create(Role.CONTROLLER,   2, "Omen", new Sidearm());

        PlayerUnit b1 = AgentRegistry.create(Role.SENTINEL,     3, "Sage", new Sniper());
        PlayerUnit b2 = AgentRegistry.create(Role.INITIATOR,    4, "Sova", new Shotgun());

        // Add extra behaviour at runtime (abilities)
        a1 = new DashSkill(a1);
        a2 = new SmokeSkill(a2);
        b1 = new HealSkill(b1);

        // Simple random simulation to test actions
        System.out.println("\nSimple random simulation");
        List<PlayerUnit> units = List.of(a1, a2, b1, b2);
        Random rnd = new Random(7);

        for (int i = 0; i < 12; i++) {
            PlayerUnit u = units.get(rnd.nextInt(units.size()));
            int op = rnd.nextInt(6);

            System.out.print(" ");
            switch (op) {
                case 0:
                    u.run(new Point2D(rnd.nextInt(10), rnd.nextInt(10)));
                    break;
                case 1:
                    u.walk(new Point2D(rnd.nextInt(10), rnd.nextInt(10)));
                    break;
                case 2:
                    u.crouch();
                    break;
                case 3:
                    u.point(new Point3D(rnd.nextInt(10), rnd.nextInt(10), rnd.nextInt(5)));
                    break;
                case 4:
                    u.shoot();
                    break;
                case 5:
                    tryUseAbility(u);
                    break;
            }
        }
    }

    private static void tryUseAbility(PlayerUnit u) {
        u.useAbility();
    }
}