package Proxy;

public class Main {
    public static void main(String[] args) {
        Imagem img = new ImagemProxy("foto_hd.png");

        // A imagem real ainda NÃO foi carregada
        System.out.println("Pedido para mostrar imagem:");
        img.mostrar();  // Agora carrega e mostra

        System.out.println("Mostrar novamente:");
        img.mostrar();  // Não volta a carregar, usa a imagem já criada
    }
}
