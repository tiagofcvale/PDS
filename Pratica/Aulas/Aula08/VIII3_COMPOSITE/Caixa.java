package Aula08.VIII3_COMPOSITE;

import java.util.ArrayList;
import java.util.List;

public class Caixa implements Produto{
    private String nome;
    private double peso;

    List<Produto> elementos;

    public Caixa(String nome, double peso) {
        this.nome = nome;
        this.peso = peso;
        elementos = new ArrayList<>();
    }

    @Override
    public String getNome() {
        return nome;
    }

    public double getPesoTotal() {
        double total = 0;
        for (Produto produto : elementos) {
            total += produto.getPeso();
        }
        return total;
    }

    @Override
    public double getPeso() {
        return peso;
    }

    public void draw() {
        System.out.println("Caixa '"+nome+"' [ Weight: "+peso+"; Total: "+getPesoTotal()+"]");
        for (Produto produto : elementos) {
            System.out.print("\t");
            produto.draw();
        }
    }

    public void add(Produto p) {
        elementos.add(p);
    }
}
