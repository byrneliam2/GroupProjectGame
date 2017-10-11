package common.player;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import common.map.IMap;
import common.utils.Direction;
import items.Backpack;
import map.Map;
import player.InvalidPlayerExceptions;

import java.awt.*;

public interface IPlayer {

	IMap getMap();

	boolean move(double x, double y) throws InvalidPlayerExceptions;

	void pickUpItem() throws InvalidPlayerExceptions;

	void shoot(double mouseX, double mouseY) throws InvalidPlayerExceptions;

	boolean isDead();

	int getxLocation();

	int getyLocation();

	int getHealth();

	Backpack getBackpack();

	void setMap(Map map);

	Rectangle.Double getBoundingBox();

	void takeDamage();

	int getCentreX();

	int getCentreY();

	int getSpeed();

	public Direction getCurrentDir();

	public void setCurrentDir(Direction newDir);
	
	public boolean isMoving();

	public void setMoving(boolean isMoving) ;
}
