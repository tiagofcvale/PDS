import java.util.HashMap;
import java.util.Map;

public class RPGSimulator {
    public static void main(String[] args) {
        // 1. SINGLETON: Motor do Mundo
        WorldEngine world = WorldEngine.getInstance();
        System.out.println("--- MUNDO: " + world.getRegion() + " ---");

        // 2. OBSERVER: Sistema de Conquistas ouvindo eventos
        AchievementSystem achievements = new AchievementSystem();
        world.getEventManager().subscribe("boss_killed", achievements);

        // 3. FACTORY: Criando Heróis
        Hero warrior = HeroFactory.createHero(ClassType.WARRIOR, "Aragorn");
        Hero mage = HeroFactory.createHero(ClassType.MAGE, "Gandalf");

        // 4. COMMAND: Executando uma ação de Conjurar Magia
        ActionMenu menu = new ActionMenu();
        menu.setCommand(new CastSpellCommand(mage, "Explosão Arcana"));
        menu.executeAction();

        // 5. STATE: Herói recebe dano e fica exausto
        System.out.println("\n--- BATALHA INTENSA ---");
        warrior.receberDano(90); 
        warrior.interagir(); // O diálogo muda porque ele está exausto

        // 6. STRATEGY: Mudando o elemento da arma
        System.out.println("\n--- TROCA DE ESTRATÉGIA ---");
        mage.setElementStrategy(new IceElement());
        mage.atacar();

        // Notificando evento global (Observer)
        System.out.println("\n--- EVENTO FINAL ---");
        world.getEventManager().notify("boss_killed", "O Dragão de Gelo foi derrotado!");
    }
}

interface RPGStrategy{
    void execute(Hero hero);
}

class IceElement implements RPGStrategy{

    @Override
    public void execute(Hero hero) {
        System.out.println("["+hero.getName()+"] Ataca com elemento de gelo!");
    }

}

interface RPGState{
    void interagir(Hero hero);
}

class Exaust implements RPGState{

    @Override
    public void interagir(Hero hero) {
        System.out.println("["+hero.getName()+"] Está exausto!");
    }
    
}

class Normal implements RPGState{
    @Override
    public void interagir(Hero hero) {
        System.out.println("["+hero.getName()+"] Está normal!");
    }
}

interface RPGCommand{
    public void execute();
}

class CastSpellCommand implements RPGCommand{

    private Hero hero;
    private String spell;

    public CastSpellCommand(Hero hero, String spell) {
        this.hero  = hero;
        this.spell = spell; 
    }

    @Override
    public void execute() {
        System.out.println("["+hero.getName()+"] lançou o feitiço "+spell);
    }

}

class ActionMenu{
    private RPGCommand command;

    public void setCommand(RPGCommand command) {
        this.command = command; 
    }

    public void executeAction() {
        this.command.execute();
    }
}

enum ClassType{
    WARRIOR,MAGE
}

interface Hero {
    public String getName();

    public void atacar();

    public void receberDano(int dano);
    public void interagir();

    public void setElementStrategy(RPGStrategy strategy);
}

class Mage implements Hero{
    private String name;
    private int hp;

    private RPGState state;

    private RPGStrategy strategy;

    public Mage(String name) {
        this.name = name; 
        this.hp = 100;
        this.state = new Normal(); 
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void receberDano(int dano) {
        hp-=dano;
        if (hp <= 0) {
            System.out.println("["+name+"] Morreu!");
        }
        else if (hp<=20) {
            System.out.println("["+name+"] Ficou exausto!");
            state = new Exaust();
        }
    }
    @Override
    public void interagir() {
        state.interagir(this);
    }
    @Override
    public void setElementStrategy(RPGStrategy strategy) {
        this.strategy = strategy;
    }
    @Override
    public void atacar() {
        strategy.execute(this);
    }

}

class Warrior implements Hero{
    private String name;
    private int hp;

    private RPGState state;

    private RPGStrategy strategy;

    public Warrior(String name) {
        this.name = name; 
        this.hp = 100;
        this.state = new Normal(); 
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void receberDano(int dano) {
        hp-=dano;
        if (hp <= 0) {
            System.out.println("["+name+"] Morreu!");
        }
        else if (hp<=20) {
            System.out.println("["+name+"] Ficou exausto!");
            state = new Exaust();
        }
    }
    @Override
    public void interagir() {
        state.interagir(this);
    }
    @Override
    public void setElementStrategy(RPGStrategy strategy) {
        this.strategy = strategy;
    }
    @Override
    public void atacar() {
        strategy.execute(this);
    }

}

class HeroFactory{
    public static Hero createHero(ClassType type, String name) {
        switch (type) {
            case WARRIOR:
                return new Warrior(name);
            case MAGE:
                return new Mage(name);
            default:
                return null;
        }
    }
}

class EventManager{

    private Map<String, RPGObserver> manager = new HashMap<>();

    public void subscribe(String event, RPGObserver observer) {
        manager.put(event, observer);
    }

    public void notify(String event, String msg) {
        manager.get(event).update(event, msg);
    }
}

interface RPGObserver{
    public void update(String event, String msg);
}

class AchievementSystem implements RPGObserver{

    @Override
    public void update(String event, String msg) {
        System.out.println("["+event+"] "+msg);
    }

}

class WorldEngine {
    private static WorldEngine wEngine;
    private EventManager eManager;

    private WorldEngine() {
        eManager = new EventManager();
    }

    public static WorldEngine getInstance() {
        if (wEngine == null) {
            wEngine = new WorldEngine();
        }
        return wEngine;
    }

    public String getRegion() {
        return "OverWorld";
    }

    public EventManager getEventManager() {
        return this.eManager;
    }
}