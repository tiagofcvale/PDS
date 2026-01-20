public class RacingMain {
    public static void main(String[] args) {

        // ===== BUILDER (construir carro personalizado) =====
        Car car = new CarBuilder()
                .model("RX-Falcon")
                .engine("V8 Turbo")
                .tires("Soft Slick")
                .aerodynamics("Downforce Alto")
                .nitro(true)
                .build();

        // ===== ABSTRACT FACTORY (famílias de pistas por campeonato) =====
        TrackFactory championshipFactory = new F1TrackFactory();
        Track normalTrack = championshipFactory.createStandardTrack();
        Track finalTrack = championshipFactory.createFinalTrack();

        // ===== BRIDGE (carro separado do modo de condução) =====
        DrivingMode aggressive = new AggressiveMode();
        DrivingMode economic = new EconomicMode();

        Vehicle raceCar = new FormulaCar(aggressive);
        Vehicle enduranceCar = new GTCar(economic);

        System.out.println("--- RACING GAME ---");
        System.out.println(car);

        normalTrack.load();
        finalTrack.load();

        raceCar.drive();
        enduranceCar.drive();
    }
}