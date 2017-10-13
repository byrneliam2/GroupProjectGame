package npc;

import java.io.Serializable;

import common.player.IPlayer;
import common.utils.Direction;
import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;
import common.utils.MathUtils;

/**
 * Moves randomly and shoots the player medium pace.
 *
 * @author Thomas Edwards
 *
 */
public class MediumScheme implements ControlScheme, Serializable {

	protected Direction randDir = getRandomDir();
	protected int moveCounter = 0;

	public MediumScheme() {
	}

	@Override
	public void doBestAction(NPC npc, IPlayer player) {
		try {
			// move at half the player's speed.
			npc.move(randDir.getX() * npc.getSpeed() / 2, randDir.getY() * npc.getSpeed() / 2);
		} catch (InvalidPlayerExceptions e) {
			// if we run into a wall, then choose a new direction...
			randDir = getRandomDir();
		}

		moveCounter++;
		decideShooting(npc, player);
	}

	/**
	 * Descides whether it is time to shoot, if it is, then make a new bullet.
	 *  @param npc
	 * @param player
	 */
	public void decideShooting(NPC npc, IPlayer player) {
		// shoot at the player every 100 moves change direction every 200
		if (moveCounter > 200) {
			moveCounter = 0;
			new Bullet(npc.getCentreX(), npc.getCentreY(), MathUtils.calculateAngle(npc.getxLocation(),
					npc.getyLocation(), player.getCentreX(), player.getCentreY()), npc, 4, "npcBullet4");
			randDir = getRandomDir();
		} else if (moveCounter == 100) {
			new Bullet(npc.getCentreX(), npc.getCentreY(), MathUtils.calculateAngle(npc.getxLocation(),
					npc.getyLocation(), player.getCentreX(), player.getCentreY()), npc, 4, "npcBullet4");
		}
	}

	public Direction getRandomDir() {
		int dir = (int) (Math.random() * 8);
		return Direction.VALUES.get(dir);
	}

}
