package FlyWeight;

public class Arvore { // Objeto leve, estado externo
    private int x;
    private int y;
    private TipoArvore tipo;  // Flyweight

    public Arvore(int x, int y, TipoArvore tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public void desenhar() {
        tipo.desenhar(x, y);
    }
}
