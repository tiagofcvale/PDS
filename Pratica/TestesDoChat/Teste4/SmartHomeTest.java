package Teste4;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SmartHomeTest {
    public static void main(String[] args) {
        // 1. Aceder ao Hub Central (deve ser a mesma instância)
        Hub hub = Hub.getInstance();
        Hub hub2 = Hub.getInstance();
        System.out.println("Mesmo Hub? " + (hub == hub2));

        // 2. Criar Dispositivos usando a estrutura de criação
        Device luzSala = hub.createDevice("light", "L01", "Philips Hue");
        Device termostato = hub.createDevice("thermostat", "T01", "Nest");

        // 3. Integrar dispositivo antigo
        LegacySensor sensorAntigo = new LegacySensor("Sensor Movimento Garagem");
        Device adaptadorSensor = new SensorAdapter(sensorAntigo);

        // 4. Configurar a comunicação via Painel de Controlo
        ControlPanel painel = new ControlPanel();
        painel.registerDevice(luzSala);
        painel.registerDevice(termostato);
        painel.registerDevice(adaptadorSensor);

        // 5. Testar Mudança de Estado
        luzSala.setState(new SecurityMode());
        luzSala.executeAction(); // Deve agir de acordo com o modo segurança

        luzSala.setState(new EnergySavingMode());
        luzSala.executeAction(); // Deve agir de acordo com a poupança

        // 6. Executar Ação e Guardar para Undo
        Command ligarLuz = new TurnOnCommand(luzSala);
        hub.executeAndStore(ligarLuz);
        
        System.out.println("Estado antes do Undo: " + luzSala.isOn());
        hub.undoLastAction();
        System.out.println("Estado após o Undo: " + luzSala.isOn());

        // 7. Simular comunicação centralizada
        System.out.println("\n--- Simulação de Notificação ---");
        painel.broadcast(adaptadorSensor, "Movimento detetado!");
    }
}

interface Command{

    void execute();
    void undo();

}

class TurnOnCommand implements Command{
    private Device device;

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.setPowered(true);
    }

    @Override
    public void undo() {
        device.setPowered(false);
    }
    
}

interface DeviceState {

    void execute(Device device);

}

class NormalMode implements DeviceState {
    @Override
    public void execute(Device device) {
        System.out.println("["+device.getName()+"] normal mode");
    }

}

class SecurityMode implements DeviceState {

    @Override
    public void execute(Device device) {
        System.out.println("["+device.getName()+"] security mode");
    }

}

class EnergySavingMode implements DeviceState {

    @Override
    public void execute(Device device) {
        System.out.println("["+device.getName()+"] energy saving mode");
    }
}

class ControlPanel {

    private List<Device> devices = new ArrayList<>();

    public void registerDevice(Device device) {
        devices.add(device);
    }

    public void broadcast(Device device, String msg) {
        for (Device d : devices) {
            if (!d.equals(device)) {
                d.update(device, msg);
            }
        }
    } 
}

class LegacySensor {
    private String name;
    public LegacySensor (String name) {
        this.name = name; 
    }
    public String getName() {
        return name;
    }
}

class SensorAdapter implements Device{
    private LegacySensor sensorAntigo;
    private String name;
    private String id;

    private DeviceState estado;

    private boolean powered;

    public SensorAdapter(LegacySensor sensorAntigo) {
        this.sensorAntigo = sensorAntigo;
        String[] nome = sensorAntigo.getName().trim().split(" ");
        this.id = nome[0];
        this.name = nome[1] + nome[2];
    }
    @Override
    public void update(Device device, String msg) {
        System.out.println("["+device.getName()+"] "+msg);
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void setState(DeviceState estado) {
        this.estado = estado;
    }
    @Override
    public void executeAction() {
        estado.execute(this);
    }
    @Override
    public boolean isOn() {
        return powered;
    }
    @Override
    public void setPowered(boolean powered) {
        this.powered = powered;
    }
    public LegacySensor getSensorAntigo() {
        return sensorAntigo;
    }
    public String getId() {
        return id;
    }
    public DeviceState getEstado() {
        return estado;
    }
    public boolean isPowered() {
        return powered;
    }
}

interface Device {

    public void update(Device device, String msg);

    public boolean isOn();

    public void setPowered(boolean b);

    public void executeAction();

    void setState(DeviceState estado);

    public String getName();

    public String getId();

}

class Light implements Device {
    private String id;
    private String name;

    private DeviceState estado;

    private boolean powered;

    public Light(String id, String name) {
        this.id = id;
        this.name = name;
        this.estado = new NormalMode();
    }
    @Override
    public void update(Device device, String msg) {
        System.out.println("["+device.getName()+"] "+msg);
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void setState(DeviceState estado) {
        this.estado = estado;
    }
    @Override
    public void executeAction() {
        estado.execute(this);
    }
    @Override
    public void setPowered(boolean powered) {
        this.powered = powered;
    }
    @Override
    public boolean isOn() {
        return powered;
    }
    @Override
    public String getId() {
        return id;
    }
    public DeviceState getEstado() {
        return estado;
    }
    public boolean isPowered() {
        return powered;
    }
}

class Thermostat implements Device {
    private String id;
    private String name;

    private DeviceState estado;

    private boolean powered;

    public Thermostat(String id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public void update(Device device, String msg) {
        System.out.println("["+device.getName()+"] "+msg);
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void setState(DeviceState estado) {
        this.estado = estado;
    }
    @Override
    public void executeAction() {
        estado.execute(this);
    }
    @Override
    public void setPowered(boolean powered) {
        this.powered = powered;
    }
    @Override
    public boolean isOn() {
        return powered;
    }
    @Override
    public String getId() {
        return id;
    }
    public DeviceState getEstado() {
        return estado;
    }
    public boolean isPowered() {
        return powered;
    }
}

class Sensor implements Device {
    private String id;
    private String name;

    private DeviceState estado;

    private boolean powered;

    public Sensor(String id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public void update(Device device, String msg) {
        System.out.println("["+device.getName()+"] "+msg);
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void setState(DeviceState estado) {
        this.estado = estado;
    }
    @Override
    public void executeAction() {
        estado.execute(this);
    }
    @Override
    public void setPowered(boolean powered) {
        this.powered = powered;
    }
    @Override
    public boolean isOn() {
        return powered;
    }
    @Override
    public String getId() {
        return id;
    }
    public DeviceState getEstado() {
        return estado;
    }
    public boolean isPowered() {
        return powered;
    }
}

class Hub {
    private static Hub hub;
    private Stack<Command> commands = new Stack<>();

    private Hub() {}

    public void undoLastAction() {
        if(!commands.isEmpty()) {
            Command lastCommand = commands.pop();
            lastCommand.undo(); // Chama o DESFAZER, não o executar!
        }
    }

    public void executeAndStore(Command ligarLuz) {
        ligarLuz.execute();
        commands.add(ligarLuz);
    }

    public static Hub getInstance() {
        if (hub == null) {
            hub = new Hub();
        }
        return hub;
    }

    public Device createDevice(String device, String id, String name) {
        switch (device) {
            case "light":
                return new Light(id,name);        
            case "thermostat":
                return new Thermostat(id,name);
            default:
                return null;
        }
    }
}