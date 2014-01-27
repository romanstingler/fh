package p2p;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

public class ServerIImpl extends UnicastRemoteObject implements ServerI {

	private static final long serialVersionUID = 1L;

	String SYSTEM_PASSWORD = "distsys";

	Vector<ClientI> connectedClients;
	Vector<GlobalFileInfo> files;

	public ServerIImpl() throws RemoteException {
		files = new Vector<GlobalFileInfo>();
		connectedClients = new Vector<ClientI>();
	}

	public boolean login(String username, String passwd, ClientI client)
			throws RemoteException {

		if (SYSTEM_PASSWORD.equals(passwd)) {

			connectedClients.add(client); // check if users is already logged on
											// is ignored here!

			System.out.println(username + " connected");

			LocalFileInfo[] clientFiles = client.getFileList();
			for (LocalFileInfo lfi : clientFiles) {
				GlobalFileInfo gfi = new GlobalFileInfo(lfi, client);
				files.add(gfi);
			}

			return true;
		} else {
			System.out.println(username + " entered wrong password!");
			return false;
		}
	}

	@Override
	public GlobalFileInfo[] query(String keyword) throws RemoteException {
		ArrayList<GlobalFileInfo> result = new ArrayList<GlobalFileInfo>();

		if (keyword.isEmpty())
			result.addAll(files);
		else {
			for (GlobalFileInfo gfi : files) {
				if (gfi.fileName.equals(keyword)) {
					result.add(gfi);
				}
			}
		}

		// GlobalFileInfo[] resultlist = new GlobalFileInfo[result.size()];
		// for (int i = 0; i < result.size(); i++) {
		// resultlist[i] = result.get(i);
		// }

		GlobalFileInfo[] resultlist = result.toArray(new GlobalFileInfo[result.size()]);
		return resultlist;
	}

	@Override
	public void logout(ClientI client) throws RemoteException {
		Vector<GlobalFileInfo> removecandidates = new Vector<GlobalFileInfo>();

		for (GlobalFileInfo gfi : files) {
			if (gfi.client.equals(client))
				removecandidates.add(gfi);
		}

		files.removeAll(removecandidates);
		connectedClients.remove(client);

	}
}
