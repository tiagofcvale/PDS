package Aula09.XI1;

public class BankAccountProxy implements BankAccount {
    private BankAccountImpl bAccImpl;
    
    public BankAccountProxy(String name, int initialDeposit) {
        this.bAccImpl = new BankAccountImpl(name,initialDeposit);
    }

    @Override
    public void deposit(double amount) {
        if (Company.user != User.OWNER) {
            throw new UnsupportedOperationException("user not access");
        }
        bAccImpl.deposit(amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if (Company.user != User.OWNER) {
            throw new UnsupportedOperationException("user not access");
        }
        return bAccImpl.withdraw(amount);
    }

    @Override
    public double balance() {
        if (Company.user != User.OWNER) {
            throw new UnsupportedOperationException("user not access");
        }
        return bAccImpl.balance();
    }


}
