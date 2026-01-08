package Teste3;

public class AMDFactory implements ComponentFactory{

    @Override
    public String createCPU() {
        return "AMD Ryzen5";
    }

    @Override
    public double getCPUPrice() {
        return 150;
    }

    @Override
    public String createRAM() {
        return "8GB DDR4";
    }

    @Override
    public double getRAMPrice() {
        return 100;
    }

    @Override
    public String getFamilyName() {
        return "AMD Certified System";
    }

    @Override
    public String createGPU() {
        return "Integrated Graphics";
    }

    @Override
    public double getGPUPrice() {
        return 0;
    }

}
