package p2p;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	
	public static void bindLocal(String name, ServerIImpl s)
	{
		//System.setSecurityManager(new RMISecurityManager());

		//Registry erzeugen bzw. verwenden
		Registry reg = null;
		try {
			reg = LocateRegistry.createRegistry(1099);
			System.out.println("Registry created");
		}
		catch (RemoteException e) {
			try {
				reg = LocateRegistry.getRegistry();
				System.out.println("Registry found");
			}
			catch (RemoteException e2) {
				System.out.println("Registry cannot be established " + e);
				System.exit(1);
			}
		}


		//Server binden
		try {
            //nur notwendig, wenn ServerIImpl nicht von UnicastRemoteObject erbt:
		    //ServerI stub = (ServerI) UnicastRemoteObject.exportObject(s, 0);
		    reg.rebind(name, (ServerI)s);

		    System.out.println("Server ready");
		} catch (Exception e) {
		    System.err.println("Server exception: " + e.toString());
		    e.printStackTrace();
		}

	}


	public static void main(String[] argv)
	{
		ServerIImpl s;
		try {
			s = new ServerIImpl();
			bindLocal("P2PServer", s);
		} catch (RemoteException re) {
			re.printStackTrace();
		}

	}
}
