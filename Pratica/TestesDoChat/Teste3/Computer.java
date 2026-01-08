package Teste3;

public class Computer {
    private String cpu;
    private String gpu;
    private String ram;
    private String family;
    private double price = 0;

    public void setCPU(String cpu, double cost) { this.cpu = cpu; this.price += cost; }
    public void setGPU(String gpu, double cost) { this.gpu = gpu; this.price += cost; }
    public void setRAM(String ram, double cost) { this.ram = ram; this.price += cost; }
    public void setFamily(String family) { this.family = family; }

    public double getPrice() { return price; }

    public void boot() {
        System.out.println("Booting Computer:");
        System.out.println("- CPU: " + cpu);
        System.out.println("- GPU: " + gpu);
        System.out.println("- RAM: " + ram);
        System.out.println("- Family: " + family);
    }
}