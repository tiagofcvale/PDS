package Bridge;

public class Main {
    public static void main(String[] args) {
        Forma c1 = new Circulo(new RenderizadorRaster(), 5);
        c1.desenhar();

        Forma c2 = new Circulo(new RenderizadorVector(), 10);
        c2.desenhar();
    }
}

