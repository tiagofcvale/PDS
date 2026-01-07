public class OldAttacker {
    private String name;

    public OldAttacker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void moveOld() {
        System.out.println(name+ " is old Moving!");
    }

    public void kickOld(){
        System.out.println(name+ " is old Kicking!");
    }
}
