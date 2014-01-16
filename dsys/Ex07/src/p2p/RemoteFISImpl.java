package p2p;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;

public class RemoteFISImpl /*TODO: something missing here*/ {

	FileInputStream fis;
	
	public RemoteFISImpl(String filepath) throws RemoteException
	{
		super();
		try {
			fis = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//TODO: implement the readByte() method here!
	//hint: fis.read();

}
