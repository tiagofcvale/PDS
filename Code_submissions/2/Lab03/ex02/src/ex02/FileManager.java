package ex02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {


    private String FileName;
    BookingSystem Booking = BookingSystem.getInstance();

    public FileManager(String fileName) {
        this.FileName = fileName;
    }   

    public void OpenFilePlane(){

        int localCount = 0;
        Plane plane = null;

        try (BufferedReader br = new BufferedReader(new FileReader(this.FileName))) {
            String line;
            // Loop through each line
            while ((line = br.readLine()) != null) {
                if (localCount == 0){
                    String firstLine = line.substring(1);
                    String[] parts = firstLine.split(" ");
                    String CodFlight = parts[0];
                    String[] executiveLayout = parts[1].split("x");
                    String[] touristicLayout = parts[2].split("x");
                    int[] executiveLayoutParts = {Integer.parseInt(executiveLayout[0]), Integer.parseInt(executiveLayout[1])};
                    int[] touristicLayoutParts = {Integer.parseInt(touristicLayout[0]), Integer.parseInt(touristicLayout[1])};

                    plane = new Plane(CodFlight, executiveLayoutParts, touristicLayoutParts);



                }else{
                    String[] parts = line.split(" ");
                    char TypeReservation = parts[0].charAt(0);
                    int NumberSeats = Integer.parseInt(parts[1]);
                    Booking.MakeReservation(plane,  NumberSeats, TypeReservation);

                }
                localCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    public void OpenInstructionsFile(){
        try (BufferedReader br = new BufferedReader(new FileReader(this.FileName))) {
            String line;
            // Loop through each line
            while ((line = br.readLine()) != null) {
                DecisionUnit.EvaluateChoice(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    


    
}
