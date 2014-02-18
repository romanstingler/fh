package connectfour;

import java.rmi.RemoteException;

public class ConnectFourServerThread implements Runnable {

	private ClientDB clients;
	
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
