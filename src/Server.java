import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Server {

	Server() {
		try {
			//System.setProperty("java.rmi.server.hostname", "192.168.15.140");
			Registry registry = LocateRegistry.getRegistry();
			//LocateRegistry.createRegistry(1099);
			PartRepository c = new PartImpl();
			System.out.print("Escolha seu host: ");
			Scanner scan = new Scanner(System.in);
			String host = scan.next();
			scan.close();
			registry.bind(host, c);
			System.out.println("Servidor " + host + " iniciado");
			//Naming.rebind(host, (Remote) c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Server();
	}
}
