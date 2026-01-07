package Proxy;

public class ImagemReal implements Imagem{

    private String ficheiro;

    public ImagemReal(String ficheiro) {
        this.ficheiro = ficheiro;
        carregarDoDisco();
    }

    private void carregarDoDisco() {
        System.out.println("A carregar do disco: "+ficheiro);
    }

    @Override
    public void mostrar() {
        System.out.println("A mostrar imagem: " + ficheiro);
    }

}
