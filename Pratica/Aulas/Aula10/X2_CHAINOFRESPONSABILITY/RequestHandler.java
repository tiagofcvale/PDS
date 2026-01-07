package Aula10.X2_CHAINOFRESPONSABILITY;

public class RequestHandler {
    protected RequestHandler next;

    public void setNext(RequestHandler next) {
        this.next = next;
    }

    public void handle(String request) {
        if(next != null) {
            next.handle(request);
        } else {
            System.out.println("We're sorry but that request can't be satisfied by our service!\n");
        }
    }
}
