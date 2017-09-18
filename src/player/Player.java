package player;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;

import items.Backpack;
import items.DoorItem;



/**
 * Created by javahemohs on 19/09/17.
 */
public class Player {
    private String name;
    private Backpack itemsList = new Backpack(this);
    private int health;
    private int xLocation;
    private int yLocation;
    private Map map;
    private List<Bullets> bullets = new ArrayList<Bullets>();


    public Player(String name, int xLocation, int yLocation){
        this.name = name;
        this.health = 5;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
     

    }





	public void pickUpItems(){
    	if(true) {

    	}

    }
    public void removeItems(){}
    public void equipItem(){}
    public void unequipItem() {}
    public void useItem() {}
    public void pickUpAndUse() {}

    public boolean canOpenDoor(DoorItem doorItem){
    	if(itemsList.checkDoorID(doorItem.getDoorID())) {
    		return true;
    	}
    	return false;
    }

    private boolean canMakeMove(){
    	// Ask the map if it possible to move (((Later)))
    	if(true) {
    		return true;
    	}
    	return false;
    }

    public void move() {
    	//Check if you can make the move and then update the x and y.

    }

    public void shoot() {
    	// Make
    }




















    /*
    * Returns a String
    */
    public String getName(){
        return this.name;
    }

    /*
    * Sets the name of
    */
    public void setName(String name){
        this.name = name;
    }

    public Backpack getItemsList() {
		return itemsList;
	}


	public void setItemsList(Backpack itemsList) {
		this.itemsList = itemsList;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public int getxLocation() {
		return xLocation;
	}


	public int getyLocation() {
		return yLocation;
	}


}