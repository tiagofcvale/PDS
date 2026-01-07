import java.util.ArrayList;
import java.util.List;

public class Produto{

    private static int idCounter = 0;
    private final int codigo;
    private String descr;
    private double precoBase;
    private long tempoLeilaoMax;
    private long inicioLeilao;
    private boolean vendido;
    
    List<IObserver> observers;

    //STOCK, LEILAO, VENDAS
    private IState estado;
    
    public Produto(String descr, double precoBase) {
        this.codigo = ++idCounter;
        this.descr = descr;
        this.precoBase = precoBase;
        this.estado = new EmStock();
        this.tempoLeilaoMax = 0;
        this.inicioLeilao = 0;
        this.vendido = false;

        observers = new ArrayList<>();
    }
    public int getCodigo() {
        return codigo;
    }

    public IState getEstado() {
        return estado;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }
    public static int getIdCounter() {
        return idCounter;
    }
    public static void setIdCounter(int idCounter) {
        Produto.idCounter = idCounter;
    }
    public long getTempoLeilaoMax() {
        return tempoLeilaoMax;
    }
    public void setTempoLeilaoMax(long tempoLeilaoMax) {
        this.tempoLeilaoMax = tempoLeilaoMax;
    }
    public long getInicioLeilao() {
        return inicioLeilao;
    }
    public void setInicioLeilao(long inicioLeilao) {
        this.inicioLeilao = inicioLeilao;
    }
    public boolean isVendido() {
        return vendido;
    }
    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }
    public void setEstado(IState estado) {
        notifyObservers("O objeto está agora em "+estado);
        this.estado = estado;
    }    

    public void attatch(IObserver o) {
        observers.add(o);
    }

    public void dettatch(IObserver o) {
        observers.remove(o);
    }

    public void notifyObservers(String msg) {
        for (IObserver observer : observers) {
            observer.update(msg);
        }
    }
}
