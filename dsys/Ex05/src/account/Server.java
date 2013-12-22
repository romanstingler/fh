package account;

import java.rmi.*;
import java.rmi.server.*;
import common.*;

public class Server {

	public static void main(String[] argv) {
		BookingDispatcher s;
		try {
			s = new BookingDispatcher();
			RMITools.bindLocal("Accounts", (UnicastRemoteObject) s);
		} catch (RemoteException re) {
			re.printStackTrace();
		}

	}
}
