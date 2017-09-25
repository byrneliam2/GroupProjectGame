package npc;

import java.util.Timer;
import java.util.TimerTask;

import player.Player;

/**
 * NPC class.
 * 
 * @author Thomas Edwards
 *
 */
public class NPC extends Player {
	public static final int SPEED = 50;// rate in milliseconds that NPC is updated
	private static Timer npcTimer = new Timer();

	private Player p;
	private ControlScheme control;
	protected TimerTask npctask;

	/**
	 * @param name
	 * @param x
	 *            centre pixel x Location
	 * @param y
	 *            centre pixel y location
	 * @param health
	 * @param mainPlayer
	 * @param cs
	 *            the control scheme of the NPC to use.
	 */
	public NPC(String name, int x, int y, int health, Player mainPlayer, ControlScheme cs) {
		super(name, x, y);
		this.p = mainPlayer;
		this.control = cs;
		npctask = new TimerTask() {
			@Override
			public void run() {
				update();
			}
		};
		super.setMaxHealth(health);
		super.setHealth(health);
	}

	/**
	 * stops the timer which controls this NPC, which causes the NPC to simply stand
	 * still.
	 */
	public void stop() {
		npctask.cancel();
		npctask = new TimerTask() {
			@Override
			public void run() {
				update();
			}
		};
	}

	/**
	 * Starts the timer which moves and controls the NPC. NPC will start moving
	 * around.
	 */
	public void start() {
		npcTimer.scheduleAtFixedRate(npctask, 0, SPEED);
	}

	private void update() {
		control.doBestAction(this, p);
	}

	/**
	 * @return the angle FROM npc, TO player. between 0 and 2Pi. 0 being straight up.
	 */
	protected double getAngleToPlayer() {
		double x = super.getxLocation() - p.getxLocation();
		double y = super.getyLocation() - p.getyLocation();

		// math to calculate 360 degree angle.
		// might have to catch divide-by-0 errors. TEST those!
		double angle;
		if (x > 0 && y >= 0) {// top left corner
			angle = 3 * Math.PI / 2 + Math.atan(y / x);
		} else if (x >= 0 && y < 0) {// bottom left corner
			angle = Math.PI - Math.atan(x / y);
		} else if (x < 0 && y <= 0) {// bottom right corner
			angle = Math.PI / 2 + Math.atan(y / x);
		} else {// else if (x <= 0 && y > 0)//top right corner
			angle = -Math.atan(x / y);
		}

		return angle;
	}

}
