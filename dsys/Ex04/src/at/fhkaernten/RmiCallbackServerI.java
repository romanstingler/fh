package at.fhkaernten;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiCallbackServerI extends Remote {
	void register(RmiCallbackClientI client) throws RemoteException;
	void sendData(int mul) throws RemoteException;

}
