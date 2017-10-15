package common.map;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.List;

import common.items.Item;
import items.DoorItem;
import npc.NPC;
import player.Bullet;

/**
 * This is an abstract Map interface which describes all the functionality a map should
 * have.
 *
 * @author James
 */
public interface IMap {
    /**
     * This method pauses all the given NPCs on a map
     */
    public void pauseMapNPCs();

    /**
     * This method "starts" all NPCs on a map. Starting means allowing all the NPCs
     * to continue their routine or scheme.
     */
    public void startMapNPCs();

    /**
     * This method removes a given NPC from a map
     *
     * @param toBeRemoved
     */
    public void removeNPC(NPC toBeRemoved);

    /**
     * This method removed a bullet from a map
     *
     * @param b
     * @return
     */
    public boolean checkBulletHit(Bullet b);

    /**
     * This method returns any doors that are located in a given bounding box,
     * or null if there are no doors.
     *
     * @param boundingBox
     * @return
     */
    public DoorItem getDoor(Rectangle.Double boundingBox);

    /**
     * This method returns the item closest to the center of a given range circle
     *
     * @param rangeCircle
     * @return Item closest to center of circle
     */
    public Item getClosestItem(Ellipse2D rangeCircle);

    /**
     * This method places a given Item onto the map at spot x and y
     *
     * @param item
     * @param x    position
     * @param y    position
     */
    public void placeItem(Item i, int x, int y);

    /**
     * This method removes the given item from the map
     *
     * @param item to remove
     * @return whether or not the item was removed
     */
    public boolean removeItem(Item i);

    /**
     * This method determines whether or not a spot x,y can be moved over
     *
     * @param x position
     * @param y position
     * @return whether or not you can move
     */
    public boolean canMove(int x, int y);

    /**
     * This method determines whether a rectangle r is a valid one on the map. valid
     * being that all points in the rectangle can be moved over.
     *
     * @param r
     * @return
     */
    public boolean canMove(Rectangle.Double r);

    /**
     * Returns the file path of the backgroudnd image for this map
     *
     * @return
     */
    public String getBackgroundLayer();

    /**
     * Returns all the items on this map
     *
     * @return
     */
    public List<Item> getItems();

    /**
     * This method determines whether a spot x,y on the map has an environment
     *
     * @param x
     * @param y
     * @return The environment represented by a enum.
     */
    public Environment onEnvironmentTile(int x, int y);

    /**
     * This method returns all the NPC's on this map
     *
     * @return A list of NPC's
     */
    public List<NPC> getNPCs();

    /**
     * This method returns the door on this map with the id doorID, or returns null
     * if there is no door with id doorID
     *
     * @param doorID
     * @return The door with id doorID
     */
    public DoorItem getDoor(int doorID);

    /**
     * The name of this map
     *
     * @return
     */
    public String getName();
    
    public IWorld getWorld();

}
