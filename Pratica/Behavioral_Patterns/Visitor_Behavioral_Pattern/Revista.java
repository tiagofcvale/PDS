package Visitor_Behavioral_Pattern;

public class Revista implements Item {
    private String title;

    public Revista(String title) {
        this.title = title;
    }

    @Override
    public void aceitar(Visitante visitante) {
        visitante.visitar(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
