package npc;

<<<<<<< HEAD
import java.io.Serializable;

=======
import common.player.IPlayer;
>>>>>>> 4276775ecaf2233d5d939e80508f781d1b738d9d
import common.utils.Direction;
import player.Bullet;
import player.InvalidPlayerExceptions;
import common.utils.MathUtils;

/**
 * Moves very fast and shoots very fast
 *
 * @author Thomas Edwards
 *
 */
public class HardScheme implements ControlScheme, Serializable {

	private Direction randDir = getRandomDir();
	private int moveCounter = 0;

	public HardScheme() {
	}

	@Override
	public void doBestAction(NPC npc, IPlayer player) {
		try {
			// move at full player's speed.
			npc.move(randDir.getX() * npc.getSpeed(), randDir.getY() * npc.getSpeed());
		} catch (InvalidPlayerExceptions e) {
			// if we run into a wall, then choose a new direction...
			randDir = getRandomDir();
		}

		// shoot at the player alot change direction every 200
		moveCounter++;
		if (moveCounter > 200) {
			moveCounter = 0;
			new Bullet(npc.getCentreX(), npc.getCentreY(), MathUtils.calculateAngle(npc.getxLocation(),
					npc.getyLocation(), player.getCentreX(), player.getCentreY()), npc, 10, "npcBullet2");
			randDir = getRandomDir();
		} else if (moveCounter == 100 || moveCounter == 90 || moveCounter == 190 || moveCounter == 50
				|| moveCounter == 52) {
			new Bullet(npc.getCentreX(), npc.getCentreY(), MathUtils.calculateAngle(npc.getxLocation(),
					npc.getyLocation(), player.getCentreX(), player.getCentreY()), npc, 10, "npcBullet2");
		}
	}

	public Direction getRandomDir() {
		int dir = (int) (Math.random() * 8);
		return Direction.VALUES.get(dir);
	}

}
