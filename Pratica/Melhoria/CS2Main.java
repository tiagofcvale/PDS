import java.util.HashMap;
import java.util.Map;

public class CS2Main {
    public static void main(String[] args) {
        // 1. SINGLETON
        GameEngine engine = GameEngine.getInstance();
        System.out.println("--- INICIANDO PARTIDA: " + engine.getMapName() + " ---");

        // 2. OBSERVER
        Radar radarSITREP = new Radar();
        engine.getEventManager().subscribe("bomb_planted", radarSITREP);

        // FACTORY
        CSPlayer tr1 = PlayerFactory.createPlayer(Side.TR, "FalleN");
        CSPlayer ct1 = PlayerFactory.createPlayer(Side.CT, "karrigan");

        // 3. COMMAND: O CT também precisa de comprar equipamento!
        System.out.println("\n--- FREEZE TIME (COMMAND PATTERN) ---");
        BuyMenu buyMenu = new BuyMenu();
        
        // TR compra AK-47
        buyMenu.setCommand(new BuyWeaponCommand(tr1, "AK-47"));
        buyMenu.executeBuy(); 
        
        // CT compra M4A1-S
        buyMenu.setCommand(new BuyWeaponCommand(ct1, "M4A1-S"));
        buyMenu.executeBuy();

        // 4. STATE: Movimentação tática
        System.out.println("\n--- COMBATE EM ANDAMENTO (STATE PATTERN) ---");
        ct1.mover(); // Começa saudável (HealthyState)
        
        System.out.println("(!) O TR surpreende o CT no Long...");
        ct1.receberDano(90); // CT fica com 10 HP
        ct1.mover();         // Agora move-se devagar (InjuredState)
        
        // 5. STRATEGY: O CT tenta retocar o bombsite com uma Flashbang
        System.out.println("\n--- UTILITÁRIOS (STRATEGY PATTERN) ---");
        // Imaginando que criaste uma classe FlashbangGrenade...
        ct1.setGranadeStrategy(new SmokeGrenade()); // Usando a Smoke que já tens
        ct1.lancarGranada();
        
        // 6. OBSERVER: Evento da bomba
        System.out.println("\n--- EVENTO GLOBAL (OBSERVER PATTERN) ---");
        engine.getEventManager().notify("bomb_planted", "C4 plantada no Bombsite A!");
        
        System.out.println("\n--- FIM DO ROUND ---");
    }
}

interface GranadeStrategy {

    public void execute(CSPlayer player);

}

class SmokeGrenade implements GranadeStrategy {

    @Override
    public void execute(CSPlayer player) {
        System.out.println("["+player.getName()+"] Lançando granada de fumo!");
    }

}

interface PlayerState{
    public void move();
}

class InjuredState implements PlayerState{

    private CSPlayer player;

    public InjuredState(CSPlayer player) {
        this.player = player;
    }

    @Override
    public void move() {
        System.out.println("["+player.getName()+"] Moveu-se devagar");
    }

}

class HealthyState implements PlayerState{
    private CSPlayer player;

    public HealthyState(CSPlayer player) {
        this.player = player;
    }

    @Override
    public void move() {
        System.out.println("["+player.getName()+"] Moveu-se");
    }
}

interface Command{
    public void execute();
}

class BuyWeaponCommand implements Command{
    private CSPlayer player;
    private String item;

    public BuyWeaponCommand(CSPlayer player, String item) {
        this.player = player;
        this.item = item; 
    }

    @Override
    public void execute() {
        this.player.compra(500, item);
    }

}

class BuyMenu{

    private Command command;

    public void setCommand(Command command) {
        this.command = command; 
    }

    public void executeBuy() {
        command.execute();
    }
}

enum Side{
    TR,CT
}

interface CSPlayer {
    public void receberDano(int dano);
    void mover();
    void compra(int price, String weapon);
    
    public String getName();

    public void setGranadeStrategy(GranadeStrategy granade);
    public void lancarGranada();
}

class JogadorCT implements CSPlayer{

    private int hp;
    private int money;
    private String name;
    private String weapon;

    private GranadeStrategy granade;

    private PlayerState state;
    
    public JogadorCT(String name) {
        this.name = name;
        this.hp = 100;
        this.money = 1000;

        this.state = new HealthyState(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void receberDano(int dano) {
        this.hp-=dano;
        if (this.hp <= 20) {
            System.out.println("["+name+"] Está ferido!");
            this.state = new InjuredState(this); 
        }
    }

    @Override
    public void mover() {
        state.move();
    }

    @Override
    public void compra(int price, String weapon) {
        if(money<price) {
            System.out.println("Not enough budget");
        } else {
            this.money -= price;
            this.weapon = weapon;
            System.out.println("["+name+"] Comprou "+weapon+" por "+price+"$");
        }
    }

    @Override
    public void setGranadeStrategy(GranadeStrategy granade) {
        this.granade = granade;
    }

    @Override
    public void lancarGranada() {
        this.granade.execute(this);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public GranadeStrategy getGranade() {
        return granade;
    }

    public void setGranade(GranadeStrategy granade) {
        this.granade = granade;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }
}

class JogadorTR implements CSPlayer{

    private int hp;
    private int money;
    private String name;
    private String weapon;

    private GranadeStrategy granade;

    private PlayerState state;
    
    public JogadorTR(String name) {
        this.name = name;
        this.hp = 100;
        this.money = 1000;

        this.state = new HealthyState(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void receberDano(int dano) {
        this.hp-=dano;
        if (this.hp <= 20) {
            System.out.println("["+name+"] Está ferido!");
            this.state = new InjuredState(this); 
        }
    }

    @Override
    public void mover() {
        state.move();
    }

    @Override
    public void compra(int price, String weapon) {
        if(money<price) {
            System.out.println("Not enough budget");
        } else {
            this.money -= price;
            this.weapon = weapon;
            System.out.println("["+name+"] Comprou "+weapon+" por "+price+"$");
        }
    }

    @Override
    public void setGranadeStrategy(GranadeStrategy granade) {
        this.granade = granade;
    }

    @Override
    public void lancarGranada() {
        this.granade.execute(this);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public GranadeStrategy getGranade() {
        return granade;
    }

    public void setGranade(GranadeStrategy granade) {
        this.granade = granade;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }
}

class PlayerFactory{
    public static CSPlayer createPlayer(Side side, String name) {
        switch (side) {
            case TR:
                return new JogadorTR(name);
            case CT:
                return new JogadorCT(name);
            default:
                return null;
        }
    } 
}

class EventManager {
    private Map<String, EventListener> listeners = new HashMap<>();

    public void subscribe(String msg, EventListener listener) {
        listeners.put(msg, listener);
    }

    public void notify(String event, String msg) {
        if (listeners.containsKey(event)) {
            listeners.get(event).update(event, msg);
        }
    }
}

interface EventListener{
    public void update(String event, String msg);
}

class Radar implements EventListener {
    public void update(String event, String msg) {
        System.out.println(event+": "+msg);
    }
}

class GameEngine{

    private static GameEngine gEngine;
    private EventManager eManager;

    private GameEngine(){
        this.eManager = new EventManager();
    }

    public static GameEngine getInstance() {
        if (gEngine == null) {
            gEngine = new GameEngine();
        }
        return gEngine;
    }

    public EventManager getEventManager() {
        return this.eManager;
    }

    public String getMapName() {
        return "Mirage";
    }
}
