public class Main {
    public static void main(String[] args) {
        TorreDeControlo torre = new TorreDeControlo();
        Aviao aviao1 = new Aviao("Avião 1", torre);
        Aviao aviao2 = new Aviao("Avião 2", torre);
        Aviao aviao3 = new Aviao("Avião 3", torre);

        torre.addAviao(aviao1);
        torre.addAviao(aviao2);
        torre.addAviao(aviao3);

        aviao1.sendMSG("Estou a descolar!");
        aviao2.sendMSG("Estou a aterrar!");
        aviao3.sendMSG("Preciso de assistência!");
    }
}
