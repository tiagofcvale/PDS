public class Main {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediator();
        Colleague c1 = new Colleague(mediator);
        Colleague c2 = new Colleague(mediator);
        mediator.addColleague(c1);
        mediator.addColleague(c2);
        c1.sendMessage("Olá");
        c2.sendMessage("Adeus");
        c1.sendMessage("Bom Dia!");
        c2.sendMessage("Boa Tarde!");
    }
}