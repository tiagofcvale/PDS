import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StrategyBoardMain {
    public static void main(String[] args) {

        // ===== ITERATOR (iterar unidades do exército sem expor estrutura) =====
        Army army = new Army();
        army.add(new Unit("Infantaria", 100));
        army.add(new Unit("Arqueiros", 70));
        army.add(new Unit("Cavalaria", 150));

        System.out.println("--- Unidades (Iterator) ---");
        for (Unit u : army) { // army devolve iterator
            System.out.println(u.getName() + " -> " + u.getPower());
        }

        // ===== TEMPLATE METHOD (turno fixo, passos variáveis por facção) =====
        TurnTemplate humans = new HumansTurn();
        TurnTemplate orcs = new OrcsTurn();

        System.out.println("\n--- Turno Humanos (Template Method) ---");
        humans.playTurn(army);

        System.out.println("\n--- Turno Orcs (Template Method) ---");
        orcs.playTurn(army);

        // ===== VISITOR (aplicar operação sem alterar classes das peças) =====
        Board board = new Board();
        board.place(new Tile("Floresta"));
        board.place(new Tile("Montanha"));
        board.place(new Tile("Cidade"));

        Visitor scoreVisitor = new ScoreVisitor();
        Visitor renderVisitor = new RenderVisitor();

        System.out.println("\n--- Visitor: Render ---");
        board.accept(renderVisitor);

        System.out.println("\n--- Visitor: Score ---");
        board.accept(scoreVisitor);
        System.out.println("Pontuação calculada: " + ((ScoreVisitor) scoreVisitor).getScore());
    }
}

class Tile{
    private String tile;

    public Tile(String tile) {
        this.tile = tile;
    }


    public void setTitle(String title) {
        this.tile = title;
    }

    public String getType() {
        return tile;
    }

}

interface Visitor{
    public void visit(Board board);
}

class ScoreVisitor implements Visitor {

    private int score = 0;

    @Override
    public void visit(Board board) {
        for (Tile tile : board.getTiles()) {
            switch (tile.getType()) {
                case "Floresta":
                    score += 10;
                    break;
                case "Montanha":
                    score += 15;
                    break;
                case "Cidade":
                    score += 25;
                    break;
            }
        }
    }

    public int getScore() {
        return score;
    }

}

class RenderVisitor implements Visitor {

    @Override
    public void visit(Board board) {

        for (Tile tile : board.getTiles()) {
            System.out.println("Tile: " + tile.getType());
        }
    }
}

class Board {
    List<Tile> tiles = new ArrayList<>();

    public void place(Tile tile) {
        tiles.add(tile);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }
}

abstract class TurnTemplate{

    public final void playTurn(Army army) {
        startTurn();
        collectResources(army);
        moveUnits(army);
        attack(army);
        endTurn();
    }

    protected abstract void collectResources(Army army);
    protected abstract void moveUnits(Army army);
    protected abstract void attack(Army army);
    
    protected void startTurn() {
        System.out.println("Início do turno...");
    }

    protected void endTurn() {
        System.out.println("Fim do turno.");
    }
}

class HumansTurn extends TurnTemplate{

    @Override
    protected void collectResources(Army army) {
        System.out.println("[Humans] Recolher recursos de forma organizada.");
    }

    @Override
    protected void moveUnits(Army army) {
        System.out.println("[Humans] Mover unidades estrategicamente.");
    }

    @Override
    protected void attack(Army army) {
        System.out.println("[Humans] Atacar apenas se houver vantagem.");
    }
}

class OrcsTurn extends TurnTemplate {

    @Override
    protected void collectResources(Army army) {
        System.out.println("[Orcs] Saquear recursos!");
    }

    @Override
    protected void moveUnits(Army army) {
        System.out.println("[Orcs] Avançar em grupo.");
    }

    @Override
    protected void attack(Army army) {
        System.out.println("[Orcs] Atacar com fúria!");
    }
}


class Unit{
    private String name;
    private int power;

    public Unit(String name, int power) {
        this.name = name;
        this.power = power; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}

class Army implements Iterable<Unit>{

    private List<Unit> unidades = new ArrayList<>();

    public void add(Unit unit) {
        unidades.add(unit);
    }

    @Override
    public Iterator<Unit> iterator() {
        return unidades.iterator();
    }

}