package common.mocks;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import common.items.Item;
import common.map.Environment;
import common.map.IMap;
import common.map.IWorld;
import items.DoorItem;
import npc.NPC;
import player.Bullet;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;

public class MockMap implements IMap {
    @Override
    public void pauseMapNPCs() {

    }

    @Override
    public void startMapNPCs() {

    }

    @Override
    public void removeNPC(NPC toBeRemoved) {

    }

    @Override
    public boolean checkBulletHit(Bullet b) {
        return false;
    }

    @Override
    public Item getClosestItem(Ellipse2D rangeCircle) {
        return null;
    }

    @Override
    public void placeItem(Item i, int x, int y) {

    }

    @Override
    public boolean removeItem(Item i) {
        return false;
    }

    @Override
    public boolean canMove(int x, int y) {
        return false;
    }

    @Override
    public String getBackgroundLayer() {
        return null;
    }

    @Override
    public List<Item> getItems() {
        return null;
    }

    @Override
    public Environment onEnvironmentTile(int x, int y) {
        return null;
    }

    @Override
    public List<NPC> getNPCs() {
        return null;
    }

    @Override
    public DoorItem getDoor(int doorID) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean canMove(Rectangle.Double r) {
        return false;
    }

    @Override
    public DoorItem getDoor(Rectangle.Double boundingBox) {
        return null;
    }

	@Override
	public IWorld getWorld() {
		// TODO Auto-generated method stub
		return null;
	}
}
