package Aula08.VIII_DECORATOR;

public class Manager extends EmployeeDecorator{

    public Manager(Employee e) {
        super(e);
    }

    @Override
    public void start(String Date) {
        super.start(Date);
        System.out.println("Manager: start date "+Date);
    }

    @Override
    public void terminate(String Date) {
        super.terminate(Date);
        System.out.println("Manager: terminate date "+Date);
    }

    @Override
    public void work() {
        super.work();
        System.out.println("Manager is Working!");
    }

    public void manage() {
        System.out.println("Manager is Managing!");
    }
    
}
