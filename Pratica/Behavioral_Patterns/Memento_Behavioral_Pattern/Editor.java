package Memento_Behavioral_Pattern;

public class Editor { // Originator (Objeto cujo estado queremos guardar)
    private String texto = "";

    public void escrever(String novoTexto){
        texto = novoTexto;
    }

    public String getTexto() {
        return this.texto;
    }

    public Memento guardarEstado() {
        return new Memento(texto);
    }

    public void restaurar(Memento m) {
        this.texto = m.getTexto();
    }
}
