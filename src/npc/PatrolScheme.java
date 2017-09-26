package npc;

import map.Map;
import player.InvalidPlayerExceptions;
import player.Player;

/**
 * A scheme which causes the NPC to patrol left-right or up-down. The NPC will
 * walk the specified distance in the specified direction, then will walk back
 * continuously walking through the loop.
 *
 * @author Thomas Edwards
 *
 */
public class PatrolScheme implements ControlScheme {

	private int progress = 0;// in pixels
	private final int maxProgress;// in pixels

	private final boolean leftRight;
	private boolean goingBack = false;
	private int shotCounter = 0;

	/**
	 * @param leftRight
	 *            whether to patrol first right then left (true) or first down then
	 *            up (false).
	 * @param patrolDistance
	 *            amount of 'map blocks' the patrol moves by.
	 */
	public PatrolScheme(boolean leftRight, int patrolDistance) {
		maxProgress = patrolDistance * Map.tileSize;
		this.leftRight = leftRight;
	}

	@Override
	public void doBestAction(NPC npc, Player player) {
		try {
			// move the npc according to the patrol direction.
			if (leftRight) {
				if (!goingBack) {
					progress++;
					if (progress >= maxProgress)
						goingBack = true;
					npc.move(1, 0);
				} else {
					progress--;
					if (progress <= 0)
						goingBack = false;
					npc.move(-1, 0);
				}
			} else {
				if (!goingBack) {
					progress++;
					if (progress >= maxProgress)
						goingBack = true;
					npc.move(0, 1);
				} else {
					progress--;
					if (progress <= 0)
						goingBack = false;
					npc.move(0, -1);

				}
			}

			// shoot at the player every 100 moves
			shotCounter++;
			if (shotCounter >= 100) {
				npc.shoot(npc.getPlayer().getxLocation(), npc.getPlayer().getyLocation());// shoots at the player.
				shotCounter = 0;
			}
		} catch (InvalidPlayerExceptions e) {
			System.out.println("Patrol ai wasn't able to do a move, it caught the exception thrown tho so its algood!"
					+ e.getMessage());
		}
	}

}
