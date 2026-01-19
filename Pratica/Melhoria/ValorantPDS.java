import java.util.ArrayList;
import java.util.List;

public class ValorantPDS {
    public static void main(String[] args) {
        ChatMediator valorantChat = new ValorantChat();

        System.out.println("--- FASE DE CRIAÇÃO (FACTORY) ---");
        Jogador p1 = AgenteFactory.criarAgente(Role.DUELIST, "Phoenix", 1, valorantChat);
        Jogador p2 = AgenteFactory.criarAgente(Role.CONTROLLER, "Sage", 2, valorantChat);
        Jogador p3 = AgenteFactory.criarAgente(Role.SENTINEL, "Jett", 3, valorantChat);

        valorantChat.addJogador(p3);
        valorantChat.addJogador(p2);
        valorantChat.addJogador(p1);

        System.out.println("\n--- FASE DE ARMAS (STRATEGY) ---");
        p1.setArma(new Rifle());
        p3.setArma(new Sniper());
        p1.atacar();
        p3.atacar();

        System.out.println("\n--- FASE DE COMUNICAÇÃO (MEDIATOR) ---");
        p1.enviarRadio("Inimigos no B!"); //// Só P2 (Equipa A) deve receber 

        System.out.println("\n--- FASE DE UPGRADES (DECORATOR) ---");
        // Phoenix ganha escudo e dano de fogo (empilhados)
        p1 = new EscudoDecorator(p1);
        p1 = new DanoFogoDecorator(p1);

        p1.usarHabilidade(); // Habilidade base + bónus do decorator
        p1.atacar();         // Ataque da arma + bónus do decorator
        tryToUseAbility(p2);
    }
    public static void tryToUseAbility(Jogador j) {
        j.usarHabilidade();
    }
}

abstract class JogadorDecorator implements Jogador{
    protected Jogador jogador;

    public JogadorDecorator(Jogador jogador) {
        this.jogador = jogador;
    }
}

class EscudoDecorator extends JogadorDecorator{

    public EscudoDecorator(Jogador jogador) {
        super(jogador);
    }

    @Override
    public void run(Point2D p) {
        jogador.run(p);
    }

    @Override
    public void receive(String msg) {
        jogador.receive(msg);
    }

    @Override
    public void walk(Point2D p) {
        jogador.walk(p);
    }

    @Override
    public void atacar() {
        jogador.atacar();
    }

    @Override
    public void setArma(Arma a) {
        jogador.setArma(a);
    }

    @Override
    public void enviarRadio(String msg) {
        jogador.enviarRadio(msg);
    }

    @Override
    public void usarHabilidade() {
        System.out.println("Usando abilidade de Escudo!");
    }

}

class DanoFogoDecorator extends JogadorDecorator{

    public DanoFogoDecorator(Jogador jogador) {
        super(jogador);
    }

    @Override
    public void run(Point2D p) {
        jogador.run(p);
    }

    @Override
    public void receive(String msg) {
        jogador.receive(msg);
    }

    @Override
    public void walk(Point2D p) {
        jogador.walk(p);
    }

    @Override
    public void atacar() {
        jogador.atacar();
    }

    @Override
    public void setArma(Arma a) {
        jogador.setArma(a);
    }

    @Override
    public void enviarRadio(String msg) {
        jogador.enviarRadio(msg);
    }

    @Override
    public void usarHabilidade() {
        System.out.println("Usando abilidade de Dano de Fogo!");
    }
}

interface ChatMediator{
    void notify(Jogador j, String msg);
    void addJogador(Jogador j);
}

class ValorantChat implements ChatMediator{
    List<Jogador> jogadores = new ArrayList<>();

    @Override
    public void notify(Jogador j, String msg) {
        for (Jogador jogador : jogadores) {
            if (!jogador.equals(j)) {
                jogador.receive(msg);
            }
        }
    }

    @Override
    public void addJogador(Jogador j) {
        this.jogadores.add(j);
    }

}

