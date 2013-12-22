package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import account.Account;
import account.BookingDispatcherI;

public class Client {

	public static final int MAX_CLIENTS = 2;

	public static void main(String[] args) throws MalformedURLException,
			RemoteException, NotBoundException, InterruptedException {

		if (args.length < 1) {
			System.out.println("USAGE: java client.Client <ID>");
			System.exit(1);
		}

		int id = Integer.parseInt(args[0]);

		// Get reference to the booking dispatcher
		BookingDispatcherI dispatcher = (BookingDispatcherI) Naming
				.lookup("Accounts");

		// create Account object
		Account a = new Account(MAX_CLIENTS, id);

		// register Account with the booking dispatcher
		dispatcher.register(a);

		// Wait a few seconds
		Thread.sleep(2000);

		for (int i = 0; i < 10; i++) {
			a.getTs().next(id);
			dispatcher.deposit(((int) (Math.random() * 100000)) / 100.0,
					a.getTs(), id);
			Thread.sleep(500);
		}

		if (id == 0) {
			a.getTs().next(id);
			dispatcher.addInterest(2, a.getTs(), id);
		}
	}

}
