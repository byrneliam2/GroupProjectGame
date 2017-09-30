package npc;

import java.util.Timer;
import java.util.TimerTask;

import frames.MainDisplay;
import map.Map;
import player.Player;

/**
 * NPC class.
 * 
 * @author Thomas Edwards
 *
 */
public class NPC extends Player {
	public static final int updateRate = MainDisplay.FRAMERATE;// rate in milliseconds that NPC is updated
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
		super.setMaxHealth(health);
		super.setHealth(health);
	}

	/**
	 * stops the timer which controls this NPC, which causes the NPC to simply stand
	 * still.
	 */
	public void stop() {
		npctask.cancel();
	}

	/**
	 * Starts the timer which moves and controls the NPC. NPC will start moving
	 * around.
	 */
	public void start() {
		npctask = new TimerTask() {
			@Override
			public void run() {
				update();
			}
		};
		npcTimer.scheduleAtFixedRate(npctask, 0, updateRate);
	}

	@Override
	public void takeDamage() {
		super.health--;
		if (super.health <= 0) {
			stop();
			p.getMap().removeNPC(this);
		}
	}

	private void update() {
		control.doBestAction(this, p);
	}

}
