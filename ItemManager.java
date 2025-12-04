import java.util.ArrayList;

public class ItemManager {
  private static final ArrayList<Item> itemList = new ArrayList<>();
  
  public static void addItem(Item item) {
    itemList.add(item);
  }
  
  public static Item getItemFromString(String itemName) {
    for (Item i : itemList) {
      if (i.getName().equals(itemName))
        return i; 
    } 
    return new Item("null", "(couldn't find item)");
  }
  
  public static boolean isItem(String itemName) {
    for (Item i : itemList) {
      if (i.getName().equals(itemName))
        return true; 
    } 
    return false;
  }
}
