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

interface DrivingMode {
    public String getDetails();
}

class AggressiveMode implements DrivingMode{

    @Override
    public String getDetails() {
        return "Agressive";
    }

}

class EconomicMode implements DrivingMode{
    @Override
    public String getDetails() {
        return "Economic";
    }
}

interface Vehicle{
    public void drive();
}

class FormulaCar implements Vehicle {
    private DrivingMode dMode;

    public FormulaCar(DrivingMode dMode) {
        this.dMode = dMode;
    }

    @Override
    public void drive() {
        System.out.println("[FormulaCar] Modo: "+dMode.getDetails());
        System.out.println("A acelerar cedo nas curvas, travagem tardia!");
    }
}

class GTCar implements Vehicle {
    private DrivingMode dMode;

    public GTCar(DrivingMode dMode) {
        this.dMode = dMode;
    }

    @Override
    public void drive() {
        System.out.println("[GTCar] Modo: "+dMode.getDetails());
        System.out.println("A gerir pneus e combustível, condução suave.");
    }
}

interface Track{
    public void load();
}

class StandardTrack implements Track {
    private String name;

    public StandardTrack(String name) {
        this.name = name; 
    }

    @Override
    public void load() {
        System.out.println("Track: "+name+" carregada.");
    }    
}

class FinalTrack implements Track{
    private String name;

    public FinalTrack(String name) {
        this.name = name; 
    }

    @Override
    public void load() {
        System.out.println("Track: "+name+" carregada.");
    }    
}

interface TrackFactory {
    public Track createStandardTrack();
    public Track createFinalTrack();
}

class F1TrackFactory implements TrackFactory{

    @Override
    public Track createStandardTrack() {
        System.out.println("[F1TrackFactory] A preparar pista standard...");
        return new StandardTrack("Silverstone");

    }

    @Override
    public Track createFinalTrack() {
        System.out.println("[F1TrackFactory] A preparar pista final...");
        return new FinalTrack("Monaco");
    }
}

class Car {
    String model;
    String engine;
    String tires;
    String aerodynamics;
    boolean nitro;

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", engine='" + engine + '\'' +
                ", tires='" + tires + '\'' +
                ", aerodynamics='" + aerodynamics + '\'' +
                ", nitro=" + nitro +
                '}';
    }
}

class CarBuilder {
    private Car car = new Car();

    public CarBuilder model(String model) {
        car.model = model;
        return this;
    }

    public CarBuilder engine(String engine) {
        car.engine = engine;
        return this;
    }

    public CarBuilder tires(String tires) {
        car.tires = tires;
        return this;
    }

    public CarBuilder aerodynamics(String aerodynamics) {
        car.aerodynamics = aerodynamics;
        return this;
    }

    public CarBuilder nitro(boolean nitro) {
        car.nitro = nitro;
        return this;
    }

    public Car build() {
        return car;
    }
}