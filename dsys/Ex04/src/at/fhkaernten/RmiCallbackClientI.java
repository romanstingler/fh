package at.fhkaernten;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiCallbackClientI extends Remote {

	String getName() throws RemoteException;
	void receiveData(int mul) throws RemoteException;
}
