import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

// Não alterar esta classe
// Mas pode (Deve!) comentar linhas com erros para poder testar o código que for desenvolvendo

public class XIII2 {
	private static Port av = new SeaPort();

	public static void main(String[] args) throws FileNotFoundException {
		String suser = System.getProperty("user.dir");
		String[] logFiles = new File(suser).list((d, s) -> {return s.equals("src");}); 
		String sp=""; if(logFiles.length>0) sp = logFiles[0]+"/";
		PrintStream fl = new PrintStream(new File(sp+"PDS2122.txt"));
		fl.println(suser + "\n" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		exam(System.out); // executa e escreve na consola
		exam(fl); // executa e escreve no ficheiro
		fl.close();
	}

	private static void exam(PrintStream out) {
		question1(out);
		question2(out);
		question3(out);
	}

	// alínea a)
	private static void question1(PrintStream out) {
		out.println("\nAlínea a) ----------------------------------\n");

		av.add("C02", new CargoShip("S101", "Quebra Molas", 155.5));
		av.add("C11", new CargoShip("S732", "SoPingas", 20.2));
		av.add("C03", new CargoShip("S923", "Madalena", 18.8));
		av.add("P54", new PassengerShip("S199", "Bananeiros", 120));
		av.add("P35", new PassengerShip("S185", "PDS All aboard", 80));
		av.add("P06", new PassengerShip("S078", "Costeiro", 25));

		Iterator<String> it = av.iterator();
		while (it.hasNext()) {
			out.println(it.next());
		}
	}

	// alínea b)
	private static void question2(PrintStream out) {
		out.println("\nAlínea b) ----------------------------------\n");
		ShipOfSmallShips shipOfSmallShips = new ShipOfSmallShips("s1234", "Container Ship");
		//PassengerShip [code=B899, name=Bora, passengers=4]
		//PassengerShip [code=B878, name=Riacho, passengers=2]
		//PassengerShip [code=B785, name=Turista, passengers=8]
		PassengerShip s1 = new PassengerShip("B899", "Bora", 4);
		PassengerShip s2 = new PassengerShip("B878", "Riacho", 2);
		PassengerShip s3 = new PassengerShip("B785", "Turista", 8);
		shipOfSmallShips.addPassengerShip(s1);
		shipOfSmallShips.addPassengerShip(s2);
		shipOfSmallShips.addPassengerShip(s3);

		av.add("C02",shipOfSmallShips);

		// Ref: X01 - CargoShip [code=S45, name=Beirão, cargo=81.0]
		av.add("X01",new CargoShip("S45", "Beirão", 81.0));


		Iterator<String> it = av.iterator();
		while ( (it.hasNext())) {
			out.println(it.next());
		}
	}

	// alínea c)
	private static void question3(PrintStream out) {
		out.println("\nAlínea c) ----------------------------------\n");
		RiverLogger logger = new RiverLogger();
		RiverPort riverPort = RiverPort.create(logger);

		// Teste: adicionar navios válidos e inválidos
		out.println("Adicionar navios:");
		out.println("add X01 (CargoShip, carga 8): " + riverPort.add("X01", new CargoShip("C8", "MiniCargo", 8)));
		out.println("add X02 (CargoShip, carga 15): " + riverPort.add("X02", new CargoShip("C15", "BigCargo", 15)));
		out.println("add P01 (PassengerShip, 7 passageiros): " + riverPort.add("P01", new PassengerShip("P7", "MiniPass", 7)));
		out.println("add P02 (PassengerShip, 20 passageiros): " + riverPort.add("P02", new PassengerShip("P20", "BigPass", 20)));

		// Verificar existência
		out.println("exists X01: " + riverPort.exists("X01"));
		out.println("exists X02: " + riverPort.exists("X02"));

		// Remover
		out.println("remove X01: " + riverPort.remove("X01"));
		out.println("remove X02: " + riverPort.remove("X02"));

		// Mostrar conteúdo do porto
		out.println("\nNavios no RiverPort:");
		Iterator<String> it = riverPort.iterator();
		while (it.hasNext()) {
			out.println(it.next());
		}

		// Mostrar logs
		out.println("\nLogs do RiverLogger:");
		for (String log : logger.getLogs()) {
			out.println(log);
		}
	}

}
