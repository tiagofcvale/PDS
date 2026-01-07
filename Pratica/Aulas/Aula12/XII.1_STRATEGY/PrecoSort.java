import java.util.ArrayList;

public class PrecoSort implements StrategyI {

    @Override
    public void exec(ArrayList<Phone> phones) {
        phones.sort((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()));
    }
    
}
