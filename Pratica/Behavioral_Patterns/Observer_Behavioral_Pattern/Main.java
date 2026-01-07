package Observer_Behavioral_Pattern;

public class Main {
    public static void main(String[] args) {
        CanalYoutube canal = new CanalYoutube();

        Observer s1 = new Subscritor("Tiago");
        Observer s2 = new Subscritor("Pedro");

        canal.attach(s2);
        canal.attach(s1);

        canal.uploadVideo("Chão é lava!");

        canal.detach(s2);

        canal.uploadVideo("Niggas");
    }
}
