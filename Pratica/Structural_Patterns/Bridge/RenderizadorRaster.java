package Bridge;

public class RenderizadorRaster implements Renderizador {
    @Override
    public void desenharCirculo(int raio) {
        System.out.println("Desenhar círculo em raster com raio " + raio);
    }
}
