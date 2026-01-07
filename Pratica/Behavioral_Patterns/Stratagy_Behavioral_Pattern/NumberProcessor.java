public class NumberProcessor {
    private OperationI operation;

    public NumberProcessor(OperationI operation) {
        this.operation = operation;
    }

    public void setOperation(OperationI op) {
        this.operation = op;
    }

    public OperationI getOperation() {
        return this.operation;
    }

    public int performOperation(int a, int b) {
        return operation.execute(a,b);
    }
}
