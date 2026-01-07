package Observer_Behavioral_Pattern;

public interface Subject {
    void attach(Observer o); // Lista de entidades que observam o subject (observers)
    void detach(Observer o);
    void notifyObservers(String videoTitle);
}
