package dmutex;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Ex0602 {

	public static void main(String[] args) throws RemoteException, InterruptedException {
		if (args.length < 2) {
			System.out
					.println("USAGE: java dmutex.Ex0602 <myid> <address1>/<id1> [<address2>/<id2> ...]");
		}

		List<ClientAddress> clients = new ArrayList<ClientAddress>();

		int myId = Integer.parseInt(args[0]);
		for (int i = 1; i < args.length; i++) {
			String client = args[i];
			String[] parts = client.split("/");
			String ip = parts[0];
			int otherId = Integer.parseInt(parts[1]);
			clients.add(new ClientAddress(ip, otherId));
		}

		System.out.println("Starting as Node " + myId);
		System.out.println("Waiting for other nodes: " + clients);

		ClientAddress myself = new ClientAddress("localhost", myId);

		// Create unicast remote object;
		Client client = new Client(myId);

		// Create/bind to registry
		RMITools.bindLocal(myself.getServiceName(), client);
		
		//Connect to other clients
		client.ConnectOthers(clients);
	}

}
