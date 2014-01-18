package p2p;

import java.rmi.RemoteException;

public class GlobalFileInfo extends LocalFileInfo {

	private static final long serialVersionUID = 1L;

	ClientI client;

	public GlobalFileInfo(int id, String name, String path, long size,
			ClientI client) {
		super(id, name, path, size);
		this.client = client;
	}

	public GlobalFileInfo(LocalFileInfo lfi, ClientI client) {
		this(lfi.fileId, lfi.fileName, lfi.filePath, lfi.fileSize, client);
	}

	public String getUsername() {
		try {
			return client.getUsername();
		} catch (RemoteException e) {
			return "N/A";
		}
	}

	public ClientI getClient() {
		return client;
	}
}
