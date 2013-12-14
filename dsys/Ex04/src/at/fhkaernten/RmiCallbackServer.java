package at.fhkaernten;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class RmiCallbackServer extends UnicastRemoteObject implements
		RmiCallbackServerI {

	private static final long serialVersionUID = 1L;
	private Map<String, RmiCallbackClientI> clients = new HashMap<String, RmiCallbackClientI>();

	protected RmiCallbackServer() throws RemoteException {
		super();
	}

	@Override
	public void register(RmiCallbackClientI client) throws RemoteException {
		clients.put(client.getName(), client);
	}

	@Override
	public void sendData(int mul) throws RemoteException {
		for (RmiCallbackClientI client : clients.values()) {
			client.receiveData(mul);
		}
	}

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

	public static void main(String[] args) {
		if (getRegistry() == null)
			System.exit(1);

		try {
			RmiCallbackServerI server = new RmiCallbackServer();

			try {
				Naming.bind("rmicallback", server);
				System.out.println("Bound server to name rmicallback.");
			} catch (MalformedURLException e) {
				System.out.println("Wrong URL Format");
				e.printStackTrace();
				System.exit(1);
			} catch (RemoteException e) {
				System.out.println("Remote Exception");
				e.printStackTrace();
				System.exit(1);
			} catch (AlreadyBoundException e) {
				System.out.println("Server exists already");
			}
		} catch (RemoteException e1) {
			System.out.println("Could not create Server object.");
			e1.printStackTrace();
			System.exit(1);
		}

	}

}
