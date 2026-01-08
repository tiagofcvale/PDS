package Aula05.V2;

public class Container {
    private final Portion portion;

    private Container(Portion portion) {
        this.portion = portion;
    }

    public static Container create(Portion portion) {
        return new Container(portion);
    }

    @Override
    public String toString() {
        String type = portion.getClass().getSimpleName();
        return type + " with portion = " + portion;
    }
}