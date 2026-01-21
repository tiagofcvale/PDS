public class GTA6Main {
    public static void main(String[] args) {

        System.out.println("=== GTA6: VICE CITY ONLINE HEIST ===");

        // ===== FLYWEIGHT (partilhar skins/modelos de NPCs/veículos) =====
        AssetFactory assetFactory = new AssetFactory();

        Asset npcCopModel = assetFactory.getAsset("NPC_COP", "cop_model.glb", "cop_tex.png");
        Asset npcCivilModel = assetFactory.getAsset("NPC_CIVIL", "civil_model.glb", "civil_tex.png");

        NPC cop1 = new NPC("Cop#1", npcCopModel, 10, 20);
        NPC cop2 = new NPC("Cop#2", npcCopModel, 12, 22);
        NPC civil1 = new NPC("Civil#1", npcCivilModel, 5, 7);

        System.out.println("\n--- Spawn de NPCs (Flyweight) ---");
        cop1.spawn();
        cop2.spawn();
        civil1.spawn();

        System.out.println("Assets em cache (partilhados): " + assetFactory.getCacheSize());

        // ===== BUILDER (montar loadout do assalto) =====
        HeistLoadout loadout = new HeistLoadoutBuilder()
                .crewLeader("Lucia")
                .driver("Jason")
                .getawayCar("Pegassi Infernus")
                .primaryWeapon("SMG")
                .secondaryWeapon("Pistola")
                .tool("C4")
                .tool("Lockpick")
                .mask("Balaclava preta")
                .build();

        System.out.println("\n--- Loadout do Assalto (Builder) ---");
        System.out.println(loadout);

        // ===== PROXY (servidor online / anti-cheat / permissões) =====
        OnlineServer server = new OnlineServerProxy(new RealOnlineServer());

        System.out.println("\n--- GTA6 Online (Proxy) ---");
        server.connect("Lucia");
        server.requestAction("Lucia", "StartHeist");
        server.requestAction("Lucia", "SpeedHack"); // proxy deve bloquear
        server.disconnect("Lucia");

        System.out.println("\n=== FIM ===");
    }
}
