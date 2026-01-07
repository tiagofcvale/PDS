package Memento_Behavioral_Pattern;

import java.util.Stack;

public class Historico { // Caretaker (guarda momentos, não os altera)
    private Stack<Memento> estados = new Stack<>();

    public void guardar(Memento m){
        estados.push(m);
    }

    public Memento desfazer() {
        if (!estados.isEmpty()) return estados.pop();
        return null;
    }
}
