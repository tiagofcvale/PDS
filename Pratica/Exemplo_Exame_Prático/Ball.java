public class Ball {
    // SingleTon
    private String color;
    private static Ball ball;

    private Ball(String color) {
        this.color = color;
    }

    public static Ball getInstance() {
        if (ball == null) {
            return new Ball("White");
        } else {
            return ball;
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
