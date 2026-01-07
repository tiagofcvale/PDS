package Bridge;

public class Circulo extends Forma{
    private int raio;

    public Circulo(Renderizador renderizador, int raio){
        super(renderizador);
        this.raio = raio;
    }

    public void desenhar() {
        renderizador.desenharCirculo(raio);
    }
}
