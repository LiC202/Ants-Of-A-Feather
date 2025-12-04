import java.io.Serializable;

public class Leaf extends Item implements Usable, Serializable {
  public Leaf(String name, String description) {
    super(name, description);
  }
  
  public String use(Room currentRoom) {
    return !currentRoom.getName().equals("fungus-farm") ? ("[" + 
      this.name + "] cannot be used here.") : ("Used [" + 
      
      this.name + "].");
  }
}
