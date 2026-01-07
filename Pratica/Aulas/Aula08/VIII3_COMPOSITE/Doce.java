package Aula08.VIII3_COMPOSITE;

public class Doce implements Produto{
    private String nome;
    private double peso;

    public Doce(String nome, double peso) {
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
        System.out.println("Doce '"+nome+"' - Weight: "+peso);
    }

    
}
