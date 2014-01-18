package p2p;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteFISI extends Remote{

	public int readByte() throws RemoteException;
}
