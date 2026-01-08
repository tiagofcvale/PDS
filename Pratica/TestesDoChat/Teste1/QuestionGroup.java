package Teste1;

import java.util.ArrayList;
import java.util.List;

public class QuestionGroup implements InnerGroup{

    private List<InnerGroup> grupos;
    private String question;

    public QuestionGroup(String question) {
        this.question = question;
        grupos = new  ArrayList<>();
    }


    public void add(InnerGroup pergunta) {
        grupos.add(pergunta);
    }

    @Override
    public String getQuestion() {
        return question;
    }


    public List<InnerGroup> getGroupos() {
        return grupos;
    }


    public void setPerguntas(List<InnerGroup> grupos) {
        this.grupos = grupos;
    }


    public void setQuestion(String question) {
        this.question = question;
    }

    public double getPoints() {
        double total = 0;
        for (InnerGroup innerGroup : grupos) {
            total+=innerGroup.getPoints();
        }
        return total;
    }


    @Override
    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("Group: ").append(this.question).append(" {\n");
        
        for (InnerGroup innerGroup : grupos) {
            // Chamada recursiva: funciona tanto para Question como para outro QuestionGroup
            // O replace serve para adicionar indentação nos sub-elementos
            sb.append("  ").append(innerGroup.display().replace("\n", "\n  ")).append("\n");
        }
        
        // Remove espaços extra no fim antes de fechar a chaveta para manter o formato
        String result = sb.toString().trim();
        return result + "\n}";
    }
    
}
