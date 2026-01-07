import java.util.ArrayList;

public class MemoriaSort implements StrategyI{

    @Override
    public void exec(ArrayList<Phone> phones) {
        phones.sort((p1, p2) -> Double.compare(p1.getMemoria(), p2.getMemoria()));
    }
    
}
