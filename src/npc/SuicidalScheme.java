package npc;

import map.Map;
import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;
import utils.Direction;
import utils.MathUtils;

public class SuicidalScheme implements ControlScheme {
	private double moveX, moveY, angle;
	private int moveCount = 60;

	public SuicidalScheme() {
	}

	@Override
	public void doBestAction(NPC npc, Player player) {
		if (moveCount >= 60) {
			chooseBestDir(npc, player);
			moveCount = 0;
		}
		try {
			npc.move(moveX, moveY);
			moveCount++;
			if (MathUtils.getDistance(npc.getCentreX(), npc.getCentreY(), player.getCentreX(),
					player.getCentreY()) < Map.tileSize * 3 && moveCount % 10 == 0) {
				new Bullet(
						npc.getxLocation(), npc.getyLocation(), MathUtils.calculateAngle(npc.getxLocation(),
								npc.getyLocation(), player.getxLocation(), player.getyLocation()),
						npc, 4, "npcBullet2");
			}
		} catch (InvalidPlayerExceptions e) {
			chooseSideDir();
			moveCount = 40;
		}
	}

	private void chooseSideDir() {
		double number = Math.random() > 0.5 ? Math.PI / 2 : -Math.PI / 2;
		angle = angle + number;
		moveX = Math.sin(angle) * 4;
		moveY = -Math.cos(angle) * 4;
		angle = angle - number;
	}

	private void chooseBestDir(NPC npc, Player player) {
		angle = MathUtils.calculateAngle(npc.getCentreX(), npc.getCentreY(), player.getCentreX(), player.getCentreY());

		moveX = Math.sin(angle) * 5;
		moveY = -Math.cos(angle) * 5;
	}
}
