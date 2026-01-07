package FlyWeight;

public class Main {
    public static void main(String[] args) {
        TipoArvore carvalho = ArvoreFactory.getTipo("Carvalho", "Verde", "Textura1");
        TipoArvore pinheiro = ArvoreFactory.getTipo("Pinheiro", "VerdeEscuro", "Textura2");

        Arvore a1 = new Arvore(10, 20, carvalho);
        Arvore a2 = new Arvore(15, 25, carvalho);
        Arvore a3 = new Arvore(50, 60, pinheiro);

        a1.desenhar();
        a2.desenhar();
        a3.desenhar();
    }
}
