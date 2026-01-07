package Template_Behavioral_Pattern;

public class Cafe extends BebidaQuente {

    @Override
    protected void adicionarIngredientePrincipal() {
        System.out.println("A adicionar pó de café");
    }
    
    @Override
    protected void adicionarExtras() {
        System.out.println("A adicionar açucar");
    }
}
