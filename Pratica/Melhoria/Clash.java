public class Clash {
    public static void main(String[] args) {
        // 1. SINGLETON / MEDIATOR (O Campo de Batalha)
        Arena arena = Arena.getInstance();
        System.out.println(arena.getName());

        System.out.println("--- FASE DE DECK (FACTORY) ---");
        // Criando unidades através da Factory
        Carta c1 = CartaFactory.criarCarta(Role.TROPA, "Gigante", 5);
        Carta c2 = CartaFactory.criarCarta(Role.TROPA, "Arqueiras", 3);
        Carta c3 = CartaFactory.criarCarta(Role.FEITICO, "Bola de Fogo", 4);

        c3.getName();

        System.out.println("\n--- FASE DE COMBATE (STRATEGY) ---");
        // Definindo comportamentos de ataque diferentes para as tropas
        c1.setComportamentoAtaque(new AtaqueCorpoACorpo());
        c2.setComportamentoAtaque(new AtaqueDistancia());

        c1.jogar();
        c2.jogar();

        System.out.println("\n--- EVENTO DE TORRE (OBSERVER) ---");
        // O Rei precisa saber quando uma torre cai para ativar o seu canhão
        TorrePrincesa torreEsq = new TorrePrincesa("Esquerda");
        TorreRei rei = new TorreRei();

        torreEsq.adicionarObservador(rei);
        torreEsq.receberDano(1000); // Simulando destruição

        System.out.println("\n--- FASE DE EVOLUÇÃO (DECORATOR) ---");
        // Aplicando a mecânica de "Evolução" (Card Evolution)
        System.out.println("As Arqueiras estão brilhando...");
        c2 = new EvolucaoEstatisticaDecorator(c2); // Aumenta HP/Dano
        c2 = new EvolucaoHabilidadeDecorator(c2);    // Adiciona efeito visual/especial

        c2.jogar();
    }
}

abstract class CartaDecorator implements Carta {
    protected Carta carta;

    public CartaDecorator(Carta carta) {
        this.carta=carta;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    @Override
    public int getDano() {
        return carta.getDano();
    }
}

class EvolucaoHabilidadeDecorator extends CartaDecorator{

    public EvolucaoHabilidadeDecorator(Carta carta) {
        super(carta);
    }

    @Override
    public void setComportamentoAtaque(StrategyI strat) {
        carta.setComportamentoAtaque(strat);
    }

    @Override
    public void jogar() {
        carta.jogar();
    }

    @Override
    public String getName() {
        return carta.getName();
    }

    @Override
    public int getDano() {
        return super.getDano() + 5;
    }

}

class EvolucaoEstatisticaDecorator extends CartaDecorator{

    public EvolucaoEstatisticaDecorator(Carta carta) {
        super(carta);
    }

    @Override
    public void setComportamentoAtaque(StrategyI strat) {
        carta.setComportamentoAtaque(strat);
    }

    @Override
    public void jogar() {
        carta.jogar();
    }

    @Override
    public String getName() {
        return carta.getName();
    }

    @Override
    public int getDano() {
        return super.getDano() + 5;
    }

}

interface ObserverI {
    public void notify(String msg, TorrePrincesa torre);
}

class TorreRei implements ObserverI{

    @Override
    public void notify(String msg, TorrePrincesa torre) {
        System.out.println(msg + " (" + torre.getName() + ")");
    }

}

class TorrePrincesa{
    private ObserverI rei;

    private String name;
    private int vida;
    public TorrePrincesa(String name) {
        this.name = name;
        this.vida = 1000;
        rei = new TorreRei(); 
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }

    public void receberDano(int dano) {
        this.vida = vida - dano; 
        checkStatus();
    }

    private void checkStatus() {
        if (vida <= 0) {
            rei.notify("A Torre caiu!",this);
        }
    }
    public ObserverI getRei() {
        return rei;
    }
    public void adicionarObservador(ObserverI rei) {
        this.rei = rei;
    }
    
}

interface StrategyI{
    public void execute(Carta c);
}

class AtaqueCorpoACorpo implements StrategyI {

    @Override
    public void execute(Carta c) {
        System.out.println(c.getName()+" atacando corpo a corpo");
    }

}

class AtaqueDistancia implements StrategyI {

    @Override
    public void execute(Carta c) {
        System.out.println(c.getName()+" atacando à distancia.");
    }

}

enum Role{
    TROPA,FEITICO
}

interface Carta{
    public void setComportamentoAtaque(StrategyI strat);
    public void jogar();

    public String getName();
    public int getDano();
}

class Tropa implements Carta{
    private String name;
    private int dano;
    private StrategyI comportamentoAtaque;

    public Tropa(String name, int dano) {
        this.name = name;
        this.dano = dano;
    }

    @Override
    public void setComportamentoAtaque(StrategyI strat) {
        this.comportamentoAtaque = strat;
    }

    @Override
    public void jogar() {
        comportamentoAtaque.execute(this);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public StrategyI getComportamentoAtaque() {
        return comportamentoAtaque;
    }
    
}

class Feitico implements Carta{
    private String name;
    private int dano;
    private StrategyI comportamentoAtaque;

    public Feitico(String name, int dano) {
        this.name = name;
        this.dano = dano;
    }

    @Override
    public void setComportamentoAtaque(StrategyI strat) {
        this.comportamentoAtaque = strat;
    }

    @Override
    public void jogar() {
        comportamentoAtaque.execute(this);
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public StrategyI getComportamentoAtaque() {
        return comportamentoAtaque;
    }
    
}

class CartaFactory{
    public static Carta criarCarta(Role role, String name, int dano) {
        switch (role) {
            case TROPA:
                return new Tropa(name,dano);
            case FEITICO:
                return new Feitico(name, dano);
            default:
                return null;
        }
    }
}

class Arena{
    private static Arena arena;
    private String name;

    private Arena() {
        this.name = "Electrovale"; 
    }

    public static Arena getInstance() {
        if (arena==null) {
            return new Arena();
        }
        return arena;
    }

    public String getName() {
        return name;
    }

}