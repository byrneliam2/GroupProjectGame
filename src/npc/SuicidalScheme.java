package npc;

import java.io.Serializable;

import common.player.IPlayer;
import map.Map;
import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;
import common.utils.MathUtils;

/**
 * A scheme where the npc move towards the player and when its close, shoots the player very quickly.
 *
 * @author Thomas Edwards
 *
 */
public class SuicidalScheme implements ControlScheme, Serializable{
	private double moveX, moveY, angle;
	private int moveCount = 60;

	public SuicidalScheme() {
	}

	@Override
	public void doBestAction(NPC npc, IPlayer player) {
		if (moveCount >= 30) {
			chooseBestDir(npc, player);
			moveCount = 0;
		}
		try {
			npc.move(moveX, moveY);
			moveCount++;
			if (MathUtils.getDistance(npc.getCentreX(), npc.getCentreY(), player.getCentreX(),
					player.getCentreY()) < Map.tileSize * 3 && moveCount % 8 == 0) {
				new Bullet(npc.getCentreX(), npc.getCentreY(), MathUtils.calculateAngle(npc.getxLocation(),
						npc.getyLocation(), player.getCentreX(), player.getCentreY()), npc, 10, "npcBullet2");
			}
		} catch (InvalidPlayerExceptions e) {
			chooseSideDir();
			moveCount = 40;
		}
	}

	private void chooseSideDir() {
		double number = Math.random() > 0.5 ? Math.PI / 2 : -Math.PI / 2;
		angle = angle + number;
		moveX = Math.sin(angle) * 6;
		moveY = -Math.cos(angle) * 6;
		angle = angle - number;
	}

	private void chooseBestDir(NPC npc, IPlayer player) {
		angle = MathUtils.calculateAngle(npc.getCentreX(), npc.getCentreY(), player.getCentreX(), player.getCentreY());

		moveX = Math.sin(angle) * 7;
		moveY = -Math.cos(angle) * 7;
	}
}
