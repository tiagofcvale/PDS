public class Cliente implements IObserver{
    private String nome;
    
    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void update(String msg) {
        System.out.println(msg);
    }    
}
