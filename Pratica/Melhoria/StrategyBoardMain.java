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