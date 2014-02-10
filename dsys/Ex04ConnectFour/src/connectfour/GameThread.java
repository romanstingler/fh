package connectfour;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class GameThread extends UnicastRemoteObject implements Runnable,
		GameThreadI {

	private static final long serialVersionUID = 1L;

	private static final int W = 7, H = 6;

	private ConnectFourWorld world;

	private ConnectFourClientI client1, client2;

	private int turn;

	private boolean stopped = false;

	protected GameThread(List<ConnectFourClientI> clients)
			throws RemoteException {
		super();
		world = new ConnectFourWorld(W, H);
		client1 = clients.get(0);
		client2 = clients.get(1);
		turn = 0;

		client1.beginGame(this, client2, world);
		client2.beginGame(this, client1, world);
	}

	private boolean checkMove(int col) {
		if (col >= 0 && col <= W - 1 && world.getWorld()[col][0] == 0) {
			int i = H - 1;
			while (i > 0 && world.getWorld()[col][i] != 0) {
				i--;
			}
			if (turn % 2 == 0) {
				world.getWorld()[col][i] = 1;
			} else
				world.getWorld()[col][i] = 2;

			turn++;
			return true;
		} else
			return false;
	}

	private boolean checkWin() {

		for (int row = 0; row < H; row++)
			for (int col = 0; col < W - 3; col++)
				if (world.getWorld()[col][row] > 0
						&& world.getWorld()[col][row] == world.getWorld()[col + 1][row]
						&& world.getWorld()[col][row] == world.getWorld()[col + 2][row]
						&& world.getWorld()[col][row] == world.getWorld()[col + 3][row])
					return true;

		for (int col = 0; col < W - 1; col++)
			for (int row = 0; row < H - 3; row++)
				if (world.getWorld()[col][row] > 0
						&& world.getWorld()[col][row] == world.getWorld()[col][row +1]
						&& world.getWorld()[col][row] == world.getWorld()[col][row +2]
						&& world.getWorld()[col][row] == world.getWorld()[col][row +3])
					return true;

		for (int row = 0; row < H - 3; row++)
			for (int col = 0; col < W - 3; col++)
				if (world.getWorld()[col][row] > 0
						&& world.getWorld()[col][row] == world.getWorld()[col + 1][row + 1]
						&& world.getWorld()[col][row] == world.getWorld()[col + 2][row + 2]
						&& world.getWorld()[col][row] == world.getWorld()[col + 3][row + 3])
					return true;

		for (int row = H - 1; row >= 3; row--)
			for (int col = 0; col < W - 3; col++)
				if (world.getWorld()[col][row] > 0
						&& world.getWorld()[col][row] == world.getWorld()[col + 1][row - 1]
						&& world.getWorld()[col][row] == world.getWorld()[col + 2][row - 2]
						&& world.getWorld()[col][row] == world.getWorld()[col + 3][row - 3])
					return true;

		return false;
	}

	@Override
	public ConnectFourWorld play(int col) throws RemoteException {
		if (checkMove(col)) {
			return world;
		} else
			return null;

	}

	@Override
	public void run() {
		while (!stopped) {
			try {
				client1.play(world);
			} catch (RemoteException e) {
				stopp();
			}
			if (turn >= 7 && checkWin()) {
				try {
					client1.endGame("You won.", world);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				try {
					client2.endGame("You lost.", world);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				stopp();
				continue;
			}
			try {
				client2.play(world);
			} catch (RemoteException e) {
				stopp();
			}
			if (turn >= 7 && checkWin()) {
				try {
					client1.endGame("You won.", world);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				try {
					client2.endGame("You lost.", world);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				stopp();
				continue;
			}

			if (turn >= W * H) {
				try {
					client1.endGame("Game has ended. It is a draw.", world);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				try {
					client2.endGame("Game has ended. It is a draw.", world);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				stopp();
				continue;
			}
		}

	}

	public void stopp() {
		stopped = true;
	}

}
