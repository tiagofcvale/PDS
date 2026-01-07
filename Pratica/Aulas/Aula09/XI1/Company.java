package Aula09.XI1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Company {
    public static User user;

    private final List<Employee> emps = new ArrayList<>();

    public void admitPerson(String name, double salary) {
        emps.add(new Employee(name, salary));
    }

    public void paySalaries(int month) {
        for (Employee e : emps) {
            e.getBankAccount().deposit(e.getSalary());
        }
    }

    public List<Employee> employees() {
        return Collections.unmodifiableList(emps);
    }
}