package Visitor_Behavioral_Pattern;

public interface Visitante {
    void visitar(Livro livro);
    void visitar(Revista revista);
}
