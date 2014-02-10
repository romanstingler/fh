package connectfour;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectFourClientI extends Remote {

	public void beginGame(GameThreadI server, ConnectFourClientI partner, 
						  ConnectFourWorld world) throws RemoteException;
	
	public void play(ConnectFourWorld world) throws RemoteException;
	
	public void endGame(String msg, ConnectFourWorld world) throws RemoteException;
	
	public String getName() throws RemoteException;
}
