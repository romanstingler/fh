package account;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import common.VectorTimestamp;

public class BookingDispatcher extends UnicastRemoteObject implements
		BookingDispatcherI {
	private static final long serialVersionUID = 1L;
	Vector<AccountI> accounts;

	public BookingDispatcher() throws RemoteException {
		accounts = new Vector<AccountI>();
	}

	public void register(AccountI acc) throws RemoteException {
		accounts.add(acc);
		System.out.println("Account added! (" + accounts.size() + " elements)");
	}

	public void addInterest(final double percent, VectorTimestamp ts, int id)
			throws RemoteException {
		System.out.println("Called from Client " + id);
		for (AccountI acc : accounts) {
			DispatcherThread dt = new DispatcherThread(acc,
					BookingType.addinterest, percent, accounts, ts, id);
			dt.start();
		}

	}

	public void deposit(final double amount, VectorTimestamp ts, int id)
			throws RemoteException {
		System.out.println("Called from Client " + id);
		for (AccountI acc : accounts) {
			DispatcherThread dt = new DispatcherThread(acc,
					BookingType.deposit, amount, accounts, ts, id);
			dt.start();
		}
	}

}
