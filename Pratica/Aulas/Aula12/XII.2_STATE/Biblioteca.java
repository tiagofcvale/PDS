import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    List<Livro> livros = new ArrayList<>();

    public void addLivro(Livro l) {
        livros.add(l);
    }

    public void removeLivro(Livro l) {
        livros.remove(l);
    }

    public void registar(Livro l) {
        for (Livro livro : livros) {
            if (livro.equals(l))
            livro.getState().registar(l);
        }
    }

    public void requisitar(Livro l) {
        for (Livro livro : livros) {
            if (livro.equals(l))
                livro.getState().requisitar(l);
        }
    }

    public void reservar(Livro l) {
        for (Livro livro : livros) {
            if (livro.equals(l))
                livro.getState().reservar(l);
        }
    }

    public void devolver(Livro l) {
        for (Livro livro : livros) {
            if (livro.equals(l))
                livro.getState().devolver(l);
        }
    }

    public void cancelarReserva(Livro l) {
        for (Livro livro : livros) {
            if (livro.equals(l))
                livro.getState().cancelarReserva(l);
        }
    }

    public void mostrarLivros() {
        System.out.println("*** Biblioteca ***");
        int i = 1;
        for (Livro l : livros) {
            String estado = l.getState().getClass().getSimpleName();
            System.out.println(i + " " + l.getTitle() + " " + l.getAutor() + " [" + estado.replace("Inventario", "Inventário").replace("Disponivel", "Disponível") + "]");
            i++;
        }
    }
}
