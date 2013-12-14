package at.fhkaernten;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {

	private static final long serialVersionUID = 1L;

	protected HelloImpl() throws RemoteException {
		super();
	}

	@Override
	public String sayHello(String name) throws RemoteException {
		System.out.println(" Server - Hello " + name);
		return "Hello " + name;
	}

	@Override
	public MessageObject getMessageObject() throws RemoteException {
		MessageObject mo = new MessageObject();
		System.out.println(mo);
		return mo;
	}

}
