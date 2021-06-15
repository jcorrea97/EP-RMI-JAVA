import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PartRepository extends Remote{
	
	public void bind(String nomeRepository) throws RemoteException;
	
	public void listp() throws RemoteException;
	
	public void getp(String nomeRepository) throws RemoteException;
	
	public PartObj bind(Integer codigo) throws RemoteException;
	
	public String showp(Integer codigo) throws RemoteException;
	
	public void clearList() throws RemoteException;
	
	public void addp(PartObj part) throws RemoteException;
	
	public void quit() throws RemoteException;
	
	public String testeHelloWorld() throws RemoteException;

}
