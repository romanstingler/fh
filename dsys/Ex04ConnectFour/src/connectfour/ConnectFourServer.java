package connectfour;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ConnectFourServer extends UnicastRemoteObject implements
		ConnectFourServerI {

	private static final long serialVersionUID = 1L;

	private ClientDB clients = new ClientDB();

	protected ConnectFourServer() throws RemoteException {
		super();
		new Thread(new ConnectFourServerThread(clients)).start();
	}

	public static void main(String[] args) throws RemoteException {
		// 1. Create or connect to registry
		Registry reg = null;
		try {
			reg = LocateRegistry.createRegistry(1099);
			System.out.println("Created new registry");
		} catch (RemoteException e) {
			try {
				reg = LocateRegistry.getRegistry();
				System.out.println("Reusing existing registry");
			} catch (RemoteException e2) {
				System.err.println("Unable to establish registry.");
				System.exit(1);
			}
		}
		if (reg != null) {
			System.out.println("Registry OK.");
		} else {
			System.err.println("Unable to establish registry.");
			System.exit(21);
		}

		// 2. Instantiate Server object
		ConnectFourServer server = new ConnectFourServer();

		// 3. Bind server object to registry
		try {
			reg.bind("connectFour", server);
			System.out.println("Bound service.");
		} catch (AlreadyBoundException e) {
			System.err.println("Service already bound!");
			System.exit(3);
		}

		// 4. Wait for connections...
		System.out.println("Waiting for connections...");
		// No while loop necessary
	}

	@Override
	public void register(ConnectFourClientI client) throws RemoteException {
		clients.addClient(client);
	}
}
