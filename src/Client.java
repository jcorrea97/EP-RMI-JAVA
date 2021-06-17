import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) {
		try {
			String ip = (args.length < 1) ? null : args[0];
			Registry registry = LocateRegistry.getRegistry(ip);
			String[] serversOn = registry.list();
			System.out.println("Servers rodando: \n");
			for(int i=0; i < serversOn.length; i++) {
				System.out.println("Server "+ serversOn[i] +"\n");
			}
			System.out.print("Escolha o servidor para entrar:\n");
			Scanner scan = new Scanner(System.in);
			String serverEscolhido = scan.next();
			scan.close();
			//PartRepository c = (PartRepository) Naming.lookup("rmi://192.168.15.140:1099/" + host);
			PartRepository c = (PartRepository) registry.lookup(serverEscolhido);
			System.out.println("chupita:" + c.testeHelloWorld());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
