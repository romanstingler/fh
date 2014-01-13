package dmutex;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CommunicationI extends Remote {
	
	public void request(TimeStamp ts) throws RemoteException;

	public int getID() throws RemoteException;
}
