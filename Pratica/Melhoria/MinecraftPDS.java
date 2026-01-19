import java.util.ArrayList;
import java.util.List;

public class MinecraftPDS {
    public static void main(String[] args) {
        WorldServer servidor = new WorldServer();

        System.out.println("--- 1. GERANDO ENTIDADES ---");
        Entity p1 = MobFactory.spawn(EntityType.PLAYER, "Steve", servidor);
        Entity m1 = MobFactory.spawn(EntityType.HOSTILE, "Creeper", servidor);

        servidor.registerEntity(p1);
        servidor.registerEntity(m1);

        System.out.println("\n--- 2. SISTEMA DE FERRAMENTAS ---");
        p1.setTool(new Pickaxe());
        p1.interact(); // Output: "Minando blocos de pedra..."

        p1.setTool(new Axe());
        p1.interact(); // Output: "Cortando madeira de carvalho..."

        System.out.println("\n--- 3. COMUNICAÇÃO NO SERVIDOR ---");
        p1.sendGlobalMessage("Cuidado com o Creeper!"); 

        System.out.println("\n--- 4. MESA DE ENCANTAMENTOS ---");
        // Começamos com uma picareta simples
        Tool minhaPicareta = new Pickaxe();

        // Aplicamos camadas de melhorias
        minhaPicareta = new EfficiencyEnchantment(minhaPicareta);
        minhaPicareta = new SilkTouchEnchantment(minhaPicareta);

        System.out.println("Status da Picareta:");
        minhaPicareta.use(); 
        // Deve mostrar o uso da picareta + os efeitos dos dois encantamentos
    }
}

abstract class ToolDecorator implements Tool{
    protected Tool decoratedTool;

    public ToolDecorator(Tool decoratedTool) {
        this.decoratedTool = decoratedTool;
    }

    @Override
    public void use() {
        decoratedTool.use();
    }
}

class EfficiencyEnchantment extends ToolDecorator {

    public EfficiencyEnchantment(Tool tool) {
        super(tool);
    }

    @Override
    public void use() {
        super.use();
        System.out.println("...com encatamento de eficiência!");
    }

}

class SilkTouchEnchantment extends ToolDecorator {

    public SilkTouchEnchantment(Tool tool) {
        super(tool);
    }

    @Override
    public void use() {
        super.use();
        System.out.println("...com encatamento de SilkTouch!");
    }

}

interface Tool{
    public void use();
}

class Pickaxe implements Tool{

    @Override
    public void use() {
        System.out.println("Minerando blocos de pedra... ");
    }

}

class Axe implements Tool{

    @Override
    public void use() {
        System.out.println("Cortando madeira de carvalho... ");
    }

}

interface Entity {
    public void setTool(Tool tool);
    public void interact();

    public String getName();

    public void sendGlobalMessage(String msg);
}

class Player implements Entity{
    private String name;
    private WorldServer server;
    private Tool tool;

    public Player(String name, WorldServer server) {
        this.name = name;
        this.server = server;
    }

    @Override
    public void setTool(Tool tool) {
        this.tool = tool;
    }

    @Override
    public void interact() {
        tool.use();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorldServer getServer() {
        return server;
    }

    public void setServer(WorldServer server) {
        this.server = server;
    }

    public Tool getTool() {
        return tool;
    }

    @Override
    public void sendGlobalMessage(String msg) {
        this.server.notifyAll(this,msg);
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", server=" + server + ", tool=" + tool + "]";
    }
}

class Hostile implements Entity{
    private String name;
    private WorldServer server;
    private Tool tool;

    public Hostile(String name, WorldServer server) {
        this.name = name;
        this.server = server;
    }

    @Override
    public void setTool(Tool tool) {
        this.tool = tool;
    }

    @Override
    public void interact() {
        tool.use();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorldServer getServer() {
        return server;
    }

    public void setServer(WorldServer server) {
        this.server = server;
    }

    public Tool getTool() {
        return tool;
    }

    @Override
    public void sendGlobalMessage(String msg) {
        this.server.notifyAll(this,msg);
    }

    @Override
    public String toString() {
        return "Hostile [name=" + name + ", server=" + server + ", tool=" + tool + "]";
    }
}

class MobFactory{
    public static Entity spawn(EntityType type,String name, WorldServer server) {
        switch (type) {
            case PLAYER:
                return new Player(name, server);        
            default:
                return new Hostile(name, server);
        }
    }
}

enum EntityType{
    PLAYER,HOSTILE
}

class WorldServer {
    List<Entity> entidades = new ArrayList<>();

    public void registerEntity(Entity e) {
        this.entidades.add(e);
    }

    public void notifyAll(Entity e, String msg) {
        for (Entity entity : entidades) {
            if (!entity.equals(e)) {
                System.out.println(e.getName()+": "+msg);
            }
        }
    }
}