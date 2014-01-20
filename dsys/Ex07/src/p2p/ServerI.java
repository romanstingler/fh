package p2p;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerI extends Remote{
	
	boolean login(String username, String passwd, ClientI client) throws RemoteException;
	GlobalFileInfo[] query(String keyword) throws RemoteException;
	void logout(ClientI client) throws RemoteException;
}
