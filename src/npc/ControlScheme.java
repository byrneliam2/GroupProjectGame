package npc;

import common.player.IPlayer;
import player.Player;

/**
 * A control scheme controls what the NPC will do. It has one method
 * doBestAction which is called every npc update and does the best action for
 * the npc.
 *
 * @author Thomas Edwards
 */
public interface ControlScheme {

	/**
	 * Decides and does the best action.
	 *
	 * @param npc
	 *            the npc which carries out the action.
	 * @param player
	 */
	public void doBestAction(NPC npc, IPlayer player);

}
