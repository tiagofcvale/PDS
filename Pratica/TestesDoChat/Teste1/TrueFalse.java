package Teste1;

public class TrueFalse extends Question{

    public TrueFalse(String question, double ponints) {
        super(question, ponints);
    }

    @Override
    public String display() {
        return super.getQuestion() + super.getPoints();
    }
    
}
