package connectfour;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Con4Panel extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 100;
	private static final int HEIGHT = 100;

	private ConnectFourWorld world = null;

	private int col = -1;

	public void setWorld(ConnectFourWorld world) {
		this.world = world;
		this.repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		if (world != null) {
			return new Dimension(world.getWidth() * WIDTH, world.getHeight()
					* HEIGHT);
		}
		return new Dimension(WIDTH, HEIGHT);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawWorld(g);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int code = event.getKeyCode();
		switch (code) {
		case KeyEvent.VK_1:
			System.out.println("Pressed 1.");
			col = 0;
			break;
		case KeyEvent.VK_2:
			System.out.println("Pressed 2.");
			col = 1;
			break;
		case KeyEvent.VK_3:
			System.out.println("Pressed 3.");
			col = 2;
			break;
		case KeyEvent.VK_4:
			System.out.println("Pressed 4.");
			col = 3;
			break;
		case KeyEvent.VK_5:
			System.out.println("Pressed 5.");
			col = 4;
			break;
		case KeyEvent.VK_6:
			System.out.println("Pressed 6.");
			col = 5;
			break;
		case KeyEvent.VK_7:
			System.out.println("Pressed 7.");
			col = 6;
			break;
		default:
			System.out.println("Cleared input");
			col = -1;
			break;

		/*
		 * case KeyEvent.VK_LEFT: world.getPlayer().newDirection(
		 * Direction.DIRECTION_LEFT); break; case KeyEvent.VK_RIGHT:
		 * world.getPlayer().newDirection(Direction .DIRECTION_RIGHT); break;
		 * case KeyEvent.VK_UP: world.getPlayer
		 * ().newDirection(Direction.DIRECTION_UP); break; case
		 * KeyEvent.VK_DOWN: world.getPlayer().newDirection(Direction
		 * .DIRECTION_DOWN); break;
		 */
		}
	}

	public int getCol() {
		int coltmp = col;
		col = -1;
		return coltmp;
	}

	@Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	public void drawWorld(Graphics g) {
		if (world == null)
			return;
		for (int r = 0; r < world.getHeight(); r++) {
			for (int c = 0; c < world.getWidth(); c++) {
				// Set color based on current content of the cell
				switch (world.getWorld()[c][r]) {
				case 1:
					g.setColor(Color.RED);
					break;
				case 2:
					g.setColor(Color.GREEN);
					break;
				default:
					g.setColor(Color.GRAY);
					break;
				}

				// Fill field
				g.fillRect(c * WIDTH, r * HEIGHT, WIDTH - 10, HEIGHT - 10);
			}
		}
	}
}
