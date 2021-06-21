import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;

public class PartImpl extends UnicastRemoteObject implements PartRepository{

	
	private static final long serialVersionUID = 1L;
	
	private HashSet<PartObj> part = new HashSet<PartObj>();
	//private List<PartObj> pecaCorrente;

	protected PartImpl() throws RemoteException {
		super();
	}

	@Override
	public void bind(String nomeRepository) throws RemoteException, MalformedURLException {
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind(nomeRepository, (Remote) this);
		
	}

	@Override
	public void listp() throws RemoteException {
		System.out.println ("-----------------------------------");
		System.out.println("Lista de Pecas: \n");
		for (PartObj item : part) {			
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
		System.out.println("Mostrando atributos da peça corrente...\n");
		System.out.println("Codigo: " + pecaCorrente.getCodigoPeca().toString());
		System.out.println("Nome: " + pecaCorrente.getNomePeca().toString());
		System.out.println("Descricao: " + pecaCorrente.getDescricaoPeca().toString());
	}

	@Override
	public void clearList(HashMap<PartObj, Integer> subPecas) throws RemoteException {
		System.out.println("Limpando lista de subpecas corrente");
		subPecas.clear();
	}
	
	@Override
	public void addsubpart(HashMap<PartObj, Integer> subPecasCorrente, PartObj pecaCorrente, Integer n) throws RemoteException {
		subPecasCorrente.put(pecaCorrente, n);
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
