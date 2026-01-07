package Aula08.VIII_DECORATOR;

public class BaseEmployee implements Employee{
    private String name;

    public BaseEmployee(String name) {
        this.name = name;
    }

    @Override
    public void start(String Date) {
        System.out.println("Employee: start date "+Date);
    }

    @Override
    public void terminate(String Date) {
        System.out.println("Employee: terminate date "+Date);
    }

    @Override
    public void work() {
        System.out.println("Employee Working!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
