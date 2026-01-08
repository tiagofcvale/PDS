package Teste2;

public class LogManager implements DocumentObserver{

    @Override
    public void update(String msg) {
        System.out.println("[EVENT] LogManager: Document "+msg+" was accessed.");
    }
    
}
