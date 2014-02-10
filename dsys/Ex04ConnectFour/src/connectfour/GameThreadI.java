package connectfour;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameThreadI extends Remote {
	
	public ConnectFourWorld play(int col) throws RemoteException;

}
