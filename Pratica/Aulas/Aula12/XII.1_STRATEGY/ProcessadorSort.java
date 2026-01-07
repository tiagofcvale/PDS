import java.util.ArrayList;

public class ProcessadorSort implements StrategyI{

    @Override
    public void exec(ArrayList<Phone> phones) {
        phones.sort((p1, p2) -> p1.getProcessador().compareTo(p2.getProcessador()));
    }
    
}
