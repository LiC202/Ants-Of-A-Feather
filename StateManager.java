import java.util.ArrayList;

public class StateManager {
  private static final ArrayList<String> states = new ArrayList<>();
  
  private static int gameState = 0;
  
  public static void init() {
    states.add("spawn");
    states.add("spoke to queen");
    states.add("got first leaf");
    states.add("spoke to beetle");
    states.add("fixed dam");
    states.add("spoke to beetle again");
    states.add("got second leaf");
    states.add("spoke to spider");
    states.add("solved maze");
    states.add("spoke to spider again");
    states.add("got third leaf");
    states.add("spoke to queen again");
    states.add("complete");
  }
  
  public static String getGameState() {
    return states.get(gameState);
  }
  
  public static int getGameStateInt() {
    return gameState;
  }
  
  public static void nextGameState() {
    gameState++;
    switch ((String)states.get(gameState)) {
      case "spoke to queen":
        RoomManager.getRoomFromString("throne-room").setDialogue("You see the queen.\n << What are you waiting for? >>\nYou decide to leave.");
        break;
      case "got first leaf":
        RoomManager.getRoomFromString("branch").setDialogue("The branch is still full of leaves.");
        RoomManager.getRoomFromString("tree-trunk").setDialogue("The tree stands tall and imposing.");
        RoomManager.getRoomFromString("dirt-path").setDialogue("The dirt path feels familiar and safe.");
        RoomManager.getRoomFromString("the-hill").setDialogue("The hill is high and formidable");
        RoomManager.getRoomFromString("clearing").setDialogue("In the clearing you see a beetle, it calls you over.\n << Can you help me?\n    You see, I built this dam, but it has so many holes that it doesn't work!\n    There are some twigs nearby, but I'm not strong enough to lift them.\n    Will you patch up the gaps? >>\nYou decide to help the beetle, but you can't do it while holding your leaf, so you make sure the leave it on the ground first.\nYou head east towards the dam.");
        RoomManager.getRoomFromString("clearing").setExit("east", "river");
        break;
      case "spoke to beetle":
        RoomManager.getRoomFromString("clearing").setDialogue("You should head east to help the beetle with his dam.");
        break;
      case "fixed dam":
        RoomManager.getRoomFromString("river").setDialogue("You're on the banks of the river, by the beetle's dam.");
        RoomManager.getRoomFromString("clearing").setDialogue("You see the beetle again.\n << Thank you for helping me!\n    I'm sorry but while you were gone another ant passed by and took you leaf.\n    You might have to head back to the tree for another one... >>\nYou know you can't come back to the Queen empty handed, so left with no other choice, you decide it's best to turn around.");
        RoomManager.getRoomFromString("clearing").deleteItem(ItemManager.getItemFromString("leaf"));
        RoomManager.getRoomFromString("branch").addItem(ItemManager.getItemFromString("leaf"));
        break;
      case "spoke to beetle again":
        RoomManager.getRoomFromString("clearing").setDialogue("The clearing is vast and empty.");
        break;
      case "got second leaf":
        RoomManager.getRoomFromString("dirt-path").setDialogue("On the dirt path you run into a spider mama in distress.\n << Will you help me find my baby?\n    He wandered off on his own into that hedge maze and I just can't find him.\n    He's very wriggly, you might have a hard time catching him carrying that leaf, leave it with me\n    while you search. >>\nYou decide to help the poor spider before delivering your leaf.\nYou head north towards the maze.");
        RoomManager.getRoomFromString("clearing").removeExit("east");
        RoomManager.getRoomFromString("dirt-path").setExit("north", "winding-trail");
        break;
      case "spoke to spider":
        RoomManager.getRoomFromString("dirt-path").setDialogue("You should head north from here to find the baby spider.");
        break;
      case "solved maze":
        RoomManager.getRoomFromString("maze12").setDialogue("This appears to be a dead end.");
        RoomManager.getRoomFromString("dirt-path").setDialogue("You see the spider again.\n << Thank you for finding my baby!\n    I'm sorry but while you were gone a bird flew down and took your leaf for its nest.\n    It was too big for me to stop it, you'll have to find another one... >>\nSo you decide to head back to the tree again.");
        RoomManager.getRoomFromString("dirt-path").deleteItem(ItemManager.getItemFromString("leaf"));
        RoomManager.getRoomFromString("branch").addItem(ItemManager.getItemFromString("leaf"));
        break;
      case "spoke to spider again":
        RoomManager.getRoomFromString("dirt-path").setDialogue("The dirt path feels familiar and safe.");
        break;
      case "got third leaf":
        RoomManager.getRoomFromString("throne-room").setDialogue("The Queen has been waiting for you.\n << Ah, you've finally returned.\n    Go to the farm immediately to complete your life's work.");
        RoomManager.getRoomFromString("fungus-farm").setDialogue("You use your leaf here to fertilise the soil.");
        RoomManager.getRoomFromString("dirt-path").removeExit("north");
        RoomManager.getRoomFromString("dirt-path").deleteItem(ItemManager.getItemFromString("baby-spider"));
        break;
      case "spoke to queen again":
        RoomManager.getRoomFromString("throne-room").setDialogue(" << What are you still doing here? >>");
        RoomManager.getRoomFromString("throne-room").removeExit("up");
        break;
      case "complete":
        RoomManager.getRoomFromString("throne-room").setDialogue("The Queen looks proud of you.\nYou can live out the rest of your days peacefully in the ant hill.");
        break;
    } 
  }
}
