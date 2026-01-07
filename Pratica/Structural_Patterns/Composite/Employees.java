package Composite;

public class Employees implements OrganizationalComponent{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employees (String name) {
        this.name = name;
    }

    @Override
    public void showDetails(String prefix) {
        System.out.println(prefix + "👤 Funcionário: " + name);
    }
}
