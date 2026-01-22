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

class Point2D {
    private double x;
    private double y;
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    
}

class Point3D {
    private double x;
    private double y;
    private double z;
    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getZ() {
        return z;
    }
    public void setZ(double z) {
        this.z = z;
    }
    
}

abstract class SkillDecorator implements PlayerUnit {
    protected PlayerUnit pUnit;

    public SkillDecorator(PlayerUnit pUnit) {
        this.pUnit = pUnit;
    }
}

class DashSkill extends SkillDecorator {

    public DashSkill(PlayerUnit pUnit) {
        super(pUnit);
    }

    @Override
    public void useAbility() {
        System.out.println("["+pUnit.getName()+"] Using DashSkill");
    }

    @Override
    public String getName() {
        return pUnit.getName();
    }

    @Override
    public void shoot() {
        pUnit.shoot();
    }

    @Override
    public void point(Point3D point3d) {
        pUnit.point(point3d);
    }

    @Override
    public void crouch() {
        pUnit.crouch();
    }

    @Override
    public void walk(Point2D point2d) {
        pUnit.walk(point2d);
    }

    @Override
    public void run(Point2D point2d) {
        pUnit.run(point2d);
    }

}

class SmokeSkill extends SkillDecorator {

    public SmokeSkill(PlayerUnit pUnit) {
        super(pUnit);
    }

    @Override
    public void useAbility() {
        System.out.println("["+pUnit.getName()+"] Using SmokeSkill");
    }

    @Override
    public String getName() {
        return pUnit.getName();
    }
    @Override
    public void shoot() {
        pUnit.shoot();
    }

    @Override
    public void point(Point3D point3d) {
        pUnit.point(point3d);
    }

    @Override
    public void crouch() {
        pUnit.crouch();
    }

    @Override
    public void walk(Point2D point2d) {
        pUnit.walk(point2d);
    }

    @Override
    public void run(Point2D point2d) {
        pUnit.run(point2d);
    }
}

class HealSkill extends SkillDecorator {

    public HealSkill(PlayerUnit pUnit) {
        super(pUnit);
    }

    @Override
    public void useAbility() {
        System.out.println("["+pUnit.getName()+"] Using SmokeSkill");
    }

    @Override
    public String getName() {
        return pUnit.getName();
    }
    @Override
    public void shoot() {
        pUnit.shoot();
    }

    @Override
    public void point(Point3D point3d) {
        pUnit.point(point3d);
    }

    @Override
    public void crouch() {
        pUnit.crouch();
    }

    @Override
    public void walk(Point2D point2d) {
        pUnit.walk(point2d);
    }

    @Override
    public void run(Point2D point2d) {
        pUnit.run(point2d);
    }
}

enum Role {
    DUELIST,CONTROLLER,INITIATOR,SENTINEL
}

interface Weapon {

    String getName();
    
}

class Rifle implements Weapon {

    @Override
    public String getName() {
        return "Rifle";
    }

}

class Sidearm implements Weapon {
    @Override
    public String getName() {
        return "SideArm";
    }
}

class Sniper implements Weapon {
    @Override
    public String getName() {
        return "Sniper";
    }
}

class Shotgun implements Weapon {
    @Override
    public String getName() {
        return "Shotgun";
    }
}

class AgentRegistry {
    public static PlayerUnit create(Role role, int id, String name, Weapon weapon) {
        switch (role) {
            case DUELIST:
                return new Duelist(id,name,weapon);
            case SENTINEL:
                return new Sentinel(id,name,weapon);
            case INITIATOR:
                return new Initiator(id,name,weapon);
            case CONTROLLER:
                return new Controller(id,name,weapon);
            default:
                return null;
        }
    }
}

interface PlayerUnit {

    void useAbility();

    void shoot();

    void point(Point3D point3d);

    void crouch();

    void walk(Point2D point2d);

    void run(Point2D point2d);

    String getName();

}

