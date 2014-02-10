package connectfour;

import java.awt.Graphics;
import java.io.Serializable;

public class ConnectFourWorld implements Serializable {

	private static final long serialVersionUID = 1L;

	private int width;
	private int height;

	private int[][] world;

	public ConnectFourWorld(int w, int h) {
		width = w;
		height = h;
		world = new int[w][h];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		for (int row = height - 1; row >= 0; row--) {
			for (int col = 0; col < width; col++) {
				sb.append(world[col][row]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public void draw(Graphics g) {

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[][] getWorld() {
		return world;
	}

	public void setWorld(int[][] world) {
		this.world = world;
	}
}
