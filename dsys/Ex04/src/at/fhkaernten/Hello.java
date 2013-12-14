package at.fhkaernten;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface Hello extends Remote {
	
	public String sayHello(String name) throws RemoteException;
	
	public MessageObject getMessageObject() throws RemoteException;

}
