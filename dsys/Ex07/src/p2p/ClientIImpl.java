package p2p;

import java.io.File;
import java.io.FileFilter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class ClientIImpl extends UnicastRemoteObject implements ClientI {

	private static final long serialVersionUID = 1L;

	String filepath;
	String username;

	public ClientIImpl(String filepath, String username) throws RemoteException {
		super();
		this.filepath = filepath;
		this.username = username;
	}

	@Override
	public LocalFileInfo[] getFileList() throws RemoteException {
		Vector<LocalFileInfo> list = new Vector<LocalFileInfo>();

		File dir = new File(filepath);
		FileFilter ff = new FileFilter() {
			public boolean accept(File f) {
				if (f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith(".jpg")
						|| f.getName().toLowerCase().endsWith(".gif")
						|| f.getName().toLowerCase().endsWith(".pdf"))
					return true;
				else {
					return false;
				}
			}
		};

		File[] arr = dir.listFiles(ff);
		int i = 0;
		for (File f : arr) {
			LocalFileInfo lfi = new LocalFileInfo(i++, f.getName(),
					f.getAbsolutePath(), f.length());
			list.add(lfi);
		}

		if (list.size() > 0)
			return list.toArray(new LocalFileInfo[0]);
		else
			return new LocalFileInfo[0];
	}

	public String getUsername() throws RemoteException {
		return username;
	}

	public String getFilepath() {
		return filepath;
	}

	@Override
	public RemoteFISI getFile(String abspath) throws RemoteException {
		return new RemoteFISImpl(abspath);
	}
}
