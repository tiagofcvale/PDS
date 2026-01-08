package Teste1;

public class MultipleChoice extends Question {

    public MultipleChoice(String question, double ponints) {
        super(question, ponints);
    }

    @Override
    public String display() {
        return super.getQuestion() + super.getPoints();
    }
    
}
