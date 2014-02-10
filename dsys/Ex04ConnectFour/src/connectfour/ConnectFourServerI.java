package connectfour;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectFourServerI extends Remote {

	public void register(ConnectFourClientI client) throws RemoteException;

}
