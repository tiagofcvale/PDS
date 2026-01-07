package Aula08.VIII_DECORATOR;

public class TodosFazem {
    public Employee hire(String name) {
        System.out.println("TF Contratado: "+name);
        return new BaseEmployee(name);
    }

    public Employee assignTeamMember(Employee e) {
        return new TeamMember(e);
    }

    public Employee assignTeamLeader(Employee e) {
        return new TeamLeader(e);
    }

    public Employee assignManager(Employee e) {
        return new Manager(e);
    }
}
