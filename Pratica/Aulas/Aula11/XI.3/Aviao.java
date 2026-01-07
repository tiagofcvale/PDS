
public class Aviao implements IAviao{
    String nome;
    TorreDeControlo torreDeControlo;
    public Aviao(String nome, TorreDeControlo torreDeControlo) {
        this.nome = nome;
        this.torreDeControlo = torreDeControlo;
    }

    @Override
    public void receive(String msg, Aviao sender) {
        System.out.println("MSG received from "+sender+": \""+msg+"\"");
    }

    @Override
    public void sendMSG(String msg) {
        torreDeControlo.notifyAll(msg,this);
    }

    @Override
    public String toString() {
        return nome;
    }


}
