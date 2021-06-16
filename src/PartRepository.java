import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PartRepository extends Remote{
	
	public void bind(String nomeRepository) throws RemoteException, MalformedURLException;
	
	public void listp() throws RemoteException;
	
	public void getp(String nomeRepository) throws RemoteException;
	
	public PartObj bind(Integer codigo) throws RemoteException;
	
	public void showp(Integer codigo) throws RemoteException;
	
	public void clearList() throws RemoteException;
	
	public void addp(PartObj part) throws RemoteException;
	
	public void quit() throws RemoteException;
	
	public String testeHelloWorld() throws RemoteException;

}