class Duelist implements PlayerUnit {
    private int id;
    private String name;
    private Weapon weapon;
    public Duelist(int id, String name, Weapon weapon) {
        this.id = id;
        this.name = name;
        this.weapon = weapon;
    }
    @Override
    public void useAbility() {
        System.out.println("No Abilities");
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void run(Point2D point2d) {
        System.out.println("["+name+"] Running to ("+ point2d.getX() +", "+point2d.getY()+")");
    }
    @Override
    public void walk(Point2D point2d) {
        System.out.println("["+name+"] Walking to ("+ point2d.getX() +", "+point2d.getY()+")");
    }
    @Override
    public void crouch() {
        System.out.println("["+name+"] Crouching");
    }
    @Override
    public void point(Point3D point3d) {
        System.out.println("["+name+"] Pointing with "+weapon.getName()+" to ("+ point3d.getX() +", "+point3d.getY()+", "+point3d.getZ()+")");
    }
    @Override
    public void shoot() {
        System.out.println("["+name+"] Shooting with "+weapon.getName());
    }
    public int getId() {
        return id;
    }
    public Weapon getWeapon() {
        return weapon;
    }
}

class Controller implements PlayerUnit {
    private int id;
    private String name;
    private Weapon weapon;
    public Controller(int id, String name, Weapon weapon) {
        this.id = id;
        this.name = name;
        this.weapon = weapon;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void useAbility() {
        System.out.println("No Abilities");
    }
    @Override
    public void run(Point2D point2d) {
        System.out.println("["+name+"] Running to ("+ point2d.getX() +", "+point2d.getY()+")");
    }
    @Override
    public void walk(Point2D point2d) {
        System.out.println("["+name+"] Walking to ("+ point2d.getX() +", "+point2d.getY()+")");
    }
    @Override
    public void crouch() {
        System.out.println("["+name+"] Crouching");
    }
    @Override
    public void point(Point3D point3d) {
        System.out.println("["+name+"] Pointing with "+weapon.getName()+" to ("+ point3d.getX() +", "+point3d.getY()+", "+point3d.getZ()+")");
    }
    @Override
    public void shoot() {
        System.out.println("["+name+"] Shooting with "+weapon.getName());
    }
    public int getId() {
        return id;
    }
    public Weapon getWeapon() {
        return weapon;
    }
}

class Sentinel implements PlayerUnit {
    private int id;
    private String name;
    private Weapon weapon;
    public Sentinel(int id, String name, Weapon weapon) {
        this.id = id;
        this.name = name;
        this.weapon = weapon;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void useAbility() {
        System.out.println("No Abilities");
    }
    @Override
    public void run(Point2D point2d) {
        System.out.println("["+name+"] Running to ("+ point2d.getX() +", "+point2d.getY()+")");
    }
    @Override
    public void walk(Point2D point2d) {
        System.out.println("["+name+"] Walking to ("+ point2d.getX() +", "+point2d.getY()+")");
    }
    @Override
    public void crouch() {
        System.out.println("["+name+"] Crouching");
    }
    @Override
    public void point(Point3D point3d) {
        System.out.println("["+name+"] Pointing with "+weapon.getName()+" to ("+ point3d.getX() +", "+point3d.getY()+", "+point3d.getZ()+")");
    }
    @Override
    public void shoot() {
        System.out.println("["+name+"] Shooting with "+weapon.getName());
    }
    public int getId() {
        return id;
    }
    public Weapon getWeapon() {
        return weapon;
    }
}

class Initiator implements PlayerUnit {
    private int id;
    private String name;
    private Weapon weapon;
    public Initiator(int id, String name, Weapon weapon) {
        this.id = id;
        this.name = name;
        this.weapon = weapon;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void useAbility() {
        System.out.println("No Abilities");
    }
    @Override
    public void run(Point2D point2d) {
        System.out.println("["+name+"] Running to ("+ point2d.getX() +", "+point2d.getY()+")");
    }
    @Override
    public void walk(Point2D point2d) {
        System.out.println("["+name+"] Walking to ("+ point2d.getX() +", "+point2d.getY()+")");
    }
    @Override
    public void crouch() {
        System.out.println("["+name+"] Crouching");
    }
    @Override
    public void point(Point3D point3d) {
        System.out.println("["+name+"] Pointing with "+weapon.getName()+" to ("+ point3d.getX() +", "+point3d.getY()+", "+point3d.getZ()+")");
    }
    @Override
    public void shoot() {
        System.out.println("["+name+"] Shooting with "+weapon.getName());
    }
    public int getId() {
        return id;
    }
    public Weapon getWeapon() {
        return weapon;
    }
}