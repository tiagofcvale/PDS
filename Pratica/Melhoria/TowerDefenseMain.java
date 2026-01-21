import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TowerDefenseMain {
    public static void main(String[] args) {

        System.out.println("=== TOWER DEFENSE: SWARM INVASION ===");

        // ===== FLYWEIGHT (partilhar dados intrínsecos de inimigos) =====
        EnemyTypeFactory typeFactory = new EnemyTypeFactory();

        EnemyType zombieType = typeFactory.getEnemyType("Zombie", 100, 10, "zombie.png");
        EnemyType batType = typeFactory.getEnemyType("Bat", 40, 4, "bat.png");

        // Criar muitos inimigos com o MESMO EnemyType (intrínseco partilhado) + posição/estado (extrínseco)
        Enemy e1 = new Enemy(zombieType, 0, 5);
        Enemy e2 = new Enemy(zombieType, 1, 5);
        Enemy e3 = new Enemy(zombieType, 2, 5);

        Enemy b1 = new Enemy(batType, 0, 8);
        Enemy b2 = new Enemy(batType, 1, 8);

        System.out.println("\n--- Spawn (Flyweight) ---");
        e1.spawn();
        e2.spawn();
        e3.spawn();
        b1.spawn();
        b2.spawn();

        System.out.println("\nTipos partilhados em memória: " + typeFactory.getCacheSize());

        // ===== FACTORY (criação de torres) =====
        TowerFactory towerFactory = new TowerFactory();
        Tower t1 = towerFactory.createTower("Archer");
        Tower t2 = towerFactory.createTower("Cannon");

        System.out.println("\n--- Torres criadas (Factory) ---");
        t1.place(3, 5);
        t2.place(5, 5);

        // ===== STRATEGY (targeting / prioridade de alvo) =====
        TargetingStrategy closest = new ClosestTargetStrategy();
        TargetingStrategy strongest = new StrongestTargetStrategy();

        t1.setTargetingStrategy(closest);
        t2.setTargetingStrategy(strongest);

        // ===== OBSERVER (eventos: base, kills, ondas) =====
        GameEvents events = new GameEvents();
        events.subscribe(new HUDObserver());
        events.subscribe(new AudioObserver());

        System.out.println("\n--- Combate (Strategy + Observer) ---");
        t1.attack(e1, events);
        t2.attack(e1, events);

        t1.attack(b1, events);
        t2.attack(e2, events);

        events.notifyEvent("WAVE_CLEARED", "Onda 1 terminada!");

        System.out.println("\n=== FIM ===");
    }
}

interface EventObserver {
    public void update(String event, String msg);
}

class HUDObserver implements EventObserver {

    @Override
    public void update(String event, String msg) {
        System.out.println(event+": "+ msg);
    }

}

class AudioObserver implements EventObserver {

    @Override
    public void update(String event, String msg) {
        System.out.println(event+": "+ msg);
    }

}

class GameEvents {
    private List<EventObserver> observers = new ArrayList<>();

    public void subscribe(EventObserver e) {
        observers.add(e);
    }

    public void notifyEvent(String event, String msg) {
        for (EventObserver eventObserver : observers) {
            eventObserver.update(event, msg);
        }
    }
}

interface TargetingStrategy {
    public void execute(Enemy e, GameEvents gEvents);
}

class ClosestTargetStrategy implements TargetingStrategy {
    @Override
    public void execute(Enemy e, GameEvents gEvents) {
        gEvents.notifyEvent("ATTACK_EVENT", "Attacking "+ e.geteType().getQuantity()+ " "+e.geteType().getName()+" enemies");
    }
}

class StrongestTargetStrategy implements TargetingStrategy {
    @Override
    public void execute(Enemy e, GameEvents gEvents) {
        gEvents.notifyEvent("ATTACK_EVENT", "Attacking "+ e.geteType().getQuantity()+ " "+e.geteType().getName()+" enemies");
    }
}

class Tower {
    private String name;

    private TargetingStrategy tStrat;
    
    public Tower(String name) {
        this.name = name;
    }

    public void setTargetingStrategy(TargetingStrategy tStrat) {
        this.tStrat = tStrat;
    }

    public void place(int x, int y) {
        System.out.println("Placing tower "+name+" at ["+ x + ", " + y + "]");
    }

    public void attack(Enemy e, GameEvents gEvents) {
        tStrat.execute(e,gEvents);
    }
}

class TowerFactory {

    public Tower createTower(String name) {
        return new Tower(name);
    }

}

class Enemy {
    private EnemyType eType;
    private int x;
    private int y;
    public Enemy(EnemyType eType, int x, int y) {
        this.eType = eType;
        this.x = x;
        this.y = y;
    }
    
    public void spawn() {
        System.out.println("Spawning "+eType.getInfo()+" at ["+ x + ", " + y + "]");
    }

    public EnemyType geteType() {
        return eType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class EnemyType {
    private String name;
    private int hp;
    private int quantity;
    private String path;
    public EnemyType(String name, int hp, int quantity, String path) {
        this.name = name;
        this.hp = hp;
        this.quantity = quantity;
        this.path = path;
    }

    public String getInfo() {
        return name + " (" + hp + ", " + quantity + ", " + path + ")"; 
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPath() {
        return path;
    }
    
}

class EnemyTypeFactory {
    private Map<String, EnemyType> cache = new HashMap<>();

    public EnemyType getEnemyType(String name, int hp, int quantity, String path) {
        String chave = name + hp + quantity + path;
        if (!cache.containsKey(chave)) {
            cache.put(chave, new EnemyType(name, hp, quantity, path));
        }
        return cache.get(chave);
    }

    public int getCacheSize() {
        return this.cache.size();
    }
}