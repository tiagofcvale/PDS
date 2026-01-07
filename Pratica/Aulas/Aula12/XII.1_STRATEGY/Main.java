import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Phone> phones = new ArrayList<>();
        phones.add(new Phone("Snapdragon 8 Gen 1", 999.99, 8, "108MP"));
        phones.add(new Phone("Apple A15", 1199.99, 6, "12MP"));
        phones.add(new Phone("Exynos 2200", 899.99, 12, "64MP"));
        phones.add(new Phone("Dimensity 9000", 799.99, 8, "50MP"));

        System.out.println("Original:");
        printPhones(phones);

        Diretor diretor = new Diretor(new PrecoSort());
        diretor.exec(phones);
        System.out.println("\nOrdenado por preço:");
        printPhones(phones);

        diretor.setStrategy(new MemoriaSort());
        diretor.exec(phones);
        System.out.println("\nOrdenado por memória:");
        printPhones(phones);

        diretor.setStrategy(new ProcessadorSort());
        diretor.exec(phones);
        System.out.println("\nOrdenado por processador:");
        printPhones(phones);
    }

    private static void printPhones(ArrayList<Phone> phones) {
        for (Phone p : phones) {
            System.out.println(p.getProcessador() + " | " + p.getPreco() + "€ | " + p.getMemoria() + "GB | " + p.getCamera());
        }
    }
}
