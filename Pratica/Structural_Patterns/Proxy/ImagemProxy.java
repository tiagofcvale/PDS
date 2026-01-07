package Proxy;

public class ImagemProxy implements Imagem {
    private String ficheiro;
    private ImagemReal imagemReal; // inicializado apenas quando necessário

    public ImagemProxy(String ficheiro) {
        this.ficheiro = ficheiro;
    }

    public void mostrar() {
        if (imagemReal == null) {
            imagemReal = new ImagemReal(ficheiro);
        }
        imagemReal.mostrar();
    }
}
