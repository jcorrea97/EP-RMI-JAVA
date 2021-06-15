import java.rmi.Naming;

public class Client {
	
	public static void main(String[] args) {
		try {
			PartRepository c = (PartRepository) Naming.lookup("rmi://192.168.0.14:1099/PartRepositoryService");
			System.out.println("chupita:" + c.testeHelloWorld());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
