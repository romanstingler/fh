package at.fhkaernten;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

	public static void main(String[] args) {
		try {
			Hello helloref = (Hello) Naming.lookup("rmi://localhost/hello");
			System.out.println("Client - " + helloref.sayHello("Test"));
			for (int i = 0; i < 10; i++) {
				MessageObject mo = helloref.getMessageObject();
				System.out.println("Client - " + mo);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

}
