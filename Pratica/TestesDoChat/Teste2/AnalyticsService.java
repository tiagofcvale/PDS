package Teste2;

public class AnalyticsService implements DocumentObserver{

    @Override
    public void update(String msg) {
        System.out.println("[EVENT] AnalyticsService: Recording metrics for "+msg);
    }

}
