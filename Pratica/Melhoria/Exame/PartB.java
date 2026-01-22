package Exame;

import java.util.*;

public class PartB {
    public static void main(String[] args) {

        MatchFeed matchFeed = new MatchFeed();

        GroupChannel alpha = new Squad("ALPHA TEAM");
        GroupChannel beta = new Squad("BETA TEAM");

        alpha.addListener(matchFeed);
        beta.addListener(matchFeed);

        PlayerUnit a1 = AgentRegistry.create(Role.DUELIST,    1, "Jett", new Rifle(), alpha);
        PlayerUnit a2 = AgentRegistry.create(Role.CONTROLLER, 2, "Omen", new Sidearm(), alpha);

        PlayerUnit b1 = AgentRegistry.create(Role.SENTINEL,   3, "Sage", new Sniper(), beta);
        PlayerUnit b2 = AgentRegistry.create(Role.INITIATOR,  4, "Sova", new Shotgun(), beta);

        alpha.register(a1); 
        alpha.register(a2);
        beta.register(b1);
        beta.register(b2);

        a1 = new DashSkill(a1);
        a2 = new SmokeSkill(a2);
        b1 = new HealSkill(b1);

        System.out.println("\nSimple random simulation");
        List<PlayerUnit> units = List.of(a1, a2, b1, b2);
        Random rnd = new Random(7);

        for (int i = 0; i < 12; i++) {
            PlayerUnit u = units.get(rnd.nextInt(units.size()));
            int op = rnd.nextInt(6);

            System.out.print(" ");
            switch (op) {
                case 0 -> u.run(new Point2D(rnd.nextInt(10), rnd.nextInt(10)));
                case 1 -> u.walk(new Point2D(rnd.nextInt(10), rnd.nextInt(10)));
                case 2 -> u.crouch();
                case 3 -> u.point(new Point3D(rnd.nextInt(10), rnd.nextInt(10), rnd.nextInt(5)));
                case 4 -> u.shoot();
                case 5 -> tryUseAbility(u);
            }
        }
    }
}

class MatchFeed {
    private List<GroupChannel> observers = new ArrayList<>();

    public void addObserver(GroupChannel gChannel) {
        observers.add(gChannel);
    }
}

interface GroupChannel {

    void addListener(MatchFeed matchFeed);

    void register(PlayerUnit a1);

}

class Squad implements GroupChannel {
    private String name;
    private List<PlayerUnit> players = new ArrayList<>();

    public Squad(String name) {
        this.name = name;
    }

    @Override
    public void addListener(MatchFeed matchFeed) {
        matchFeed.addObserver(this);
    }

    @Override
    public void register(PlayerUnit pUnit) {
        players.add(pUnit);
    }
}

class AgentRegistry {
    public static PlayerUnit create(Role role, int id, String name, Weapon weapon, GroupChannel channel) {
        switch (role) {
            case DUELIST:
                return new Duelist(id,name,weapon, channel);
            case SENTINEL:
                return new Sentinel(id,name,weapon, channel);
            case INITIATOR:
                return new Initiator(id,name,weapon, channel);
            case CONTROLLER:
                return new Controller(id,name,weapon, channel);
            default:
                return null;
        }
    }
}

