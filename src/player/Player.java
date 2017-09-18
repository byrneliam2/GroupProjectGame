/**
 * Created by javahemohs on 19/09/17.
 */
public class Player {
    private String name;
    private List<Item> itemCollection = new ArrayList<Item>();
    private int power;
    private int health;


    public Player(String name){
        health = 5;
        power = 1;
    }


    public void updateFromCollection(){}
    public void DeleteFromCollection(){}
    public void AddToCollection(){}

    public boolean canOpenDoor(){return true;}
    public boolean canMakeMove(){return true;}

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
}