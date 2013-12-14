package at.fhkaernten;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	private static Registry getRegistry() {
		Registry reg = null;
		try {
			reg = LocateRegistry.createRegistry(1099);
			System.out.println("Registry created");
		} catch (RemoteException e) {
			try {
				reg = LocateRegistry.getRegistry();
				System.out.println("Registry found");
			} catch (RemoteException e2) {
				System.out.println("Registry cannot be established ");
			}
		}
		return reg;
	}

	public static void main(String[] args) throws RemoteException {
		getRegistry();

		HelloImpl hello = new HelloImpl();
		try {
			Naming.bind("hello", hello);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			try {
				Naming.rebind("hello", hello);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}

	}

}