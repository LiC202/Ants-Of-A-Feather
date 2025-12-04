import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room implements Serializable {
  private final String name;
  
  private final String description;
  
  private String dialogue;
  
  private final Map<String, String> exits;
  
  private ArrayList<Item> itemList;
  
  public Room(String name, String description, String dialogue) {
    this.name = name;
    this.description = description;
    this.dialogue = dialogue;
    this.itemList = new ArrayList<>();
    this.exits = new HashMap<>();
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return "You are " + this.description + ".\n";
  }
  
  public void setDialogue(String dialogue) {
    this.dialogue = dialogue;
  }
  
  public String getDialogue() {
    return this.dialogue + "\n";
  }
  
  public void setExit(String direction, String neighbor) {
    this.exits.put(direction, neighbor);
  }
  
  public void removeExit(String direction) {
    this.exits.remove(direction);
  }
  
  public Room getExit(String direction) {
    return RoomManager.getRoomFromString(this.exits.get(direction));
  }
  
  public void addItem(Item item) {
    this.itemList.add(item);
  }
  
  public void deleteItem(Item item) {
    this.itemList.remove(item);
  }
  
  public boolean hasItem(Item item) {
    return this.itemList.contains(item);
  }
  
  public ArrayList<Item> getItems() {
    return this.itemList;
  }
  
  public void setItems(ArrayList<Item> itemList) {
    this.itemList = itemList;
  }
  
  public String getExitString() {
    StringBuilder sb = new StringBuilder();
    for (String direction : this.exits.keySet())
      sb.append(direction).append(" "); 
    return "Exits: " + sb.toString().trim();
  }
  
  public boolean isEmpty() {
    return this.itemList.isEmpty();
  }
}
