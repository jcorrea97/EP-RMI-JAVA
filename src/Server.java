import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

public class Server {

	Server(String host) {
		try {
			System.setProperty("java.rmi.server.hostname", "192.168.15.140");
			LocateRegistry.createRegistry(1099);
			PartRepository c = new PartImpl();
			Naming.rebind(host, (Remote) c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		String host = (args.length < 1) ? null : args[0];
		new Server(host);
	}
}
