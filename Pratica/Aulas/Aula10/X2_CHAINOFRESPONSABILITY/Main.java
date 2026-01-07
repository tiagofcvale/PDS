package Aula10.X2_CHAINOFRESPONSABILITY;

public class Main {
    public static void main(String[] args) {
        Chef sushiChef = new Chef("SushiChef","sushi nigiri and sashimi");
        Chef pastaChef = new Chef("PastaChef","Pasta Carbonara");
        Chef pizzaChef = new Chef("PizzaChef","PLAIN pizza, no toppings!");
        Chef burguerChef = new Chef("BurguerChef","veggie burguer");
        Chef dessertChef = new Chef("DessertChef","strawberry ice cream and waffles dessert");

        sushiChef.setNext(pastaChef);
        pastaChef.setNext(pizzaChef);
        pizzaChef.setNext(burguerChef);
        burguerChef.setNext(dessertChef);

        System.out.println("Can I please get a veggie burguer?");
        sushiChef.handle("veggie burguer");
    
        System.out.println("Can I please get a Pasta Carbonara?");
        sushiChef.handle("Pasta Carbonara");

        System.out.println("Can I please get a PLAIN pizza, no toppings!?");
        sushiChef.handle("PLAIN pizza, no toppings!");

        System.out.println("Can I please get a sushi nigiri and sashimi?");
        sushiChef.handle("sushi nigiri and sashimi");

        System.out.println("Can I please get a salad with tuna?");
        sushiChef.handle("salad with tuna");

        System.out.println("Can I please get a strawberry ice cream and waffles dessert?");
        sushiChef.handle("strawberry ice cream and waffles dessert");
    }
}
