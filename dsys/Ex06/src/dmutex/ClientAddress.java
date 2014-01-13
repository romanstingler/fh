package dmutex;

public class ClientAddress {
	private String ip;
	private int id;
	CommunicationI remoteObject;

	public ClientAddress(String ip, int id) {
		this.ip = ip;
		this.id = id;
		this.remoteObject = null;
	}

	public String getServiceName() {
		return "dmutex" + id;
	}

	@Override
	public String toString() {
		return ip + " / " + id;
	}

	public String getIp() {
		return ip;
	}

	public int getId() {
		return id;
	}

	public void setRemoteObject(CommunicationI remoteObject) {
		this.remoteObject = remoteObject;
	}

	public CommunicationI getRemoteObject() {
		return remoteObject;
	}

}