class Point2D{
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
    @Override
    public String toString() {
        return "Point2D [x=" + x + ", y=" + y + "]";
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
    @Override
    public String toString() {
        return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
}

interface Jogador  {
    void run(Point2D p);
    void receive(String msg);
    void walk(Point2D p);
    void atacar();
    void setArma(Arma a);

    void enviarRadio(String msg);
    void usarHabilidade();
}

interface Arma {
    void point(Point3D p);
    void shoot();
}

class Rifle implements Arma{

    @Override
    public void point(Point3D p) {
        System.out.println("Ponting to "+p);
    }

    @Override
    public void shoot() {
        System.out.println("Shooting!");
    }
}

class Sniper implements Arma{

    @Override
    public void point(Point3D p) {
        System.out.println("Ponting to "+p);
    }

    @Override
    public void shoot() {
        System.out.println("Shooting!");
    }
}

class Duelist implements Jogador{
    
    private String name;
    private int id;
    private ChatMediator chat;
    private Arma arma;

    public Duelist(String name, int id, ChatMediator chat) {
        this.name = name;
        this.id = id;
        this.chat = chat;
    }
    @Override
    public void run(Point2D p) {
        System.out.println("Running to "+p);
    }
    @Override
    public void walk(Point2D p) {
        System.out.println("Walking to "+p);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ChatMediator getChat() {
        return chat;
    }
    public void setChat(ChatMediator chat) {
        this.chat = chat;
    }
    public Arma getArma() {
        return arma;
    }
    public void setArma(Arma a) {
        this.arma = a;
    }
    @Override
    public void atacar() {
        this.arma.shoot();
    }
    @Override
    public void enviarRadio(String msg) {
        this.chat.notify(this, msg);
    }
    @Override
    public void receive(String msg) {
        System.out.println(msg);
    }
    @Override
    public void usarHabilidade() {
        System.out.println("Nenhuma abilidade");
    }
}

class Controller implements Jogador {
    private String name;
    private int id;
    private ChatMediator chat;
    private Arma arma;

    public Controller(String name, int id, ChatMediator chat) {
        this.name = name;
        this.id = id;
        this.chat = chat;
    }
    @Override
    public void run(Point2D p) {
        System.out.println("Running to "+p);
    }
    @Override
    public void walk(Point2D p) {
        System.out.println("Walking to "+p);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ChatMediator getChat() {
        return chat;
    }
    public void setChat(ChatMediator chat) {
        this.chat = chat;
    }
    public Arma getArma() {
        return arma;
    }
    public void setArma(Arma a) {
        this.arma = a;
    }
    @Override
    public void atacar() {
        this.arma.shoot();
    }
    @Override
    public void enviarRadio(String msg) {
        this.chat.notify(this, msg);
    }
    @Override
    public void receive(String msg) {
        System.out.println(msg);
    }
    @Override
    public void usarHabilidade() {
        System.out.println("Nenhuma abilidade");
    }
}

class Sentinel implements Jogador {
    private String name;
    private int id;
    private ChatMediator chat;
    private Arma arma;

    public Sentinel(String name, int id, ChatMediator chat) {
        this.name = name;
        this.id = id;
        this.chat = chat;
    }
    @Override
    public void run(Point2D p) {
        System.out.println("Running to "+p);
    }
    @Override
    public void walk(Point2D p) {
        System.out.println("Walking to "+p);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ChatMediator getChat() {
        return chat;
    }
    public void setChat(ChatMediator chat) {
        this.chat = chat;
    }
    public Arma getArma() {
        return arma;
    }
    public void setArma(Arma a) {
        this.arma = a;
    }
    @Override
    public void atacar() {
        this.arma.shoot();
    }
    @Override
    public void enviarRadio(String msg) {
        this.chat.notify(this, msg);
    }
    @Override
    public void receive(String msg) {
        System.out.println(msg);
    }
    @Override
    public void usarHabilidade() {
        System.out.println("Nenhuma abilidade");
    }
}

enum Role {
    DUELIST,SENTINEL,CONTROLLER
}

class AgenteFactory{
    public static Jogador criarAgente(Role role, String name, int id, ChatMediator chat) {
        switch (role) {
            case DUELIST:
                return new Duelist(name, id, chat);
            case CONTROLLER:
                return new Controller(name,id,chat);
            case SENTINEL:
                return new Sentinel(name,id, chat);
            default:
                return null;
        }
    }
}