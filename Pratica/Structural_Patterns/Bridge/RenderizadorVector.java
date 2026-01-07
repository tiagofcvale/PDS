package Bridge;

public class RenderizadorVector implements Renderizador{
    @Override
    public void desenharCirculo(int raio) {
        System.out.println("Desenhar círculo em vector com raio " + raio);
    }
}
