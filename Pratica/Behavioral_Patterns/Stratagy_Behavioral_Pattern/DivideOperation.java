public class DivideOperation implements OperationI {
    
    @Override
    public int execute(int num1, int num2) {
        if (num2 == 0) {
            System.out.println("Segundo número tem de ser diferente de 0!");
            return -1;
        }
        return num1 / num2;
        
    }
}
