public class Colleague {
    
    private ChatMediator mediator;

    public Colleague(ChatMediator mediator) {
        this.mediator = mediator;
    }

    public void sendMessage(String message) {
        mediator.notify(this, message);
    }
    public void receive(String message) {
        System.out.println("Mensagem: "+ message);
    }
}
