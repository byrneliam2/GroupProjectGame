package player;

public class Player {
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
    /*
    *@ Returns the name of this Player
    */
    public String getName(){
        return this.name;
    }

    /*
    *@ Sets the name of this Player to
    */
    public void setName(String name){
        this.name = name;
    }
}
}