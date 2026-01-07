package Aula08.VIII1_DECORATOR;

public class TeamLeader extends EmployeeDecorator{

    protected TeamLeader(Employee decorated) {
        super(decorated);
    }
    
    @Override
    public void start(String Date) {
        super.start(Date);
        System.out.println("TeamLeader: start date "+Date);
    }

    @Override
    public void terminate(String Date) {
        super.terminate(Date);
        System.out.println("TeamLeader: terminate date "+ Date);
    }

    @Override
    public void work() {
        super.work();
        System.out.println("TeamLeader is Working!");
    }

    public void plan() {
       System.out.println("TeamLeader is Planning!");
    }
}
