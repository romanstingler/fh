package at.fhkaernten;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RmiCallbackClient extends UnicastRemoteObject implements
		RmiCallbackClientI {

	private static final long serialVersionUID = 1L;
	private String name;
	private int mul = 1;
	private final static int N = 10;

	public RmiCallbackClient(String name) throws RemoteException {
		super();
		this.name = name;
	}

	@Override
	public String getName() throws RemoteException {
		return this.name;
	}

	@Override
	public void receiveData(int mul) throws RemoteException {
		this.mul *= mul;
		System.out.println("Client " + this.name + ": " + this.mul);
	}

	public static void main(String[] args) throws RemoteException {
		if (args.length < 1) {
			System.out
					.println("USAGE: java at.fhkaernten.RMICallbackClient <ID>");
			System.exit(1);
		}

		Scanner sc = new Scanner(System.in);

		Remote server = null;
		RmiCallbackServerI rmiserver = null;

		try {
			server = Naming.lookup("rmi://localhost/rmicallback");
		} catch (MalformedURLException e) {
			System.out.println("Wrong URL format.");
			e.printStackTrace();
			System.exit(1);
		} catch (RemoteException e) {
			System.out.println("Unable to connect to registry");
			e.printStackTrace();
			System.exit(1);
		} catch (NotBoundException e) {
			System.out.println("Service not known");
			e.printStackTrace();
			System.exit(1);
		}

		if (server != null && server instanceof RmiCallbackServerI) {
			System.out.println("Connected to server.");
			rmiserver = (RmiCallbackServerI) server;
		} else {
			System.out.println("Connection failed");
			System.exit(1);
		}

		try {
			RmiCallbackClientI client = new RmiCallbackClient(args[0]);
			rmiserver.register(client);
		} catch (RemoteException e) {
			System.out.println("Could not create client object.");
			e.printStackTrace();
			System.exit(1);
		}

		int mult;
		do {
			System.out.println("Zahl eingeben:");
			mult = sc.nextInt();
			rmiserver.sendData(mult);
		} while (mult != 0);

		sc.close();

		// RmiCallbackClientI[] client = new RmiCallbackClientI[N];
		//
		// for (int i = 0; i < N; i++) {
		// try {
		// client[i] = new RmiCallbackClient("Test" + i);
		// rmiserver.register(client[i]);
		// } catch (RemoteException e) {
		// System.out.println("Could not create client object.");
		// e.printStackTrace();
		// System.exit(1);
		// }
		// }
		//
		// for (int i = 2; i <= N; i++) {
		// rmiserver.sendData(i);
		// }
	}
}
