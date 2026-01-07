public class Phone {
    private String processador;
    private double preco;
    private double memoria;
    private String camera;

    public Phone(String processador, double preco, double memoria, String camera) {
        this.processador = processador;
        this.preco = preco;
        this.memoria = memoria; 
        this.camera = camera; 
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getMemoria() {
        return memoria;
    }

    public void setMemoria(double memoria) {
        this.memoria = memoria;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }
}
