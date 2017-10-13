package npc;

import java.io.Serializable;

import common.player.IPlayer;
import common.utils.Direction;
import common.utils.MathUtils;
import player.Bullet;
import player.InvalidPlayerExceptions;

/**
 * Scheme used for the boss, shoots both randomly and structured.
 * @author Thomas Edwards
 */
public class BossScheme implements ControlScheme, Serializable {
	private Direction randDir = getRandomDir();
	private int moveCounter = 0;

	public BossScheme() {
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
					npc.getyLocation(), player.getCentreX(), player.getCentreY()), npc, 6, "npcBullet2");
			randDir = getRandomDir();
		} else if (moveCounter == 100 || moveCounter == 90 || moveCounter == 190 || moveCounter == 50
				|| moveCounter == 52) {
			new Bullet(npc.getCentreX(), npc.getCentreY(), MathUtils.calculateAngle(npc.getxLocation(),
					npc.getyLocation(), player.getCentreX(), player.getCentreY()), npc, 6, "npcBullet2");
			new Bullet(npc.getCentreX(), npc.getCentreY(), 0, npc, 4, "npcBullet2");// N
			new Bullet(npc.getCentreX(), npc.getCentreY(), Math.PI , npc, 5, "npcBullet2");// S
			new Bullet(npc.getCentreX(), npc.getCentreY(), Math.PI / 2 , npc, 6, "npcBullet2");// E
			new Bullet(npc.getCentreX(), npc.getCentreY(), 3 * Math.PI / 2, npc, 7, "npcBullet2");// W
		}
	}

	public Direction getRandomDir() {
		int dir = (int) (Math.random() * 8);
		return Direction.VALUES.get(dir);
	}
}
