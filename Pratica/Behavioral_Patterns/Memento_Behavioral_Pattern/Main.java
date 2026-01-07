package Memento_Behavioral_Pattern;

public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor();
        Historico historico = new Historico();

        editor.escrever("Primeira versão");
        historico.guardar(editor.guardarEstado());

        editor.escrever("Segunda versão");
        historico.guardar(editor.guardarEstado());

        editor.escrever("Versão final");
        System.out.println("Atual: " + editor.getTexto());

        // Undo
        editor.restaurar(historico.desfazer());
        System.out.println("Undo 1: " + editor.getTexto());

        editor.restaurar(historico.desfazer());
        System.out.println("Undo 2: " + editor.getTexto());
    }
}
