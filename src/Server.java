import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

public class Server {

	Server() {
		try {
			System.setProperty("java.rmi.server.hostname", "192.168.0.14");
			LocateRegistry.createRegistry(1099);
			PartRepository c = new PartImpl();
			Naming.rebind("PartRepositoryService", (Remote) c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Server();
	}
}
