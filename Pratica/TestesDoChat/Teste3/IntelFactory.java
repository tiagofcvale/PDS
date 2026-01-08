package Teste3;

public class IntelFactory implements ComponentFactory {

    @Override
    public String createCPU() {
        return "Intel Core I9";
    }

    @Override
    public double getCPUPrice() {
        return 250;
    }

    @Override
    public String createRAM() {
        return "32GB DDR5";
    }

    @Override
    public double getRAMPrice() {
        return 200;
    }

    @Override
    public String getFamilyName() {
        return "Intel Certified System";
    }

    @Override
    public String createGPU() {
        return "NVIDIA RTX 4090";
    }

    @Override
    public double getGPUPrice() {
        return 500;
    }

    

}
