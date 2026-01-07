package Aula08.VIII_DECORATOR;

public class TeamMember extends EmployeeDecorator{

    protected TeamMember(Employee decorated) {
        super(decorated);
    }
    
    @Override
    public void start(String Date) {
        super.start(Date);
        System.out.println("TeamMember: start date "+Date);
    }

    @Override
    public void terminate(String Date) {
        super.start(Date);
        System.out.println("TeamMember: terminate date "+Date);
    }

    @Override
    public void work() {
        super.work();
        System.out.println("TeamMember Working!");
    }

    public void colaborate() {
        System.out.println("TeamMember is Colaborating!");
    }
}
