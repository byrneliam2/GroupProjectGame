package npc;

import java.io.Serializable;

import common.player.IPlayer;
import map.Map;

/**
 * @author Thomas Edwards
 */
public class BossNPC extends NPC implements Serializable{

	public BossNPC(IPlayer mainPlayer) {
		super("Boss", 500, 400, 20, mainPlayer, new BossScheme());
		super.setBoundingBoxWidth(Map.tileSize * 2 - 6, Map.tileSize * 2 - 6);
	}

}
