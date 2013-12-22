package common;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMITools {
	public static void bindLocal(String name, UnicastRemoteObject obj)
	{
		//System.setSecurityManager(new RMISecurityManager());

		//Create and use registry
		Registry reg = null;
		try {
			reg = LocateRegistry.createRegistry(1099);
			System.out.println("Registry created");
		} catch (RemoteException e) {
			try {
				reg = LocateRegistry.getRegistry();
				System.out.println("Registry found");
			}
			catch (RemoteException e2) {
				System.out.println("Registry cannot be established " + e);
				System.exit(1);
			}
		}
		
		//Bind server
		try {
			// only neccessary, if ServerIImpl is not derived from UnicastRemoteObject:
		    //ServerI stub = (ServerI) UnicastRemoteObject.exportObject(s, 0);
		    reg.rebind(name, obj);

		    System.out.println("Server ready: " + name);
		} catch (Exception e) {
		    System.err.println("Server exception: " + e.toString());
		    e.printStackTrace();
		}
	}
}
