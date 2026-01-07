package Aula10.X2_CHAINOFRESPONSABILITY;

public class Chef extends RequestHandler{
    String name;
    String order;

    public Chef(String name, String order){
        this.name = name;
        this.order = order;
    }

    @Override
    public void handle(String request) {
        if (request.equalsIgnoreCase(order)) {
            System.out.println(name+": Starting to cook "+order+". Out in 19 minutes!\n");
        } else {
            System.out.println(name+": I can't cook that.");
            super.handle(request);
        }
    }
}
