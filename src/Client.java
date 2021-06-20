import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Client {
	
	private Server serverCorrente;
	private PartObj pecaCorrente;
	private HashMap<PartObj, Integer> subPecasCorrente = pecaCorrente.getPecas();
	
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
			//scan.close();
			//PartRepository c = (PartRepository) Naming.lookup("rmi://192.168.15.140:1099/" + host);
			PartRepository c = (PartRepository) registry.lookup(serverEscolhido);
			String comandoEscolhido = "";
			while(true) {
				
			
			System.out.print("Escolha o comando: (addp, listp, quit, clearList) : \n");
			if (scan.hasNext()) {
				comandoEscolhido = scan.next();
			} else {
				System.out.print("vsff");
			}
			
			
			switch(comandoEscolhido){
	        case "addp":
	        	System.out.print("Qual o codigo da peca?");
	    		String codigoEscolhido = scan.next();
	    		int intCodigo = Integer.parseInt(codigoEscolhido);
	    		PartObj part = new PartObj();
	    		part.setCodigoPeca(intCodigo);
	    		System.out.print("Qual a descricao da peca?");
	    		String descricaoEscolhida = scan.next();
	    		part.setDescricaoPeca(descricaoEscolhida);
	    		System.out.print("Qual o nome da peca?");
	    		String nomeEscolhido = scan.next();
	    		part.setNomePeca(nomeEscolhido);
	    		HashMap<PartObj, Integer> map = new HashMap<>(); 
	    		map.put(part, intCodigo);
	    		part.setPecas(map);
	    		c.addp(part);
	            break;

	        case "quit":
	        	System.out.print("Qual servidor vc quer encerrar?");
	    		String servidorEscolhido = scan.next();
	            c.quit(servidorEscolhido);
	            scan.close();
	            break;
	         
	        case "listp":
	        	System.out.print("Listando peças: \n");
	        	c.listp();
	            break;
	            
	        case "clearList":
	        	//c.clearList();
	            break;

	        default:
	        	System.out.print("comando invalido");

	    }
			
			
			//System.out.println("chupita:" + c.testeHelloWorld());
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void criarPeca() {
		
		
	}

}
