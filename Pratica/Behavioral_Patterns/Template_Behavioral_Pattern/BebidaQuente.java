package Template_Behavioral_Pattern;

public abstract class BebidaQuente {
    public final void preparar() {
        ferverAgua();
        adicionarIngredientePrincipal();
        deitarNaChavena();
        adicionarExtras();
    }

    private void ferverAgua() {
        System.out.println("A ferver água");
    }

    private void deitarNaChavena() {
        System.out.println("A deitar na chávena");
    }

    protected abstract void adicionarIngredientePrincipal();
    protected void adicionarExtras() {};
}
