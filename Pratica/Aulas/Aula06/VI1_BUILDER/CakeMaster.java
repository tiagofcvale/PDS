package Aula06.VI1_BUILDER;

public class CakeMaster {
    private CakeBuilder cBuilder;

    public void setCakeBuilder(CakeBuilder cBuilder) {
        this.cBuilder = cBuilder;
    }

    public void createCake(String Message) {
        this.cBuilder.createCake();
        this.cBuilder.setCakeShape(Shape.CIRCLE);
        this.cBuilder.setCakeLayer();
        this.cBuilder.setNumCakeLayers();
        this.cBuilder.setMidLayerCream();
        this.cBuilder.setTopLayerCream();
        this.cBuilder.setTopping();
        this.cBuilder.setMessage(Message);
    }

    public Cake getCake() {
        return cBuilder.getCake();
    }

    public void createCake(int i, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCake'");
    }

    public void createCake(Shape square, int i, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCake'");
    }

}
