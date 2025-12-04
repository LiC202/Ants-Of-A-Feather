import java.io.Serializable;
import java.util.ArrayList;

public class SaveState implements Serializable {
  public ArrayList<Item> inventory;
  
  public Room playerCurrentRoom;
  
  public ArrayList<Room> map;
  
  public int gameState;
  
  public boolean[] damGaps;
}
