import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.List;

public class PartImpl extends UnicastRemoteObject implements PartRepository{

	
	private static final long serialVersionUID = 1L;
	
	private HashSet<PartObj> part;
	private List<PartObj> pecaCorrente;

	protected PartImpl() throws RemoteException {
		super();
	}

	@Override
	public void bind(String nomeRepository) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listp() throws RemoteException {
		for (PartObj item : part) {
		    System.out.println (item);
		}
	}

	@Override
	public void getp(String codigoPeca) throws RemoteException {
		if (part.contains(codigoPeca)) {
			
		}
		
	}

	@Override
	public PartObj bind(Integer codigo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showp(Integer codigo) throws RemoteException {
		for(int i=0; i<pecaCorrente.size(); i++) {
			if (pecaCorrente.get(i).getCodigoPeca().equals(codigo)) {
				return pecaCorrente.get(i).getCodigoPeca().toString();
			}
		}
		return "";
	}

	@Override
	public void clearList() throws RemoteException {
		part.clear();
		
	}

	@Override
	public void addp(PartObj part) throws RemoteException {
		this.part.add(part);
		
	}

	@Override
	public void quit() throws RemoteException {
		 try{
		        // Unregister ourself
		        Naming.unbind("rmi://192.168.0.14:1099/PartRepositoryService");

		        // Unexport; this will also remove us from the RMI runtime
		        UnicastRemoteObject.unexportObject(this, true);

		        System.out.println("exiting.");
		    }
		    catch(Exception e){}
		
	}

	@Override
	public String testeHelloWorld() throws RemoteException {
		return "parmera vc me da depressao";
	}
	

}
