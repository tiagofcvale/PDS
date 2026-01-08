package Teste1;
public class GradingSystem {
    Strategy strat;

    public void setStrategy(Strategy strat) {
        this.strat = strat;
    }

    public double evaluate(InnerGroup exam) {
        return strat.exec();
    }  
}
