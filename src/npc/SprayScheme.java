package npc;

import java.io.Serializable;

import common.player.IPlayer;
import common.utils.Direction;
import player.Bullet;

/**
 * Spray Scheme is a scheme that extends Medium scheme, however the NPC will shoot sprays of bullets in all directions
 * at the given spray rate.
 *
 * @author Thomas Edwards
 *
 */
public class SprayScheme extends MediumScheme implements Serializable{

	private int sprayRate;

	/**
	 * @param sprayRate
	 *            how frequently the spray scheme shoots sprays of bullets. Smaller numbers are faster.
	 */
	public SprayScheme(int sprayRate) {
		this.sprayRate = sprayRate;
	}

	/**
	 * Decides whether it is time to shoot, if it is, then make a new spray of bullet.
	 *  @param npc
	 * @param player
     */
	@Override
	public void decideShooting(NPC npc, IPlayer player) {
		// shoot at the player when sprayRate is divisable and change direction every 200
		if (moveCounter > 200) {
			moveCounter = 0;
			randDir = Direction.getRandomDirection();//change direction.
		}
		if (moveCounter % sprayRate == 0) {
			double offset = Math.random() * Math.PI / 8;
			new Bullet(npc.getCentreX(), npc.getCentreY(), 0 + offset, npc, 4, "npcBullet1");// N
			new Bullet(npc.getCentreX(), npc.getCentreY(), Math.PI + offset, npc, 4, "npcBullet1");// S
			new Bullet(npc.getCentreX(), npc.getCentreY(), Math.PI / 2 + offset, npc, 4, "npcBullet1");// E
			new Bullet(npc.getCentreX(), npc.getCentreY(), 3 * Math.PI / 2 + offset, npc, 4, "npcBullet1");// W

			new Bullet(npc.getCentreX(), npc.getCentreY(), Math.PI / 4 + offset, npc, 4, "npcBullet1");// NE
			new Bullet(npc.getCentreX(), npc.getCentreY(), 3 * Math.PI / 4 + offset, npc, 4, "npcBullet1");// SE
			new Bullet(npc.getCentreX(), npc.getCentreY(), 5 * Math.PI / 4 + offset, npc, 4, "npcBullet1");// SW
			new Bullet(npc.getCentreX(), npc.getCentreY(), 7 * Math.PI / 4 + offset, npc, 4, "npcBullet1");// NW
		}
	}

}
