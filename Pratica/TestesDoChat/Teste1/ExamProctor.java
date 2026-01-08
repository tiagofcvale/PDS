package Teste1;

public class ExamProctor implements ExamObserver{

    private String name;

    public ExamProctor(String name) {
        this.name = name;
    }

    @Override
    public void update(String message, StudentSession student) {
        System.out.println("["+name+"]"+" Alert: Student "+student.getName()+" ("+student.getnMec()+") changed status to: "+message);
    }
    
}
