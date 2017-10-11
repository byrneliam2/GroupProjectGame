package npc;

import common.player.IPlayer;
import player.Player;

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
