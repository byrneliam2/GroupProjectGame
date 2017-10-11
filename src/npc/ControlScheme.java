package npc;

import java.io.Serializable;

import player.Player;

public interface ControlScheme{

	/**
	 * Decides and does the best action.
	 *
	 * @param npc
	 *            the npc which carries out the action.
	 */
	public void doBestAction(NPC npc, Player player);

}
