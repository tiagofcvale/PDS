package Aula06.VI1_BUILDER;

public class ChocolateCakeBuilder implements CakeBuilder {

    Cake cake;

    @Override
    public void setCakeShape(Shape shape) {
        cake.setShape(shape);
    }

    @Override
    public void setCakeLayer() {
        cake.setCakeLayer(null);
    }

    @Override
    public void setNumCakeLayers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNumCakeLayers'");
    }

    @Override
    public void setMidLayerCream() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMidLayerCream'");
    }

    @Override
    public void setTopLayerCream() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTopLayerCream'");
    }

    @Override
    public void setTopping() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTopping'");
    }

    @Override
    public void setMessage(String Message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMessage'");
    }

    @Override
    public void createCake() {
        this.cake = new Cake();
    }

    @Override
    public Cake getCake() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCake'");
    }
    
}
