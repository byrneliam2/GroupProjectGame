package npc;

import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;
import utils.MathUtils;

/**
 * This scheme slowly shoots at the player and moves randomly
 * 
 * @author Thomas Edwards
 *
 */
public class EasyScheme implements ControlScheme {

	private RandDirection randDir;
	private int shotCounter = 0;

	public EasyScheme() {
	}

	@Override
	public void doBestAction(NPC npc, Player player) {

		if (randDir == null) {
			randDir = new RandDirection();
		}
		try {
			npc.move(randDir.getX() * npc.getSpeed() / 2, randDir.getY() * npc.getSpeed() / 2);
		} catch (InvalidPlayerExceptions e) {
			randDir = new RandDirection();
		}

		// shoot at the player every 200 moves. and then change direction
		shotCounter++;
		if (shotCounter > 200) {
			shotCounter = 0;
			new Bullet(npc.getxLocation(), npc.getyLocation(), MathUtils.calculateAngle(npc.getxLocation(),
					npc.getyLocation(), player.getxLocation(), player.getyLocation()), npc);
			randDir = new RandDirection();
		}
	}

	private class RandDirection {
		private int x, y;

		RandDirection() {
			double rand1 = Math.random();
			double rand2 = Math.random();
			if (rand1 < 0.33) {
				x = -1;
			} else if (rand1 < 0.66) {
				x = 0;
			} else {
				x = 1;
			}
			if (rand2 < 0.33) {
				y = -1;
			} else if (rand2 < 0.66) {
				y = 0;
			} else {
				y = 1;
			}
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}

}
