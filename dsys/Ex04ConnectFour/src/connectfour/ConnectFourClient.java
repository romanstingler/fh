package connectfour;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;

public class ConnectFourClient extends UnicastRemoteObject implements
		ConnectFourClientI {

	private static final long serialVersionUID = 1L;

	private String name;

	private JFrame f;

	private Con4Panel panel;

	private ConnectFourClientI partner;

	private GameThreadI server;

	protected ConnectFourClient(String name) throws RemoteException {
		super();
		this.name = name;
	}

	@Override
	public void beginGame(GameThreadI server, ConnectFourClientI partner,
			ConnectFourWorld world) throws RemoteException {

		this.server = server;
		this.partner = partner;

		f = new JFrame("Connect Four Frame");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new Con4Panel();
		panel.setWorld(world);

		f.add(panel);
		f.pack();
		f.addKeyListener(panel);
		f.setVisible(true);

	}

	@Override
	public void play(ConnectFourWorld world) throws RemoteException {

		panel.setWorld(world);
		ConnectFourWorld newworld = null;

		do {
			// int col = (int) (Math.random() * 7);
			// col = -1;
			int col = panel.getCol();

			newworld = server.play(col);
		} while (newworld == null);
		world = newworld;
		panel.setWorld(world);
	}

	@Override
	public void endGame(String msg, ConnectFourWorld world)
			throws RemoteException {
		panel.setWorld(world);
		System.out.println(msg);
	}

	@Override
	public String getName() throws RemoteException {
		return this.name;
	}

	public static void main(String[] args) throws RemoteException {
		Remote server = null;
		ConnectFourServerI connectFourServer = null;

		try {
			server = Naming.lookup("rmi://localhost/connectFour");
		} catch (MalformedURLException e) {
			System.err.println("Wrong URL.");
			System.exit(1);
		} catch (RemoteException e) {
			System.err.println("Unable to connect to registry.");
			System.exit(2);
		} catch (NotBoundException e) {
			System.err.println("Service not known.");
			System.exit(3);
		}

		if (server != null && server instanceof ConnectFourServerI) {
			System.out.println("Successfully connected.");
			connectFourServer = (ConnectFourServerI) server;
		} else {
			System.err.println("Server connection failed.");
			System.exit(4);
		}

		System.out.println("Enter client name:");
		Scanner sc = new Scanner(System.in);

		ConnectFourClient client = new ConnectFourClient(sc.next());

		sc.close();

		connectFourServer.register(client);
	}
}
