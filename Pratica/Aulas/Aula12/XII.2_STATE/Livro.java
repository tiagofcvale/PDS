public class Livro {
    private IState state;
    private String title;
    private static int ISBN = 0;
    private String ano;
    private String autor;

    public Livro(String title, String ISBM, String ano, String autor) {
        this.state = new Inventario();
        this.title = title;
        this.ano = ano;
        this.autor = autor;
        ISBN++;
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
