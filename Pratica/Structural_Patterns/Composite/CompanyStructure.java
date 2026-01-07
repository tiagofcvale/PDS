package Composite;

public class CompanyStructure {
    public static void main(String[] args) {
        // Employees
        Employees emp1 = new Employees("Alice");
        Employees emp2 = new Employees("Roberto");
        Employees emp3 = new Employees("Carlos");
        Employees emp4 = new Employees("Diana");

        // Sub-department
        Department designDept = new Department("Investigadores");
        designDept.addComponent(emp2);

        // Main department
        Department itDept = new Department("Professores");
        itDept.addComponent(emp1);
        itDept.addComponent(designDept);

        // Top-level department
        Department headOffice = new Department("Técnicos");
        headOffice.addComponent(itDept);
        headOffice.addComponent(emp3);
        headOffice.addComponent(emp4);

        // Show full structure
        headOffice.showDetails("");
    }
}