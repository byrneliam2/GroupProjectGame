package npc;

import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;
import common.MathUtils;

/**
 * This scheme slowly shoots at the player and moves randomly
 * 
 * @author Thomas Edwards
 *
 */
public class EasyScheme implements ControlScheme {

	private common.Direction randDir = getRandomDir();
	private int shotCounter = 0;

	public EasyScheme() {
	}

	@Override
	public void doBestAction(NPC npc, Player player) {
		try {
			// move at half the player's speed.
			npc.move(randDir.getX() * npc.getSpeed() / 2, randDir.getY() * npc.getSpeed() / 2);
		} catch (InvalidPlayerExceptions e) {
			// if we run into a wall, then choose a new direction...
			randDir = getRandomDir();
		}

		// shoot at the player every 200 moves. and then change direction
		shotCounter++;
		if (shotCounter > 200) {
			shotCounter = 0;
			new Bullet(npc.getCentreX(), npc.getCentreY(), MathUtils.calculateAngle(npc.getxLocation(),
					npc.getyLocation(), player.getCentreX(), player.getCentreY()), npc, 4, "npcBullet1");
			randDir = getRandomDir();
		}
	}

	public common.Direction getRandomDir() {
		int dir = (int) (Math.random() * 8);
		return common.Direction.VALUES.get(dir);
	}

}
