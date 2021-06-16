import java.rmi.Naming;

public class Client {
	
	public static void main(String[] args) {
		try {
			String host = (args.length < 1) ? null : args[0];
			PartRepository c = (PartRepository) Naming.lookup("rmi://192.168.15.140:1099/" + host);
			System.out.println("chupita:" + c.testeHelloWorld());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
