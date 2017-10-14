package npc;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import common.player.IPlayer;
import common.utils.DisplayValues;
import game.Game;
import player.InvalidPlayerExceptions;
import player.Player;

/**
 * NPC class.
 *
 * @author Thomas Edwards
 *
 */
public class NPC extends Player implements Serializable {

	public static final int updateRate = DisplayValues.FRAMERATE;// rate in milliseconds that NPC is updated
	public static Timer npcTimer = new Timer();

	private IPlayer p;
	private ControlScheme control;
	protected transient TimerTask npctask;

	/**
	 * @param name
	 * @param x
	 *            centre pixel x Location
	 * @param y
	 *            centre pixel y location
	 * @param health
	 * @param mainPlayer
	 * @param cs
	 */
	public NPC(String name, int x, int y, int health, IPlayer mainPlayer, ControlScheme cs) {
		super(name, x, y);
		this.p = mainPlayer;
		this.control = cs;
		super.setMaxHealth(health);
		super.setHealth(health);

		if (Game.DEV_MODE) {
			super.setMaxHealth(1);
			super.setHealth(1);
		}
	}

	@Override
	public boolean move(double dx, double dy) throws InvalidPlayerExceptions {
		//custom npc moving which ignores all environmental effects such as fire.
		//also prevents npc's from moving through doors.
		playerBox.setFrame(playerBox.getX() + dx, playerBox.getY() + dy, playerBox.getWidth(), playerBox.getHeight());
		if (map.canMove(playerBox)) {
			return true;
		} else {
			playerBox.setFrame(playerBox.getX() - dx, playerBox.getY() - dy, playerBox.getWidth(),
					playerBox.getHeight());
			throw new InvalidPlayerExceptions("You cant make a move/Invalid move");
		}
	}

	/**
	 * Stops the timer which controls this NPC, which causes the NPC to simply stand
	 * still.
	 */
	public boolean stop() {
		if (npctask == null)
			return false;
		return npctask.cancel();
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
			super.isDead = true;
			stop();
			p.getMap().removeNPC(this);
		}
	}

	private void update() {
		control.doBestAction(this, p);
	}

}
