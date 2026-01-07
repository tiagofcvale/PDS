import java.util.ArrayList;

public class Diretor {
    private StrategyI strategy;
    
    public Diretor(StrategyI strategy) {
        this.strategy = strategy;
    }

    public StrategyI getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyI strategy) {
        this.strategy = strategy;
    }

    public void exec(ArrayList<Phone> phones){
        this.strategy.exec(phones);
    }
}
