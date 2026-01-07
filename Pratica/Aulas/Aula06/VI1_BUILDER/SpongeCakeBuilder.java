package Aula06.VI1_BUILDER;

public class SpongeCakeBuilder implements CakeBuilder{

    Cake cake;

    @Override
    public void setCakeShape(Shape shape) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCakeShape'");
    }

    @Override
    public void setCakeLayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCakeLayer'");
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
        cake = new Cake();
    }

    @Override
    public Cake getCake() {
        return cake;
    }
    
}
