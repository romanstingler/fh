package p2p;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ClientI extends Remote {
	LocalFileInfo[] getFileList() throws RemoteException;
	String getUsername() throws RemoteException;
	RemoteFISI getFile(String abspath) throws RemoteException;
	
}
