package Teste3;

public class ControlUnit {
    
    private GPU gpu;
    private Fan fan;

    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }

    public void setFan(Fan fan) {
        this.fan = fan;
    }

    public void notify(GPU gpu) {
        fan.receive("cooling");
    }

    public void notify(Fan fan) {
        gpu.receive("processing");
    }
}
