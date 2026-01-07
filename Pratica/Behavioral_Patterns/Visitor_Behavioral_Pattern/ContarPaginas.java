package Visitor_Behavioral_Pattern;

public class ContarPaginas implements Visitante{

    @Override
    public void visitar(Livro livro) {
        System.out.println("Contando as páginas do livro: "+ livro.getTitle());
    }

    @Override
    public void visitar(Revista revista) {
        System.out.println("Contando as páginas da revista: "+revista.getTitle());
    }
    
}
