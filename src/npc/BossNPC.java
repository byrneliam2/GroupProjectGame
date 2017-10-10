package npc;

import common.player.IPlayer;
import map.Map;

public class BossNPC extends NPC {

	public BossNPC(IPlayer mainPlayer) {
		super("Boss", 500, 400, 20, mainPlayer, new BossScheme());
		super.setBoundingBoxWidth(Map.tileSize * 2 - 6, Map.tileSize * 2 - 6);
	}

}
