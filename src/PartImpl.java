import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class PartImpl extends UnicastRemoteObject implements PartRepository{

	
	private static final long serialVersionUID = 1L;
	
	private HashSet<PartObj> part = new HashSet<PartObj>();
	//private List<PartObj> pecaCorrente;


	private void preencheAleatorio() throws RemoteException {

		Random gerador = new Random();

		String[] nPeca = {
			"Motor", 
			"Tampa de valvula", 
			"Pneu", 
			"Roda de liga leve", 
			"cinto sport", 
			"kit sport",
			"Vidro blindado",
			"Upgrade de motor",
			"Bancos de couro"
		};

		String[] dPeca = {
			"400 cavalos de pontencia", 
			"Tampa feita em plastico pouco duravel", 
			"Borracha de qualidade testada na nasa",
			"Roda de aro 20 com material premium",
			"Cinto de segurança vermelho",
			"Farol de led e adesivagem esportiva",
			"Vidro resistente a tiros de armas pequenas",
			"Reconfiguração na central de software do motor para tornar mais esportivo",
			"Bancos com revestimento em couro premium"
			};

	
		ArrayList<Integer> arrlist = new ArrayList<Integer>(3);
	
		for (int i = 0; i < 3; i++) {
	
			int codPeca = gerador.nextInt(999);
			int indiceP = gerador.nextInt(4)%3;

			if(arrlist.contains(indiceP)){
				i--;
				continue;
			}

			arrlist.add(indiceP);


			PartObj newPart = new PartObj();

			newPart.setCodigoPeca(codPeca);
			newPart.setDescricaoPeca(dPeca[indiceP]);
			newPart.setNomePeca(nPeca[indiceP]);
			newPart.setPecas(null);	

			part.add(newPart);


   
        }
	}

	protected PartImpl() throws RemoteException {
		super();
		preencheAleatorio();
	}

	@Override
	public void bind(String nomeRepository) throws RemoteException, MalformedURLException {
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind(nomeRepository, (Remote) this);
		
	}


	// aqui mostra so o nome da peca, n mostra os detalhes
	@Override
	public void listp() throws RemoteException {
		System.out.println ("-----------------------------------");
		System.out.println("Lista de Pecas: \n");
		for (PartObj item : part) {		
			System.out.print("Codigo da peca: " + item.getCodigoPeca() + " | "); // linha aqui	
		    System.out.println ("Nome da peca: " + item.getNomePeca());
		    System.out.println ("-----------------------------------");
		}
	}

	@Override
	public PartObj getp(Integer codigoPeca) throws RemoteException {
		for (PartObj item : part) {
	        if (item.getCodigoPeca().equals(codigoPeca)) {
	        	System.out.println("Peca " + item.getNomePeca() + " encontrada! Agora ela e sua peca corrente!");
	        	return item;
	        }
	    }
		System.out.println("Nao ha pecas com esse codigo neste servidor");
		return null;
		
	}

	@Override
	public void showp(PartObj pecaCorrente) throws RemoteException {
		System.out.println("Mostrando atributos da peca corrente...\n");
		System.out.println("Codigo: " + pecaCorrente.getCodigoPeca().toString());
		System.out.println("Nome: " + pecaCorrente.getNomePeca().toString());
		System.out.println("Descricao: " + pecaCorrente.getDescricaoPeca().toString());

		System.out.println("Subpecas: ");
		if(pecaCorrente.pecas!=null)
			pecaCorrente.pecas.forEach((key, value) -> System.out.println(key.getNomePeca() + " " + value));
		
			
	}









	//Daqui para baixo - em tese ok
	@Override
	public void clearList(HashMap<PartObj, Integer> subPecas) throws RemoteException {
		System.out.println("Limpando lista de subpecas corrente");

		if(subPecas != null)
			subPecas.clear(); 
	}
	



	/*

		TODO ERRADO

	*/
	@Override
	public void addsubpart(PartObj subPecasCorrente, PartObj pecaCorrente, Integer n) throws RemoteException {
		
		


		pecaCorrente.addSubPecas(subPecasCorrente, n);
		pecaCorrente.pecas.forEach((key, value) -> System.out.println(key.getNomePeca() + " arroz " + value));

	}











	@Override
	public void addp(PartObj part) throws RemoteException {
		System.out.println("---------------NOVA PECA---------------");
		this.part.add(part);
		for (PartObj newPart : this.part) {
	        if (newPart.equals(part)) 
	          newPart.setPecas(part.getPecas());
	      }
		System.out.println("Codigo: " + part.getCodigoPeca() + "\nNome: " + part.getNomePeca());
	}

	@Override
	public void quit(String servidorCorrente) throws RemoteException {
		 try{
		        // Unregister ourself
				 Registry registry = LocateRegistry.getRegistry();
				 registry.unbind(servidorCorrente);
	
		        // Unexport; this will also remove us from the RMI runtime
		        UnicastRemoteObject.unexportObject(this, true);
	
		        System.out.println("exiting.");
	        }
		    catch(Exception e){}
		
	}
}
