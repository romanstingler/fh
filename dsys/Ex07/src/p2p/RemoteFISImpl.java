package p2p;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteFISImpl extends UnicastRemoteObject implements RemoteFISI {

	private static final long serialVersionUID = 1L;
	FileInputStream fis;

	public RemoteFISImpl(String filepath) throws RemoteException {
		super();
		try {
			fis = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int readByte() throws RemoteException {
		try {
			return fis.read();
		} catch (IOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
}
