package Template_Behavioral_Pattern;

public class Main {
    public static void main(String[] args) {
        BebidaQuente cha = new Cha();
        BebidaQuente cafe = new Cafe();

        cha.preparar();
        System.out.println();
        cafe.preparar();
    }
}
