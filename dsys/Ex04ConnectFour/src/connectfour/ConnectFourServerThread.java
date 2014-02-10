package connectfour;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ConnectFourServerThread implements Runnable {

	private ClientDB clients;
	
	private List<ConnectFourWorld> worlds = new ArrayList<ConnectFourWorld>(0);

	public ConnectFourServerThread(ClientDB clients) {
		this.clients = clients;
	}

	@Override
	public void run() {
		while (true) {
			if (clients.numClients() >= 2)
				try {
					new Thread(new GameThread(clients.removeClient(2))).start();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				}

		}

	}
}
