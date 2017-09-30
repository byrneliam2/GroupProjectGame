package npc;

import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;
import utils.MathUtils;

/**
 * Moves very fast and shoots very fast
 * 
 * @author Thomas Edwards
 *
 */
public class HardScheme implements ControlScheme {

	private utils.Direction randDir = getRandomDir();
	private int moveCounter = 0;

	public HardScheme() {
	}

	@Override
	public void doBestAction(NPC npc, Player player) {
		try {
			// move at half the player's speed.
			npc.move(randDir.getX() * npc.getSpeed(), randDir.getY() * npc.getSpeed());
		} catch (InvalidPlayerExceptions e) {
			// if we run into a wall, then choose a new direction...
			randDir = getRandomDir();
		}

		// shoot at the player alot change direction every 200
		moveCounter++;
		if (moveCounter > 200) {
			moveCounter = 0;
			new Bullet(npc.getxLocation(), npc.getyLocation(), MathUtils.calculateAngle(npc.getxLocation(),
					npc.getyLocation(), player.getxLocation(), player.getyLocation()), npc, 4);
			randDir = getRandomDir();
		} else if (moveCounter == 100 || moveCounter == 90 || moveCounter == 190 || moveCounter == 50
				|| moveCounter == 52) {
			new Bullet(npc.getxLocation(), npc.getyLocation(), MathUtils.calculateAngle(npc.getxLocation(),
					npc.getyLocation(), player.getxLocation(), player.getyLocation()), npc, 4);
		}
	}

	public utils.Direction getRandomDir() {
		int dir = (int) (Math.random() * 8);
		return utils.Direction.VALUES.get(dir);
	}

}
