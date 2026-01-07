import java.util.ArrayList;
import java.util.List;

public class TorreDeControlo {
    List<Aviao> avioes = new ArrayList<>();

    public void addAviao(Aviao a) {
        this.avioes.add(a);
    }

    public void removeAviao(Aviao a){
        avioes.remove(a);
    }

    public void notifyAll(String msg, Aviao sender) {
        for (Aviao aviao : avioes) {
            if(aviao!=sender) {
                aviao.receive(msg, sender);
            }
        }
    }
}
