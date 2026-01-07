package Aula09.XI1;

class Person {
    private final String name;
    private final BankAccount bankAccount;

    public Person(String name) {
        this.name = name;
        this.bankAccount = new BankAccountProxy("PeDeMeia", 0);
    }

    public String getName() {
        return name;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
