package Decorator;

public class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Simple Coffe";
    }

    @Override
    public double getCost() {
        return 0.5;
    }
    
}
