package Aula05.V2;

public class PortionFactory {
    public static Portion create(String name, Temperature temp) {
        State estado;
        if (name.equals("Milk") || name.equals("FruitJuice") || name.equals("Beverage")) estado = State.LIQUID;
        else if (name.equals("Tuna") || name.equals("Pork") || name.equals("Meat")) estado = State.SOLID;
        else estado = null;
        switch (estado) {
            case State.LIQUID:
                if (temp == Temperature.COLD) return new PlasticBottle(name, temp);
                if (temp == Temperature.WARM || temp == Temperature.COLD) return new TermicBottle(name, temp);
            case State.SOLID:
                if (temp == Temperature.COLD) return new PlasticBag(name, temp);
                if (temp == Temperature.WARM || temp == Temperature.COLD) return new Tupperware(name, temp);
            default:
                break;
        }
        return null;
    }
}
