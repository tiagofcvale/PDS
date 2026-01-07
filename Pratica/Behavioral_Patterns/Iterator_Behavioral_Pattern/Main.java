package Iterator_Behavioral_Pattern;

public class Main {
    public static void main(String[] args) {
        Playlist p = new Playlist();
        p.adicionar("Música A");
        p.adicionar("Música B");
        p.adicionar("Música C");

        IteratorMusicas it = p.criarIterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}