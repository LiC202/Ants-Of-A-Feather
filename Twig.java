import java.io.Serializable;

public class Twig extends Item implements Usable, Serializable {
  public Twig(String name, String description) {
    super(name, description);
  }
  
  public String use(Room currentRoom) {
    if (!currentRoom.getName().equals("dam1") && !currentRoom.getName().equals("dam2") && !currentRoom.getName().equals("dam3"))
      return "[" + this.name + "] cannot be used here."; 
    if (currentRoom.getName().equals("dam1") && this.name.equals("large-twig")) {
      Dam.plugGap(1);
      RoomManager.getRoomFromString("dam1").setDialogue("There are no gaps here.");
      return "Used [" + this.name + "].";
    } 
    if (currentRoom.getName().equals("dam2") && this.name.equals("small-twig")) {
      Dam.plugGap(2);
      RoomManager.getRoomFromString("dam2").setDialogue("There are no gaps here.");
      return "Used [" + this.name + "].";
    } 
    if (currentRoom.getName().equals("dam3") && this.name.equals("medium-twig")) {
      Dam.plugGap(3);
      RoomManager.getRoomFromString("dam3").setDialogue("There are no gaps here.");
      return "Used [" + this.name + "].";
    } 
    return "[" + this.name + "] doesn't fit here.";
  }
}
