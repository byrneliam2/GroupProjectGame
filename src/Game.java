import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import player.Player;

/**
 *
 * @author Thomas Edwards
 *
 */
public class Game {

	private Player player;

	public Game() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(getMapBackground(), 0, 0, 400, 400, null);
				g.drawImage(getEntityForeground(), 0, 0, 400, 400, null);
			}
		};
		panel.setPreferredSize(new Dimension(600, 600));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public void startGame() {

	}

	public void pauseGame() {

	}

	/**
	 * Gets the background map Image.
	 */
	public Image getMapBackground() {
		return null;
	}

	/**
	 * Gets an image of the entities including items, npc's
	 */
	public Image getEntityForeground() {
		return null;
	}

	public Player getPlayer() {
		return this.player;
	}

	private void drawBullets() {
	}

	private void drawMap() {
	}

	private void drawPlayer() {
	}

	private void drawItems() {
	}

	private void drawNPCs() {
	}

	public static void main(String[] args) {
		new Game();
	}
}
