package Aula08.VIII1_DECORATOR;

public class EmployeeDecorator implements Employee{
    protected final Employee decorated;

    protected EmployeeDecorator(Employee decorated) {
        this.decorated = decorated;
    }

    @Override
    public void start(String Date) {
        decorated.start(Date);
    }

    @Override
    public void terminate(String Date) {
        decorated.start(Date);
    }

    @Override
    public void work() {
        decorated.work();
    }
}
