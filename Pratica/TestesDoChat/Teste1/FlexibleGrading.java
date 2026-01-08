package Teste1;

public class FlexibleGrading implements Strategy{

    @Override
    public double exec(InnerGroup exam) {
        return exam.getPoints();
    }

}
