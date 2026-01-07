package ex02;

import java.util.Scanner;

public class AirportManager {

    public static void main(String[] args) {

        System.out.println("Airport Manager System Initialized.");
        BookingSystem bookingSystem = BookingSystem.getInstance();
        DecisionUnit decider = new DecisionUnit();




        if(args.length > 0){
            String InstructionsFile = args[0];
            FileManager fileManager = new FileManager(InstructionsFile);
            fileManager.OpenInstructionsFile();
        }
        else{

            Scanner scanner = new Scanner(System.in);
            String choice = "H";
            String choiceOptions = choice.substring(0,1);


            while(!choiceOptions.equals("Q")) {
                decider.EvaluateChoice(choice);

                System.out.print("\nSelecione uma opção:  ");
                choice = scanner.nextLine(); 
                if (choice.isEmpty()) continue; // evita erro se o utilizador só pressiona Enter
                choiceOptions = choice.substring(0, 1).toUpperCase(); // transforma para maiúsculaF 
            }

            scanner.close();

        }
    }
}