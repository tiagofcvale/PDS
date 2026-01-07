package Adapter;

public class AdaptadorUKparaEU implements TomadaEuropeia{
    private TomadaUK dispositivo;

    public AdaptadorUKparaEU(TomadaUK dispositivo) {
        this.dispositivo = dispositivo;
    }

    @Override
    public void ligar() {
        this.dispositivo.powerOn();
    }
}
