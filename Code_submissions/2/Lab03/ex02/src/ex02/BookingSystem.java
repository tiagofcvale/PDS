package ex02;

import java.util.HashMap;
import java.util.Map;

public class BookingSystem {

    private static final BookingSystem instance = new BookingSystem();
    private final Map<Plane, DetailedBooking> currentBookingBlocks = new HashMap<>();
    private BookingSystem() {}


    // ✅ getter for the single instance
    public static BookingSystem getInstance() {
        return instance;
    }

    private void insertAirplaneIntoBooking(Plane plane) {
        if (currentBookingBlocks.containsKey(plane)) {
            System.out.println("❌ Flight with code " + plane.getCodFlight() + " already exists in the booking system.");
        } else {
            DetailedBooking detailedReservation = new DetailedBooking();
            detailedReservation.initializeAvailableSeats(plane.getExecutiveLayout(), plane.getTouristicLayout());

            currentBookingBlocks.put(plane, detailedReservation);
            System.out.println("✅ Flight " + plane.getCodFlight() + " inserted into the booking system.");
        }
    }

    public void openEndedBooking(Plane plane) {
        this.insertAirplaneIntoBooking(plane);
    }

    public static void printPlaneLayout(String codFlight) {
        BookingSystem bookingSystem = BookingSystem.getInstance();
    
        // Procurar o plane com esse código dentro do currentBookingBlocks
        Plane selectedPlane = null;
        DetailedBooking booking = null;
    
        for (Map.Entry<Plane, DetailedBooking> entry : bookingSystem.getCurrentBookingBlocks().entrySet()) {
            if (entry.getKey().getCodFlight().equals(codFlight)) {
                selectedPlane = entry.getKey();
                booking = entry.getValue();
                break;
            }
        }
    
        if (selectedPlane == null || booking == null) {
            System.out.println("❌ Não existe nenhum voo com o código " + codFlight);
            return;
        }
    
        // Dimensões do avião
        int totalRows = selectedPlane.getTotalRows();
        int totalCols = selectedPlane.getTotalCols();
        int executiveRows = selectedPlane.getExecutiveLayout()[0]; // nº de filas executivas
        int executiveCols = selectedPlane.getExecutiveLayout()[1]; // nº de colunas executivas
        int touristicCols = selectedPlane.getTouristicLayout()[1]; // nº de colunas turísticas
    
        // Criar matriz inicializada com 0
        String[][] layout = new String[totalRows][totalCols];
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                layout[i][j] = "0";
            }
        }
    
        // bookedSeats: "TP123:3" -> ["1A", "1B", "1C"]
        for (Map.Entry<String, String[]> entry : booking.getBookedSeats().entrySet()) {
            String bookingKey = entry.getKey();  // exemplo "TP123:3"
            String[] seats = entry.getValue();
    
            // Número da reserva depois do ':'
            String[] parts = bookingKey.split(":");
            String bookingNumber = parts[1];
    
            for (String seat : seats) {
                int row = Integer.parseInt(seat.substring(0, seat.length() - 1)) - 1;
                char seatLetter = seat.charAt(seat.length() - 1);
                int col = seatLetter - 'A';
    
                if (row < totalRows && col < totalCols) {
                    layout[row][col] = bookingNumber;
                }
            }
        }
    
        // Imprimir
        System.out.println("📍 Layout do voo " + codFlight);
        for (int i = 0; i < totalRows; i++) {
            System.out.printf("Row %2d:", (i + 1));
    
            for (int j = 0; j < totalCols; j++) {
                boolean isExecutive = i < executiveRows;
    
                // Se for executivo e a coluna passar o limite -> "X"
                if (isExecutive && j >= executiveCols) {
                    System.out.printf("  X ");
                }
                // Se for turístico e a coluna passar o limite -> "X"
                else if (!isExecutive && j >= touristicCols) {
                    System.out.printf("  X ");
                }
                else {
                    System.out.printf(" %2s ", layout[i][j]);
                }
            }
            System.out.println();
        }
    }

    public void MakeReservation(Plane plane, int numSeats, Character ReservationClass) {
        DetailedBooking detailedBooking = currentBookingBlocks.get(plane);
        detailedBooking.MakeReservation(plane, numSeats, ReservationClass);
    }

    public void CancelReservation(String reservationCode){

        for (Map.Entry<Plane, DetailedBooking> entry : currentBookingBlocks.entrySet()) {
            DetailedBooking detailedBooking = entry.getValue();
            if (detailedBooking.getBookedSeats().containsKey(reservationCode)) {
                detailedBooking.CancelReservation(reservationCode);
                return;
            }
        }
        System.out.println("❌ Reservation with code " + reservationCode + " not found.");
    }

    private DetailedBooking getDetailedBookingPlane(Plane plane){
        return currentBookingBlocks.get(plane);
    }
    public Map<Plane, DetailedBooking> getCurrentBookingBlocks() {
        return currentBookingBlocks;
    }

    public Plane getPlaneByCode(String codFlight) {
        for (Plane plane : currentBookingBlocks.keySet()) {
            if (plane.getCodFlight().equals(codFlight)) {
                return plane;
            }
        }
        return null; // Plane not found
    }
}