import java.util.HashMap;
import java.util.Map;

public class GTA6RescueMain {
    public static void main(String[] args) {

        System.out.println("=== GTA6: RESGATE DO JASON ===");

        // ===== FLYWEIGHT (renderizar assets PESADOS da Lucia) =====
        // Ideia: a Lucia tem modelo/roupa/animações muito pesadas -> partilhar assets em cache (Flyweight)
        LuciaRenderFactory renderFactory = new LuciaRenderFactory();

        LuciaRenderAsset baseModel = renderFactory.getAsset(
                "LUCIA_BASE",
                "lucia_model_ultra.glb",
                "lucia_4k_textures.pack",
                "lucia_rig_advanced.anim"
        );

        LuciaEntity luciaCutscene = new LuciaEntity("Lucia-Cutscene", baseModel, 120, 340);
        LuciaEntity luciaGameplay = new LuciaEntity("Lucia-Gameplay", baseModel, 122, 345);
        LuciaEntity luciaPhotoMode = new LuciaEntity("Lucia-PhotoMode", baseModel, 118, 338);

        System.out.println("\n--- Render Lucia (Flyweight) ---");
        luciaCutscene.render();
        luciaGameplay.render();
        luciaPhotoMode.render();

        System.out.println("Assets partilhados em cache: " + renderFactory.getCacheSize());

        // ===== BUILDER (construir uma TOWER) =====
        // Ideia: tower tem muitos parâmetros opcionais -> Builder dá chaining e clareza
        Tower tower = new TowerBuilder()
                .name("Torre de Vigilância - Porto de Vice City")
                .foundation("Betão armado")
                .heightMeters(45)
                .material("Aço + Vidro")
                .securityLevel(4)
                .addModule("Holofotes")
                .addModule("Câmara térmica")
                .addModule("Sniper Nest")
                .powerSource("Gerador + Rede")
                .build();

        System.out.println("\n--- Tower construída (Builder) ---");
        System.out.println(tower);
        tower.deploy(10, 6);

        // ===== PROXY (reverse proxy fictício para “salvar o Jason da prisão”) =====
        // Ideia: o jogador usa um ReverseProxy “in-game” que encaminha pedidos para serviços internos do jogo
        // (sem hacking real). O proxy filtra/autoriza operações e simula o “resgate”.
        PrisonNetwork gateway = new ReverseProxyGateway(new RealPrisonNetwork());

        System.out.println("\n--- Operação: Libertar Jason (Reverse Proxy - mecânica de jogo) ---");
        gateway.connect("Lucia");
        gateway.request("Jason", "TRANSFER_REQUEST");     // pedido “legítimo” (ex.: transferência)
        gateway.request("Jason", "OVERRIDE_RELEASE");      // tentativa “não autorizada” -> proxy bloqueia
        gateway.request("Jason", "LAWYER_FAST_TRACK");     // rota “permitida” no jogo
        gateway.disconnect("Lucia");

        System.out.println("\n=== FIM ===");
    }
}

interface PrisonNetwork {
    void connect(String user);
    void request(String target, String action);
    void disconnect(String user);
}

class ReverseProxyGateway implements PrisonNetwork {

    private PrisonNetwork pNetwork;

    public ReverseProxyGateway(PrisonNetwork pNetwork) {
        this.pNetwork = pNetwork;
    }

    @Override
    public void connect(String user) {
        System.out.println("[REVERSE PROXY] A autenticar " + user + "...");
        pNetwork.connect(user);
    }

    @Override
    public void request(String target, String action) {

        if (action.equals("OVERRIDE_RELEASE")) {
            System.out.println("[REVERSE PROXY] Ação BLOQUEADA: " + action);
            return;
        }

        System.out.println("[REVERSE PROXY] Encaminhando pedido seguro: " + action);
        pNetwork.request(target, action);
    }

    @Override
    public void disconnect(String user) {
        pNetwork.disconnect(user);
        System.out.println("[REVERSE PROXY] Ligação encerrada.");
    }
}

class RealPrisonNetwork implements PrisonNetwork {

    @Override
    public void connect(String user) {
        System.out.println("[PRISON NET] " + user + " ligado ao sistema da prisão.");
    }

    @Override
    public void request(String target, String action) {
        System.out.println("[PRISON NET] Ação executada sobre " + target + ": " + action);
    }

    @Override
    public void disconnect(String user) {
        System.out.println("[PRISON NET] " + user + " desligado.");
    }
}

class Tower {
    private String name;
    private String foundation;
    private int heightMeters;
    private String material;
    private int securityLevel;
    private String module;
    private String powerSource;
    public void setName(String name) {
        this.name = name;
    }
    public void deploy(int i, int j) {
        System.out.println("Deploying tower "+name+ " "+foundation+ " "+heightMeters+ " "+material+ " "+securityLevel+ " "+module+ " "+powerSource);
    }
    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }
    public void setHeightMeters(int heightMeters) {
        this.heightMeters = heightMeters;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
    }
    public void setModule(String module) {
        this.module = module;
    }
    public void setPowerSource(String powerSource) {
        this.powerSource = powerSource;
    }
}

class TowerBuilder {

    private Tower tower = new Tower();

    public TowerBuilder name(String name) {
        tower.setName(name);
        return this;
    }

    public TowerBuilder foundation(String foundation) {
        tower.setFoundation(foundation);
        return this;
    }

    public TowerBuilder heightMeters(int meters) {
        tower.setHeightMeters(meters);
        return this;
    }

    public TowerBuilder material(String material) {
        tower.setMaterial(material);
        return this;
    }

    public TowerBuilder securityLevel(int sLevel) {
        tower.setSecurityLevel(sLevel);
        return this;
    }

    public TowerBuilder addModule(String module) {
        tower.setModule(module);
        return this;
    }

    public TowerBuilder powerSource(String pSource) {
        tower.setPowerSource(pSource);
        return this;
    }

    public Tower build() {
        return tower;
    }

}

class LuciaEntity {
    private String scene;
    private LuciaRenderAsset lRAsset;
    private int num1;
    private int num2;
    
    public LuciaEntity(String scene, LuciaRenderAsset lRAsset, int num1, int num2) {
        this.scene = scene;
        this.lRAsset = lRAsset;
        this.num1 = num1;
        this.num2 = num2;
    }

    public void render() {
        System.out.println(scene +" + "+ lRAsset+" + "+num1+" + "+num2+ "; LuciaRenderAsset: "+lRAsset.getModel()+" + "+lRAsset.getGlb() +" + "+lRAsset.getPack() +" + "+lRAsset.getAnim());
    }
}

class LuciaRenderAsset {

    private String model;
    private String glb;
    private String pack;
    private String anim;

    public LuciaRenderAsset(String model, String glb, String pack, String anim) {
        this.model = model;
        this.glb = glb;
        this.pack = pack;
        this.anim = anim;
    }

    public String getModel() {
        return model;
    }

    public String getGlb() {
        return glb;
    }

    public String getPack() {
        return pack;
    }

    public String getAnim() {
        return anim;
    }

}

class LuciaRenderFactory {

    private Map<String, LuciaRenderAsset> cache = new HashMap<>();

    public LuciaRenderAsset getAsset(String model, String glb, String pack, String anim) {
        String chave = model+glb+pack+anim;
        if (!cache.containsKey(chave)) {
            cache.put(chave, new LuciaRenderAsset(model, glb, pack, anim));
        }
        return cache.get(chave);
    }

    public int getCacheSize() {
        return cache.size();
    }
}