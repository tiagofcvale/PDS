package ex02;

public class Plane {

    private String codFlight;
    private int[] executiveLayout;
    private int[] touristicLayout;
    private int executiveSeats;
    private int touristicSeats;

    // Constructors
    Plane(String codFlight, int[] executiveLayout, int[] touristicLayout) {
        this.codFlight = codFlight;
        this.executiveLayout = executiveLayout;
        this.touristicLayout = touristicLayout;
        setNumberSeats(executiveLayout, touristicLayout);

        BookingSystem.getInstance().openEndedBooking(this);
    }

    Plane(String codFlight, int[] touristicLayout) {
        this.codFlight = codFlight;
        this.touristicLayout = touristicLayout;
        this.executiveLayout = new int[]{0,0};
        setNumberSeats(this.executiveLayout, touristicLayout);

        BookingSystem.getInstance().openEndedBooking(this);
    }

    private void setNumberSeats(int[] executiveLayout, int[] touristicLayout) {
        this.executiveSeats = executiveLayout[0] * executiveLayout[1];
        this.touristicSeats = touristicLayout[0] * touristicLayout[1];
    }


    
    // Getters
    public String getCodFlight() { return codFlight; }
    public int getExecutiveSeats() { return executiveSeats; }
    public int getTouristicSeats() { return touristicSeats; }
    public int[] getExecutiveLayout() { return executiveLayout; }
    public int[] getTouristicLayout() { return touristicLayout; }
    public int getTotalRows() { return executiveLayout[0] + touristicLayout[0]; }
    public int getTotalCols() { return Math.max(executiveLayout[1], touristicLayout[1]); }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return codFlight.equals(plane.codFlight);
    }

    @Override
    public int hashCode() {
        return codFlight.hashCode();
    }
}