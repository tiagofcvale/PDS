package Teste1;

public abstract class Question implements InnerGroup{

    protected String question;
    private double ponints;

    public Question(String question, double ponints) {
        this.question = question;
        this.ponints = ponints;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public double getPoints() {
        return ponints;
    }
}
