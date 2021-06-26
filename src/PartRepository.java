import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface PartRepository extends Remote{
	
	public void bind(String nomeRepository) throws RemoteException, MalformedURLException;
	
	public void listp() throws RemoteException;
	
	public PartObj getp(Integer nomeRepository) throws RemoteException;
	
	public void showp(PartObj pecaCorrente) throws RemoteException;
	
	public void clearList(HashMap<PartObj, Integer> subPecas) throws RemoteException;
	
	public void addsubpart(PartObj subPecasCorrente, PartObj pecaCorrente, Integer n) throws RemoteException;
	
	public void addp(PartObj part) throws RemoteException;
	
	public void quit(String servidorCorrente) throws RemoteException;

}
