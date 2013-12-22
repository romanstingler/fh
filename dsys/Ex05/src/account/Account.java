package account;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.VectorTimestamp;

public class Account extends UnicastRemoteObject implements AccountI {

	private static final long serialVersionUID = 1L;

	private double balance;

	private VectorTimestamp ts;

	private int id;

	public Account(int dim, int id) throws RemoteException {
		super();

		balance = 0.0;
		ts = new VectorTimestamp(dim);
		this.id = id;
	}

	public VectorTimestamp getTs() {
		return ts;
	}

	public int getId() {
		return id;
	}

	@Override
	public void deposit(double val, VectorTimestamp ts, int id)
			throws RemoteException {

		System.out.println("Received Timestamp: " + ts);
		System.out.println("Local Timestamp: " + this.ts);

		if (!(this.id == id))
			this.ts.next(this.id, ts);

		System.out.println("Updated Timestamp: " + this.ts);
		balance += val;
		System.out.println("Depositing " + val + " yields new balance "
				+ balance);
	}

	@Override
	public void addInterest(double val, VectorTimestamp ts, int id)
			throws RemoteException {

		System.out.println("Received Timestamp: " + ts);
		System.out.println("Local Timestamp: " + this.ts);

		if (ts.isLessThan(this.ts)) {
			System.err.println("Causality Problem, can not add interest");
			System.exit(1);
		} else {
			balance *= (1 + val / 100);
			System.out.println("Interest added: " + val
					+ "% yields new balance " + balance);
		}

		if (!(this.id == id))
			this.ts.next(this.id, ts);

		System.out.println("Updated Timestamp: " + this.ts);
	}
}
