import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.addLivro(new Livro("Java Anti-Stress", "1", "2020", "Omodionah"));
        biblioteca.addLivro(new Livro("A Guerra dos Padrões", "2", "2018", "Jorge Omel"));
        biblioteca.addLivro(new Livro("A Procura da Luz", "3", "2015", "Khumatkli"));

        Scanner sc = new Scanner(System.in);
        while (true) {
            biblioteca.mostrarLivros();
            System.out.println(">> <livro>, <operação: (1)regista; (2)requisita; (3)devolve; (4)reserva; (5)cancela");
            System.out.print(">> ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) break;
            String[] parts = input.split(",");
            if (parts.length != 2) continue;
            int idx, op;
            try {
                idx = Integer.parseInt(parts[0].trim()) - 1;
                op = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println("Input inválido");
                continue;
            }
            switch (op) {
                case 1: biblioteca.registar(biblioteca.livros.get(idx)); break;
                case 2: biblioteca.requisitar(biblioteca.livros.get(idx)); break;
                case 3: biblioteca.devolver(biblioteca.livros.get(idx)); break;
                case 4: biblioteca.reservar(biblioteca.livros.get(idx)); break;
                case 5: biblioteca.cancelarReserva(biblioteca.livros.get(idx)); break;
                default: System.out.println("Operação não disponível");
            }
        }
        sc.close();
    }
}
