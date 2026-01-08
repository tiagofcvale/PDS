package Teste3;

public class SpecializedComputerBuilder implements ComputerBuilder {
    private Computer pc;
    private ComponentFactory factory; // Abstract Factory (IntelFactory ou AMDFactory)

    public SpecializedComputerBuilder(ComponentFactory factory) {
        this.factory = factory;
        this.pc = new Computer();
        this.pc.setFamily(factory.getFamilyName());
    }

    @Override
    public void addCPU() {
        // A factory decide se é um i9 ou um Ryzen
        pc.setCPU(factory.createCPU(), factory.getCPUPrice());
    }

    @Override
    public void addGPU() {
        pc.setGPU(factory.createGPU(), factory.getGPUPrice());
    }

    @Override
    public void addRAM() {
        pc.setRAM(factory.createRAM(), factory.getRAMPrice());
    }

    @Override
    public Computer getResult() {
        return this.pc;
    }
}
