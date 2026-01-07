package Aula09.XI1;

class Employee extends Person {
    private final double salary;

    public Employee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}