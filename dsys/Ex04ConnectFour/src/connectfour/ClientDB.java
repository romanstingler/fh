package connectfour;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDB {
	private Map<String, ConnectFourClientI> clients;

	private List<String> clientQueue;

	public ClientDB() {
		clients = new HashMap<String, ConnectFourClientI>();
		clientQueue = new ArrayList<String>(2);
	}

	public synchronized void addClient(ConnectFourClientI client)
			throws RemoteException {
		String name = client.getName();
		clients.put(name, client);
		clientQueue.add(name);
		System.out.println("Client " + client.getName() + " has connected.");
		System.out.println(clientQueue);
		notifyAll();
	}

	public synchronized List<ConnectFourClientI> removeClient(int n)
			throws InterruptedException {
		while (n > clientQueue.size())
			wait();
		int i = 0;
		List<ConnectFourClientI> clientlist = new ArrayList<ConnectFourClientI>(
				0);
		while (i < n) {
			clientlist.add(clients.get(clientQueue.remove(0)));
			i++;
		}
		System.out.println("Removed: " + clientlist);
		return clientlist;
	}

	public synchronized int numClients() {
		return clientQueue.size();
	}

	public synchronized List<String> getClientQueue() {
		return clientQueue;
	}

}
