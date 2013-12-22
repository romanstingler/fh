package account;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.VectorTimestamp;

public interface AccountI extends Remote {
	public void deposit(double val, VectorTimestamp ts, int id) throws RemoteException;
	
	/**
	 * Add interest to the balance
	 * @param val Percentage to add, e.g. "2"
	 * @throws RemoteException
	 */
	public void addInterest(double val, VectorTimestamp ts, int id) throws RemoteException;
}
