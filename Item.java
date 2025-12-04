import java.io.Serializable;

public class Item implements Serializable {
  protected String description;
  
  protected String name;
  
  public Item(String name, String description) {
    this.name = name;
    this.description = description;
  }
  
  public String use(Room currentRoom) {
    return "[" + this.name + "] cannot be used.";
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getName() {
    return this.name;
  }
}
