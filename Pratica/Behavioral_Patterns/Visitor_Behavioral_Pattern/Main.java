package Visitor_Behavioral_Pattern;

public class Main {
    public static void main(String[] args) {
        Item livro = new Livro("Java para Iniciantes");
        Item revista = new Revista("Revista de Tecnologia");

        Visitante contador = new ContarPaginas();

        livro.aceitar(contador);  // "Contando as páginas do livro: Java para Iniciantes"
        revista.aceitar(contador);  // "Contando as páginas da revista: Revista de Tecnologia"
    }
}

