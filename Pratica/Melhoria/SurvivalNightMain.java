public class SurvivalNightMain {
    public static void main(String[] args) {

        System.out.println("=== SURVIVAL NIGHT ===");

        // ===== DECORATOR (arma + runas/efeitos empilháveis) =====
        Weapon weapon = new Knife();
        weapon = new FireRune(weapon);
        weapon = new PoisonRune(weapon);

        System.out.println("\n--- Arma equipada (Decorator) ---");
        weapon.use();

        // ===== CHAIN OF RESPONSIBILITY (pipeline de dano) =====
        SNDamageHandler damageChain =
                new SNArmorHandler(
                        new SNShieldHandler(
                                new SNPotionHandler(
                                        new SNHealthHandler(null))));

        SNPlayer player = new SNPlayer("Nova", 100);
        
        player.setWeapon(weapon);
        player.setDamageChain(damageChain);

        System.out.println("\n--- Estado inicial ---");
        System.out.println(player);

        // ===== TEMPLATE METHOD (noite com fases fixas, variações por dificuldade) =====
        NightTemplate easy = new EasyNight();
        NightTemplate hard = new HardNight();

        System.out.println("\n=== NOITE FÁCIL (Template Method) ===");
        easy.playNight(player);

        System.out.println("\n=== NOITE DIFÍCIL (Template Method) ===");
        hard.playNight(player);

        System.out.println("\n--- Estado final ---");
        System.out.println(player);
    }
}

abstract class NightTemplate {

    public final void playNight(SNPlayer player) {
        preparar();
        explorar(player);
        combater(player);
        terminar();
    }

    protected abstract void preparar();
    protected abstract void explorar(SNPlayer player);
    protected abstract void combater(SNPlayer player);

    public void terminar() {
        System.out.println("Terminando a noite!");
    }
}

class EasyNight extends NightTemplate {

    @Override
    public void preparar() {
        System.out.println("Preparando noite Easy!");
    }

    @Override
    public void explorar(SNPlayer player) {
        System.out.println(player.getName() + " está a explorar...");
        System.out.println("... e encontrou uma horda de morcegos!");
    }

    @Override
    public void combater(SNPlayer player) {
        System.out.println(player.getName()+ " a combater!");
        player.getWeapon().use();
        System.out.println("Morcegos lançam veneno! (30 dano)");
        SNDamageContext dano = new SNDamageContext(30);
        player.getHandler().handle(dano);
        System.out.println("Dano final: "+dano.getDano());
        player.setHp(player.getHp() - dano.getDano());
    }
}

class SNDamageContext {
    private int dano;

    public SNDamageContext(int dano) {
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = Math.max(0, dano);
    }
}

class HardNight extends NightTemplate {

    @Override
    public void preparar() {
        System.out.println("Preparando noite Hard!");
    }

    @Override
    public void explorar(SNPlayer player) {
        System.out.println(player.getName() + " está a explorar...");
        System.out.println("... e encontrou uma horda de Zombies!");
    }

    @Override
    public void combater(SNPlayer player) {
        System.out.println(player.getName()+ " a combater!");
        player.getWeapon().use();
        System.out.println("Zombies atacam! (70 dano)");
        SNDamageContext dano = new SNDamageContext(70);
        player.getHandler().handle(dano);
        System.out.println("Dano final: "+dano.getDano());
        player.setHp(player.getHp() - dano.getDano());
    }

}

class SNPlayer {
    private String name;
    private int hp;

    private SNDamageHandler handler;

    private Weapon weapon;

    public SNPlayer(String name, int hp) {
        this.name = name;
        this.hp = hp; 
    }

    public void setDamageChain(SNDamageHandler handler) {
        this.handler = handler;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public SNDamageHandler getHandler() {
        return handler;
    }

    public void setHandler(SNDamageHandler handler) {
        this.handler = handler;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public String toString() {
        return "SNPlayer [name=" + name + ", hp=" + hp + ", weapon=" + weapon + "]";
    }
}

interface Weapon{
    public void use();
}

class Knife implements Weapon{

    @Override
    public void use() {
        System.out.println("Using knife...");
    }

    @Override
    public String toString() {
        return "Knife";
    }

}

abstract class WeaponDecorator implements Weapon{

    protected Weapon weapon;

    public WeaponDecorator(Weapon weapon) {
        this.weapon = weapon; 
    }
}

class FireRune extends WeaponDecorator{

    public FireRune(Weapon weapon) {
        super(weapon);
    }

    @Override
    public void use() {
        weapon.use();
        System.out.println("with FireRune...");
    }

    @Override
    public String toString() {
        return weapon.toString() + " + FireRune";
    }

}

class PoisonRune extends WeaponDecorator{

    public PoisonRune(Weapon weapon) {
        super(weapon);
    }

    @Override
    public void use() {
        weapon.use();
        System.out.println("with PoisonRune");
    }

    @Override
    public String toString() {
        return weapon.toString() + " + PoisonRune";
    }
}

interface SNDamageHandler {
    public void handle(SNDamageContext dano);
}

class SNArmorHandler implements SNDamageHandler{
    private SNDamageHandler next;

    public SNArmorHandler(SNDamageHandler next) {
        this.next = next;
    }

    @Override
    public void handle(SNDamageContext dano) {
        dano.setDano(dano.getDano() - 5);
        if (!(next == null)) next.handle(dano);
    }
}

class SNShieldHandler implements SNDamageHandler{
    private SNDamageHandler next;

    public SNShieldHandler(SNDamageHandler next) {
        this.next = next;
    }

    @Override
    public void handle(SNDamageContext dano) {
        dano.setDano(dano.getDano() - 10);
        if (!(next == null)) next.handle(dano);
    }
}

class SNPotionHandler implements SNDamageHandler{
    private SNDamageHandler next;

    public SNPotionHandler(SNDamageHandler next) {
        this.next = next;
    }

    @Override
    public void handle(SNDamageContext dano) {
        dano.setDano(dano.getDano() - 2);
        if (!(next == null)) next.handle(dano);
    }
}

class SNHealthHandler implements SNDamageHandler{
    private SNDamageHandler next;

    public SNHealthHandler(SNDamageHandler next) {
        this.next = next;
    }

    @Override
    public void handle(SNDamageContext dano) {
        dano.setDano(dano.getDano() - 3);
        if (!(next == null)) next.handle(dano);
    }
}