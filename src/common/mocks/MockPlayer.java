package common.mocks;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import common.map.IMap;
import common.player.IPlayer;
import common.utils.Direction;
import items.Backpack;
import map.Map;
import player.InvalidPlayerExceptions;

import java.awt.*;

public class MockPlayer implements IPlayer {
    @Override
    public IMap getMap() {
        return null;
    }

    @Override
    public boolean move(double x, double y) throws InvalidPlayerExceptions {
        return false;
    }

    @Override
    public void pickUpItem() throws InvalidPlayerExceptions {

    }

    @Override
    public void shoot(double mouseX, double mouseY) throws InvalidPlayerExceptions {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public int getxLocation() {
        return 0;
    }

    @Override
    public int getyLocation() {
        return 0;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public Backpack getBackpack() {
        return null;
    }

    @Override
    public void setMap(Map map) {

    }

    @Override
    public Rectangle.Double getBoundingBox() {
        return null;
    }

    @Override
    public void takeDamage() {

    }

    @Override
    public int getCentreX() {
        return 0;
    }

    @Override
    public int getCentreY() {
        return 0;
    }

    @Override
    public int getSpeed() {
        return 0;
    }

	@Override
	public Direction getCurrentDir() {
		
		return null;
	}

	@Override
	public void setCurrentDir(Direction newDir) {
	}

	@Override
	public boolean isMoving() {
		
		return false;
	}

	@Override
	public void setMoving(boolean isMoving) {
	}

	@Override
	public void specialAbility(double mouseX, double mouseY) throws InvalidPlayerExceptions {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 0;
	}
}
