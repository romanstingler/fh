package account;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.Vector;

import common.VectorTimestamp;

enum BookingType {
	deposit, addinterest
};

public class DispatcherThread extends Thread {

	final int MIN_WAIT = 1000;
	final int MAX_WAIT = 5000;

	AccountI acc;
	BookingType bt;
	double val;
	VectorTimestamp ts;
	int id;
	
	Vector<AccountI> accounts;

	public DispatcherThread(AccountI acc, BookingType bt, double val,
			Vector<AccountI> accounts, VectorTimestamp ts, int id) {
		this.acc = acc;
		this.bt = bt;
		this.val = val;

		this.accounts = accounts;
		this.ts = ts;
		this.id = id;
	}

	public void run() {
		try {
			Random rand = new Random();
			int ms = MIN_WAIT + rand.nextInt((MAX_WAIT + 1) - MIN_WAIT);
			System.out.println("Dispatcher: calling " + bt.toString() + " "
					+ val + ") in " + ms + " msecs");
			sleep(ms); // wait between 1 and 5 seconds
			if (!accounts.contains(acc))
				return;
			try {
				if (bt == BookingType.deposit)
					acc.deposit(val, ts, id);
				else if (bt == BookingType.addinterest)
					acc.addInterest(val, ts, id);
			} catch (RemoteException re) {
				accounts.remove(acc);
				System.out.println("reference removed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
