package Memento_Behavioral_Pattern;

public class Memento { // Memento : Objeto que guarda o estado
    private final String texto;

    public Memento(String texto){
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
