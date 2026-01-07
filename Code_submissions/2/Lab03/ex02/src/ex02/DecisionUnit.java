package ex02;

public class DecisionUnit {



    public static void EvaluateChoice(String choice){
        String choiceOptions = choice.substring(0,1).toUpperCase();
        String characterSplit = " ";

        switch(choiceOptions) {
            case "H":
                System.out.println("===== MENU DE OPÇÕES =====");
                System.out.println("H - Mostrar Opções do Menu");
                System.out.println("I - Ler Ficheiro Com Avião");
                System.out.println("M - Mostrar Mapa de Voo");
                System.out.println("F - Adicionar Avião às reservas");
                System.out.println("R - Fazer Reserva");
                System.out.println("C - Cancelar Reserva");
                System.out.println("Q - Sair do Sistema");
                System.out.println("==========================");
                break;
        
            case "I":
                System.out.println("Inicializar Ficheiro");
                String[] Divide = choice.split(characterSplit);
                if (Divide.length < 2) {
                    System.out.println("❌ Por favor, forneça o nome do ficheiro após a opção I.");
                } else {
                    String fileName = Divide[1];
                    FileManager fileManager = new FileManager(fileName);
                    fileManager.OpenFilePlane();
                }
                break;
        
            case "M":
                String[] partsMap = choice.split(characterSplit);
                BookingSystem bookingSystem = BookingSystem.getInstance();
                bookingSystem.printPlaneLayout(partsMap[1]);
                break;
        
            case "F":
                String[] parts = choice.split(characterSplit);
                if(parts.length < 2){
                    System.out.println("❌ Por favor, forneça o código do voo após a opção F.");
                } else {
                    String codFlight = parts[1];
                    
                    if(parts.length == 3){
                        String touristicLayout = parts[2];
                        String[] touristicParts = touristicLayout.split("x");
                        int[] touristicLayoutParts = {Integer.parseInt(touristicParts[0]), Integer.parseInt(touristicParts[1])};
                        Plane plane = new Plane(codFlight, touristicLayoutParts);
                        
                    } else {
                        String executiveLayout = parts[2];
                        String touristicLayout = parts[3];
                        String[] executiveParts = executiveLayout.split("x");
                        String[] touristicParts = touristicLayout.split("x");
                        int[] executiveLayoutParts = {Integer.parseInt(executiveParts[0]), Integer.parseInt(executiveParts[1])};
                        int[] touristicLayoutParts = {Integer.parseInt(touristicParts[0]), Integer.parseInt(touristicParts[1])};
                        Plane plane = new Plane(codFlight, executiveLayoutParts, touristicLayoutParts);   
                    }
                }
                break;
        
            case "R":
                String[] partReserve= choice.split(characterSplit);
                if(partReserve.length < 4){
                    System.out.println("❌ Por favor, forneça o código do voo, número de lugares e tipo de reserva após a opção R.");
                } else {
                    String codFlight = partReserve[1];
                    int numberSeats;
                    try {
                        numberSeats = Integer.parseInt(partReserve[3]);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Número de lugares inválido. Por favor, insira um número inteiro.");
                        break;
                    }
                    char typeReservation = partReserve[2].toUpperCase().charAt(0);
                    Plane plane = BookingSystem.getInstance().getPlaneByCode(codFlight);
                    if (plane == null) {
                        System.out.println("❌ Voo com código " + codFlight + " não encontrado.");
                    } else {
                        BookingSystem.getInstance().MakeReservation(plane, numberSeats, typeReservation);
                    }
                }

                break;
        
            case "C":
                String[] partCancel= choice.split(characterSplit);
                if(partCancel.length < 2){
                    System.out.println("❌ Por favor, forneça o código da reserva após a opção C.");
                } else {
                    String reservationCode = partCancel[1];
                    BookingSystem.getInstance().CancelReservation(reservationCode);
                }
                break;
        
            case "Q":
                System.out.println("Quitting System");
                System.exit(0);
                break;
        
            default:
                System.out.println("❌ Opção inválida. Por favor selecione uma opção válida.");
        }
    }
}