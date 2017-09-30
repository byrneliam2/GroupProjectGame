package npc;

import player.InvalidPlayerExceptions;
import player.Player;

public class SuicidalScheme implements ControlScheme {
	private utils.Direction[] directions = new utils.Direction[8];

	public SuicidalScheme() {
	}

	@Override
	public void doBestAction(NPC npc, Player player) {
		fillDirections(npc, player);
		int i = 0;
		while (true) {
			try {
				npc.move(directions[i].getX(), directions[i].getY());
				break;
			} catch (InvalidPlayerExceptions e) {
				i++;
			}
		}
	}

	void fillDirections(NPC npc, Player player) {
		double distX = npc.getCentreX() - player.getCentreX();
		double distY = npc.getCentreY() - player.getCentreY();
		//figure out first 4, then place opposites in the other direction.
		
		if (distX > 0 && distY >= 0) {// move towards top left corner
			//need to figure out whether between N- NW or NW- W
				//need to figure out which of the two you are closer too.

		} else if (distX <= 0 && distY > 0) {// top right

		} else if (distX < 0 && distY <= 0) {// bottom right

		} else {// bottom left

		}
	}
}
