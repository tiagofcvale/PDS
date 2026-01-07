package Observer_Behavioral_Pattern;

public class Subscritor implements Observer{

    private String nome;

    public Subscritor(String nome) {
        this.nome = nome;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println(nome +": Novo vídeo ->"+ videoTitle);
    }
}
