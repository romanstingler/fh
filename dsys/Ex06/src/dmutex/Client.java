package dmutex;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Client extends UnicastRemoteObject implements CommunicationI {

	private static final long serialVersionUID = 1L;

	private int id;

	private List<ClientAddress> clients;

	/**
	 * Timestamp when entry into CS was requested. Null if no request is active.
	 */
	TimeStamp csRequestTs;

	/**
	 * Current Lamport time
	 */
	TimeStamp ts;

	protected Client(int id) throws RemoteException {
		super();
		this.id = id;
		ts = new TimeStamp(id);
	}

	@Override
	public void request(TimeStamp ts) throws RemoteException {
		this.ts = this.ts.next(ts);

		// I do not want to go into CS anyway - proceed!
		if (csRequestTs == null) {
			System.out.println("I do not want to go into CS anyway - proceed!");
			return;
		}
		if (ts.isSmaller(csRequestTs)) {
			System.out
					.println("Your ts is smaller, i.e. you asked earlier - proceed!");
			return;
		}

		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (csRequestTs == null) {
				System.out
						.println("I no langer want to go into the CS - proceed!");
				return;
			}
			if (ts.isSmaller(csRequestTs)) {
				System.out.println("Your ts is now smaller - proceed!");
				return;
			}

		}
	}

	@Override
	public int getID() throws RemoteException {
		return id;
	}

	private void run() throws InterruptedException, RemoteException {
		while (true) {
			// Sleep a random amount of time
			Thread.sleep((int) (Math.random() * 5000) + 1000);

			// Update Lamport timestamp (of when we want to enter the CS)
			ts = ts.next();
			csRequestTs = ts;

			// Try to enter critical section
			for (ClientAddress client : clients) {
				ts = ts.next();
				client.getRemoteObject().request(csRequestTs);
			}

			// Enter critical section
			System.out.println("==== CLIENT " + id
					+ " ENTERING CRITICAL SECTION ====");

			// Sleep
			Thread.sleep((int) (Math.random() * 8000) + 2000);

			// Leave critical section
			System.out.println("==== CLIENT " + id
					+ " LEAVING CRITICAL SECTION ====");

			csRequestTs = null;
			// Loop
		}
	}

	public void ConnectOthers(List<ClientAddress> clients)
			throws InterruptedException, RemoteException {
		this.clients = clients;
		boolean allConnected = false;
		while (!allConnected) {
			for (ClientAddress client : clients) {
				if (client.getRemoteObject() != null)
					continue;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				allConnected = true;
				String registryIp = client.getIp();
				String name = client.getServiceName();
				try {
					Remote object = Naming.lookup("rmi://" + registryIp + "/"
							+ name);
					if (object != null && object instanceof CommunicationI) {
						client.setRemoteObject((CommunicationI) object);
						System.out.println("Connected to client " + client);
					}
				} catch (MalformedURLException | RemoteException
						| NotBoundException e) {
					// System.out.println("Connection attempt to client " +
					// client
					// + " failed.");
					allConnected = false;
				}
			}
		}
		System.out.println("Connected to ALL clients.");

		this.run();
	}
}
