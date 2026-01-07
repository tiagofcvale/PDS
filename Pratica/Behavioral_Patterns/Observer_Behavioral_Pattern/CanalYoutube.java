package Observer_Behavioral_Pattern;

import java.util.ArrayList;
import java.util.List;

public class CanalYoutube implements Subject{ // Subject concreto
    
    private List<Observer> subs = new ArrayList<>();

    @Override
    public void attach(Observer o) {
        this.subs.add(o);
    }

    @Override
    public void detach(Observer o) {
        this.subs.remove(o);
    }

    @Override
    public void notifyObservers(String videoTitle) {
        for (Observer sub : subs) {
            sub.update(videoTitle);
        }
    }
    
    public void uploadVideo(String title) {
        notifyObservers(title);
    }
}
