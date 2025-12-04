import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
  private Room currentRoom;
  
  private ArrayList<Item> inventory;
  
  public Player(Room startingRoom) {
    this.currentRoom = startingRoom;
    this.inventory = new ArrayList<>();
  }
  
  public Room getCurrentRoom() {
    return this.currentRoom;
  }
  
  public void setCurrentRoom(Room room) {
    this.currentRoom = room;
  }
  
  public void pickupItem(Item item) {
    this.inventory.add(item);
  }
  
  public void dropItem(Item item) {
    this.inventory.remove(item);
  }
  
  public boolean hasItem(Item item) {
    return this.inventory.contains(item);
  }
  
  public void consumeItem(Item item) {
    this.inventory.remove(item);
  }
  
  public boolean inventoryEmpty() {
    return this.inventory.isEmpty();
  }
  
  public ArrayList<Item> getInventory() {
    return this.inventory;
  }
  
  public void setInventory(ArrayList<Item> inventory) {
    this.inventory = inventory;
  }
  
  public ArrayList<String> getInventoryList() {
    ArrayList<String> inventoryString = new ArrayList<>();
    if (this.inventory.isEmpty()) {
      inventoryString.add("Your inventory is empty.");
      return inventoryString;
    } 
    inventoryString.add("\nYour inventory:");
    for (Item item : this.inventory)
      inventoryString.add("- " + item.getName() + ", " + item.getDescription()); 
    return inventoryString;
  }
}
