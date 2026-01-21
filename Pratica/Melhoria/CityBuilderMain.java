import java.util.ArrayList;
import java.util.List;

public class CityBuilderMain {
    public static void main(String[] args) {

        System.out.println("=== SMART CITY SIMULATOR ===");

        // ===== STRATEGY (política de impostos) =====
        TaxStrategy lowTax = new LowTaxStrategy();
        TaxStrategy highTax = new HighTaxStrategy();

        City city = new City("NeoLisboa");
        city.setTaxStrategy(lowTax);

        System.out.println("\n--- Política de impostos inicial ---");
        city.collectTaxes();

        city.setTaxStrategy(highTax);
        System.out.println("\n--- Nova política de impostos ---");
        city.collectTaxes();

        // ===== OBSERVER (cidadãos reagem a eventos) =====
        Citizen c1 = new Citizen("Ana");
        Citizen c2 = new Citizen("Miguel");

        city.addObserver(c1);
        city.addObserver(c2);

        city.notifyEvent("Construção de um novo hospital");

        // ===== PROXY (acesso controlado ao orçamento) =====
        BudgetService budget = new BudgetServiceProxy(new RealBudgetService());

        budget.requestFunds("Hospital", 500_000);
        budget.requestFunds("Estádio Luxo", 5_000_000);

        // ===== FACADE (gestão simples da cidade) =====
        CityFacade facade = new CityFacade(city, budget);

        System.out.println("\n--- Gestão via Facade ---");
        facade.runDailyCycle();
    }
}

class CityFacade {
    private City city;
    private BudgetService bService;

    public CityFacade(City city, BudgetService bService) {
        this.city = city;
        this.bService = bService;
    }

    public void runDailyCycle() {
        System.out.println("Novo dia na cidade: "+city.getName() +"...");
        System.out.println("Colletando Taxas Altas");
        city.setTaxStrategy(new HighTaxStrategy());
        city.collectTaxes();
        city.addObserver(new Citizen("Pedro"));
        city.addObserver(new Citizen("Carlos"));
        city.addObserver(new Citizen("Orlando"));
        city.notifyEvent("Construção de um novo cinema!");
        bService.requestFunds("Cinema", 600000);
        city.notifyEvent("Construção de Um novo Resort!");
        bService.requestFunds("Resort", 5000000);
        System.out.println("Dia terminado!");
    }
}

interface BudgetService {
    public void requestFunds(String building, int fund);
}

class BudgetServiceProxy implements BudgetService{
    private BudgetService bService;

    public BudgetServiceProxy(BudgetService bService) {
        this.bService = bService;
    }

    @Override
    public void requestFunds(String building, int fund) {
        if (fund <= 1000000) {
            bService.requestFunds(building, fund);
        } else {
            System.out.println("Requst of $"+fund+" to "+building+" declined.");
        }
    }


}

class RealBudgetService implements BudgetService{

    @Override
    public void requestFunds(String building, int fund) {
        System.out.println("Requst of $"+fund+" to "+building+" accepted!.");
    }

}

interface CityObserver {
    public void update(String msg);
}

class Citizen implements CityObserver {
    private String name; 

    public Citizen(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg) {
        System.out.println(name + " recebeu: " + msg);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}

class City {
    private TaxStrategy tStrat;
    private String name;

    private List<CityObserver> citizens;

    public City(String name) {
        this.name = name;
        this.citizens = new ArrayList<>();
    }

    public void notifyEvent(String msg) {
        for (CityObserver citizen : citizens) {
            citizen.update(msg);
        }
    }

    public void addObserver(CityObserver c2) {
        this.citizens.add(c2);
    }

    public void setTaxStrategy(TaxStrategy tStrat) {
        this.tStrat = tStrat;
    }

    public void collectTaxes() {
        tStrat.execute(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

interface TaxStrategy {
    public void execute(City city);
}

class LowTaxStrategy implements TaxStrategy {

    @Override
    public void execute(City city) {
        System.out.println("[LowTax] " + city.getName() + " cobrou impostos baixos (+200k).");
    }

}

class HighTaxStrategy implements TaxStrategy {

    @Override
    public void execute(City city) {
        System.out.println("[HighTax] " + city.getName() + " cobrou impostos altos (+600k).");
    }

}