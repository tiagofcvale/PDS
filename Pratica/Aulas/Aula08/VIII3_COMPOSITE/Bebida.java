package Aula08.VIII3_COMPOSITE;

public class Bebida implements Produto{
    private String nome;
    private double peso;

    public Bebida(String nome, double peso) {
        this.nome = nome;
        this.peso = peso;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPeso() {
        return peso;
    }

    @Override
    public void draw() {
        System.out.println("Bebida '"+nome+"' - Weight: "+peso);
    }
    
    
}
