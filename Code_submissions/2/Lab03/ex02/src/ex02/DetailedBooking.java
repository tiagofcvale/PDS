package ex02;

import java.util.*;

public class DetailedBooking {

    private Map<Integer, String[]> availableSeats;
    private Map<String, String[]> bookedSeats;

    DetailedBooking() {
        availableSeats = new HashMap<>();
        bookedSeats = new HashMap<>();
    }

    public void initializeAvailableSeats(int[] executiveLayout, int[] touristicLayout) {

        int executiveRows = executiveLayout[0];
        int executiveCols = executiveLayout[1];
        int touristicRows = touristicLayout[0];
        int touristicCols = touristicLayout[1];

        // Inicializar classe executiva
        for (int i = 1; i <= executiveRows; i++) {
            String[] rowSeats = new String[executiveCols];
            for (int j = 0; j < executiveCols; j++) {
                rowSeats[j] = String.valueOf((char) ('A' + j));
            }
            availableSeats.put(i, rowSeats);
        }

        // Inicializar classe turística
        for (int i = executiveRows + 1; i <= executiveRows + touristicRows; i++) {
            String[] rowSeats = new String[touristicCols];
            for (int j = 0; j < touristicCols; j++) {
                rowSeats[j] = String.valueOf((char) ('A' + j));
            }
            availableSeats.put(i, rowSeats);
        }
    }

    // Getters
    public Map<Integer, String[]> getAvailableSeats() {
        return availableSeats;
    }

    public Map<String, String[]> getBookedSeats() {
        return bookedSeats;
    }

    public void MakeReservation(Plane plane, int numSeats, Character typeReservation) {
        List<String> assignedSeats = new ArrayList<>();

        int executiveRows = plane.getExecutiveLayout()[0];
        int touristicRows = plane.getTouristicLayout()[0];

        int startRow, endRow;
        if (typeReservation == 'E') {
            startRow = 1;
            endRow = executiveRows;
        } else {
            startRow = executiveRows + 1;
            endRow = executiveRows + touristicRows;
        }

        // 1º Passo: Verificar capacidade total
        int freeSeats = 0;
        for (int i = startRow; i <= endRow; i++) {
            for (String seat : availableSeats.get(i)) {
                if (seat != null && !seat.equals("X")) freeSeats++;
            }
        }
        if (freeSeats < numSeats) {
            System.out.println("❌ Reserva não efetuada para voo " + plane.getCodFlight()
                    + ". Lugares insuficientes.");
            return;
        }

        // 2º Passo: Procurar fila(s) totalmente vazias
        for (int i = startRow; i <= endRow && assignedSeats.size() < numSeats; i++) {
            String[] row = availableSeats.get(i);
            boolean rowEmpty = Arrays.stream(row).allMatch(seat -> seat != null && !seat.equals("X"));
            if (rowEmpty) {
                for (int j = 0; j < row.length && assignedSeats.size() < numSeats; j++) {
                    assignedSeats.add(i + row[j]);
                    row[j] = "X"; // marca como reservado
                }
            }
        }

        // 3º Passo: Se ainda faltar lugares, distribuir sequencialmente
        if (assignedSeats.size() < numSeats) {
            for (int i = startRow; i <= endRow && assignedSeats.size() < numSeats; i++) {
                String[] row = availableSeats.get(i);
                for (int j = 0; j < row.length && assignedSeats.size() < numSeats; j++) {
                    if (row[j] != null && !row[j].equals("X")) {
                        assignedSeats.add(i + row[j]);
                        row[j] = "X"; // marca como reservado
                    }
                }
            }
        }

        // Guardar reserva com código único
        String reservationId = plane.getCodFlight() + ":" + (bookedSeats.size() + 1);
        bookedSeats.put(reservationId, assignedSeats.toArray(new String[0]));

        System.out.println(plane.getCodFlight() +":"+
             reservationId + "-> " + assignedSeats);
    }

    public void CancelReservation(String reservationCode) {
        if (!bookedSeats.containsKey(reservationCode)) {
            System.out.println("❌ Reserva " + reservationCode + " não existe.");
            return;
        }

        String[] seatsToFree = bookedSeats.get(reservationCode);

        for (String seat : seatsToFree) {
            int row = Integer.parseInt(seat.replaceAll("[^0-9]", ""));
            char colLetter = seat.replaceAll("[0-9]", "").charAt(0);

            String[] rowSeats = availableSeats.get(row);
            int colIndex = colLetter - 'A';
            rowSeats[colIndex] = String.valueOf(colLetter); // liberta lugar
        }

        bookedSeats.remove(reservationCode);
        System.out.println("✅ Reserva " + reservationCode + " cancelada e lugares libertados.");
    }

    public void GetFlightLayout(Plane plane) {
        if (availableSeats.isEmpty()) {
            System.out.println("❌ Flight " + plane.getCodFlight() + " has no seats initialized.");
            return;
        }

        for (int rowNum : availableSeats.keySet()) {
            String[] row = availableSeats.get(rowNum);
            System.out.print("Row " + rowNum + ": ");
            for (int col = 0; col < row.length; col++) {
                String seatCode = rowNum + String.valueOf((char) ('A' + col));
                int reservationNumber = 0;

                // Check each reservation
                for (Map.Entry<String, String[]> entry : bookedSeats.entrySet()) {
                    String resCode = entry.getKey(); // e.g., FL123:1
                    String[] seats = entry.getValue();
                    for (String s : seats) {
                        if (s.equals(seatCode)) {
                            reservationNumber = Integer.parseInt(resCode.split(":")[1]);
                            break;
                        }
                    }
                    if (reservationNumber != 0) break;
                }

                System.out.print(reservationNumber + " ");
            }
            System.out.println();
        }
    }
}