package Composite;

import java.util.ArrayList;
import java.util.List;

public class Department implements OrganizationalComponent{

    private String name;
    private List<OrganizationalComponent> components = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public void addComponent(OrganizationalComponent component) {
        components.add(component);
    }

    public void removeComponent(OrganizationalComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails(String prefix) {
        System.out.println(prefix + "🏢 Departamento: " + name);
        for (OrganizationalComponent component : components) {
            component.showDetails(prefix + "   ");
        }
    }
    
}
