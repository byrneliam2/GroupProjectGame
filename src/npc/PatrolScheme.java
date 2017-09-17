package npc;

/**
 * A scheme which causes the NPC to patrol left-right or up-down.
 * 
 * @author Thomas Edwards
 *
 */
public class PatrolScheme implements ControlScheme {

	/**
	 * @param leftRight
	 *            whether to patrol first right then left (true) or first down then up (false).
	 * @param patrolDistance
	 *            amount of 'map blocks' the patrol moves by.
	 */
	public PatrolScheme(boolean leftRight, int patrolDistance) {
	}

	@Override
	public void doBestAction(Player p, NPC npc) {
		
	}

}
