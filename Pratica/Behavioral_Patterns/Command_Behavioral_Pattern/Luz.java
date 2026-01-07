package Command_Behavioral_Pattern;

public class Luz { // Receiver (quem realmente faz o trabalho)
    public void ligar() {
        System.out.println("Luz ligada");
    }

    public void desligar() {
        System.out.println("Luz desligada");
    }
}
