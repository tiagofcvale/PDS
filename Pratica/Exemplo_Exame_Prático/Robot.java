public interface Robot {
    public void move();
    public void kick();

    public String getName();

    public int getKicks();
    public int getMoves();

    public void accept(SavePlays savePlays);

    public void receiveMessage(String message, Robot sender);
}