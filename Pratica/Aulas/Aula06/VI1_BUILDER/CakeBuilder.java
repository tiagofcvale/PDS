package Aula06.VI1_BUILDER;

public interface CakeBuilder {
    public void setCakeShape(Shape shape);
    public void setCakeLayer();
    public void setNumCakeLayers();
    public void setMidLayerCream();
    public void setTopLayerCream();
    public void setTopping();
    public void setMessage(String Message);

    public void createCake();
    public Cake getCake();
}
