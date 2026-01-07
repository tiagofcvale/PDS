package Decorator;

public abstract class CoffeeDecorator implements Coffee{
    protected Coffee decoratorCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratorCoffee = decoratedCoffee;
    }

    @Override
    public String getDescription() {
        return decoratorCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratorCoffee.getCost();
    }
}
