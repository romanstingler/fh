package p2p;

import java.rmi.*;
import java.rmi.registry.*;
import GUI.*;

public class Client {

	ClientIImpl clientiimpl;
	ServerI server;
	Registry registry;

	public Client() {
	}

	public void setClientIImpl(ClientIImpl ci) {
		this.clientiimpl = ci;
	}

	public ClientIImpl getClientiimpl() {
		return clientiimpl;
	}

	public boolean login(String username, String password, String servername) {
		// looking for server
		try {
			registry = LocateRegistry.getRegistry(servername);
			server = (ServerI) registry.lookup("P2PServer");
			// oder: server = (ServerI) Naming.lookup("rmi://" + servername +
			// "/P2PServer");

			return server.login(username, password, clientiimpl);
		} catch (NotBoundException nbe) {
			System.err.println("NotBoundException: " + nbe.toString());
		} catch (RemoteException re) {
			System.err.println("RemoteException: " + re.toString());
		}

		return false;
	}

	public boolean logout() {
		try {
			server.logout(clientiimpl);
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public GlobalFileInfo[] query(String keyword) {
		try {
			return server.query(keyword);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new GlobalFileInfo[0];
	}

	public static void main(final String[] args) {

		// System.setSecurityManager(new RMISecurityManager());

		final Client c = new Client();

		if (args.length == 3) {
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					new GUI(c, args[0], args[1], args[2]).setVisible(true);
				}
			});
		} else {
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					new GUI(c).setVisible(true);
				}
			});
		}

	}
}
