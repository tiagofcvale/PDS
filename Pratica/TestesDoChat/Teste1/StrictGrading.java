package Teste1;

public class StrictGrading implements Strategy{

    @Override
    public double exec(InnerGroup exam) {
        return Math.floor(exam.getPoints());
    }

}
