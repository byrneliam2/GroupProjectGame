package npc;

<<<<<<< HEAD
import java.io.Serializable;

=======
import common.player.IPlayer;
>>>>>>> 4276775ecaf2233d5d939e80508f781d1b738d9d
import player.Player;

public interface ControlScheme{

	/**
	 * Decides and does the best action.
	 *
<<<<<<< HEAD
	 * @param npc
	 *            the npc which carries out the action.
	 */
	public void doBestAction(NPC npc, Player player);
=======
     * @param npc
     *            the npc which carries out the action.
     * @param player
     */
	public void doBestAction(NPC npc, IPlayer player);
>>>>>>> 4276775ecaf2233d5d939e80508f781d1b738d9d

}
