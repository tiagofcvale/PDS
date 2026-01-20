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