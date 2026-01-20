import java.util.ArrayList;
import java.util.List;

public class ShooterMain {
    public static void main(String[] args) {

        // ===== FACADE (arrancar partida com uma chamada simples) =====
        MatchFacade match = new MatchFacade();
        match.quickStart("Dust II", 10); // mapa + nº jogadores

        // ===== PROXY (anti-cheat / validação / controlo de acesso) =====
        GameServer server = new GameServerProxy(new RealGameServer());
        server.connect("Steve");
        server.sendAction("Steve", "shoot");
        server.sendAction("Steve", "speedHack"); // proxy deve bloquear/recusar

        // ===== CHAIN OF RESPONSIBILITY (pipeline de dano) =====
        DamageHandler chain =
                new ArmorHandler(
                        new ShieldHandler(
                                new CriticalHandler(
                                        new HealthHandler(null))));

        DamageContext hit = new DamageContext("Steve", 45); // alvo + dano base
        chain.handle(hit);

        System.out.println("--- Shooter ---");
        System.out.println("Dano final aplicado: " + hit.getFinalDamage());
    }
}

class DamageContext {
    private final String player;
    private int dano;

    public DamageContext(String player, int dano) {
        this.player = player;
        this.dano = dano;
    }

    public int getFinalDamage() {
        return dano;
    }

    public void changeDamage(int change) {
        dano -= change;
        if (dano < 0) dano = 0;
    }

    public String getPlayer() {
        return player;
    }
}

interface DamageHandler{
    public void handle(DamageContext hit);
}

class ArmorHandler implements DamageHandler {
    private final DamageHandler next;

    public ArmorHandler(DamageHandler next) {
        this.next = next;
    }

    @Override
    public void handle(DamageContext hit) {
        hit.changeDamage(10);
        System.out.println("ArmorHandler: -10 (armadura)");
        if (next != null) next.handle(hit);
    }
}

class ShieldHandler implements DamageHandler {
    private final DamageHandler next;

    public ShieldHandler(DamageHandler next) {
        this.next = next;
    }

    @Override
    public void handle(DamageContext hit) {
        hit.changeDamage(5);
        System.out.println("ShieldHandler: -5 (escudo)");
        if (next != null) next.handle(hit);
    }
}

class CriticalHandler implements DamageHandler {
    private final DamageHandler next;

    public CriticalHandler(DamageHandler next) {
        this.next = next;
    }

    @Override
    public void handle(DamageContext hit) {
        System.out.println("CriticalHandler: sem crítico");
        if (next != null) next.handle(hit);
    }
}

class HealthHandler implements DamageHandler {
    private final DamageHandler next;

    public HealthHandler(DamageHandler next) {
        this.next = next;
    }

    @Override
    public void handle(DamageContext hit) {
        System.out.println("HealthHandler: aplicado " + hit.getFinalDamage() + " ao HP");
        if (next != null) next.handle(hit);
    }
}

interface GameServer{
    public void sendAction(String player, String action);
    public void connect(String player);
    public void kick(String player);
}

class GameServerProxy implements GameServer {
    private GameServer gServer;

    public GameServerProxy(GameServer gServer) {
        this.gServer = gServer;
    }

    @Override
    public void sendAction(String player, String action) {
        if (action.equals("speedHack")) {
            System.out.println("[BLOQUEADO] Ação suspeita: "+player+" -> "+action+" (kick aplicado)");
        } else {
            gServer.sendAction(player, action);
        }
    }

    @Override
    public void connect(String player) {
        gServer.connect(player);
        System.out.println("[OK] " + player + " ligou-se ao servidor.");
    }

    @Override
    public void kick(String player) {
        gServer.kick(player);
        System.out.println("[KICK] " + player + " foi removido do servidor.");
    }
}

class RealGameServer implements GameServer {

    private List<String> players = new ArrayList<>();

    @Override
    public void sendAction(String player, String action) {
        System.out.println(player+" "+action+"!");
    }

    @Override
    public void connect(String player) {
        players.add(player);
        System.out.println("[OK] "+player+" ligou-se ao servidor.");
    }

    @Override
    public void kick(String player) {
        players.remove(player);
    }
}

class MatchFacade {

    private final LobbySystem lobby = new LobbySystem();
    private final MapSystem mapSystem = new MapSystem();
    private final TeamSystem teamSystem = new TeamSystem();
    private final CountdownSystem countdown = new CountdownSystem();
    private final MatchSystem matchSystem = new MatchSystem();
    private final ServerSystem serverSystem = new ServerSystem();

    public void quickStart(String mapa, int nPlayers) {
        System.out.println("--- MatchFacade ---");

        lobby.createLobby();

        serverSystem.boot();

        List<String> players = lobby.fillWithMockPlayers(nPlayers);

        mapSystem.loadMap(mapa);

        teamSystem.assignTeams(players);

        countdown.start(3);

        matchSystem.start(mapa, players.size());

        System.out.println("Partida iniciada!\n");
    }
}

/* ===================== SUBSISTEMAS (internos) ===================== */

class LobbySystem {
    private final List<String> players = new ArrayList<>();

    public void createLobby() {
        System.out.println("A criar lobby...");
        players.clear();
    }

    public List<String> fillWithMockPlayers(int nPlayers) {
        // Exemplo simples: cria nomes fake para simular o lobby cheio
        for (int i = 1; i <= nPlayers; i++) {
            players.add("Player" + i);
        }
        System.out.println("Lobby cheio: " + players.size() + " jogadores.");
        return new ArrayList<>(players);
    }
}

class MapSystem {
    public void loadMap(String mapName) {
        System.out.println("A carregar mapa: " + mapName);
    }
}

class TeamSystem {
    public void assignTeams(List<String> players) {
        System.out.println("A distribuir equipas (" + players.size() + " jogadores)...");
        // Simples: metade para CT, metade para T
        int half = players.size() / 2;
        System.out.println("CT: " + players.subList(0, half));
        System.out.println("T:  " + players.subList(half, players.size()));
    }
}

class CountdownSystem {
    public void start(int seconds) {
        System.out.println("A iniciar contagem decrescente...");
        for (int i = seconds; i >= 1; i--) {
            System.out.println(i + "...");
        }
        System.out.println("GO!");
    }
}

class MatchSystem {
    public void start(String mapName, int nPlayers) {
        System.out.println("A iniciar partida no " + mapName + " com " + nPlayers + " jogadores.");
    }
}

class ServerSystem {
    public void boot() {
        System.out.println("A iniciar servidor da partida...");
    }
}
