package account;

import java.rmi.RemoteException;

public interface BookingDispatcherI extends AccountI {

	public void register(AccountI acc) throws RemoteException;
}