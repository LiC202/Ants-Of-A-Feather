import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.SwingUtilities;

public class Game {
  private GameGUI gui;
  
  private Player player;
  
  public Game() {
    StateManager.init();
    createWorld();
  }
  
  public static void main(String[] args) {
    Game game = new Game();
    SwingUtilities.invokeLater(() -> {
          GameGUI gui = new GameGUI(game);
          game.setGui(gui);
          game.play();
        });
  }
  
  public void setGui(GameGUI gui) {
    this.gui = gui;
  }
  
  public void play() {
    this.gui.appendText("\nWelcome to Ants of a Feather!");
    printDivider();
    this.gui.appendText(this.player.getCurrentRoom().getDescription());
    this.gui.appendText(this.player.getCurrentRoom().getDialogue());
    this.gui.appendText(this.player.getCurrentRoom().getExitString());
  }
  
  private void createWorld() {
    Item leaf = new Leaf("leaf", "for fertiliser");
    Item lTwig = new Twig("large-twig", "for plugging dams");
    Item sTwig = new Twig("small-twig", "for walking");
    Item mTwig = new Twig("medium-twig", "for sword fights");
    Item babySpider = new Item("baby-spider", "mama spider's precious child");
    ItemManager.addItem(leaf);
    ItemManager.addItem(lTwig);
    ItemManager.addItem(sTwig);
    ItemManager.addItem(mTwig);
    ItemManager.addItem(babySpider);
    Room nursery = new Room("nursery", "in the colony's nursery", "This is where the larvae are cared for.\nYou look around and see worker ants tending to your family, but today is your last day here.\nYou're all grown up. It's time to dedicate your life to serving the colony.\nYou think it's good idea to start by exploring beyond the rest of the ant hill.\nYou know north of the corridor lies the Queen's Chamber.\nGo there to receive your task when you're ready.");
    Room corridor = new Room("corridor", "in the main corridor", "There's a sign in the centre of the room.\n  North: Queen's Chamber\n  East: Fungus Farm\n  South: Nursery\n  South West: Egg Chamber\n  West: Barracks");
    Room eggChamber = new Room("egg-chamber", "in the colony's egg chamber", "The room is filled with yucky sticky ant eggs.\nYou think it'd be best to leave them alone.");
    Room fungusFarm = new Room("fungus-farm", "in the colony's fungus farm", "The floor is covered in leaves. This is the colony's primary fertiliser used to cultivate its food.\nYummy fungus...");
    Room soldierBarracks = new Room("soldier-barracks", "in the soldier ants' quarters", "These barracks are full of soldiers training their combat.\nDangerous work being a soldier, it's not for you.");
    Room throneRoom = new Room("throne-room", "in the Queen's throne room", "This is the colony's throne room.\nThe Queen is here to give you your life's mission.\n << My loyal subject,\n    You like all worker ants, are required to bring home one leaf\n    This leaf will be used to fertilise our frolicsome fungus fields, to provide food to the\n    colony.\n    Go up now and out of the hill, return with a leaf or face my unbridled fury. >>\nSo you do just that.");
    Room theHill = new Room("the-hill", "on top of the ant hill", "You step out into the wider world for the first time. It's big and scary.\nDespite this you know you must continue, the colony needs you to bring back a leaf.");
    Room dirt1 = new Room("dirt-path", "on a dirt path outside in the forest", "You walk along a path and you spot an ideal tree in the distance.");
    Room dirt2 = new Room("clearing", "in a clearing in the forest", "You keep walking and find that the tree is at the edge of a large clearing.\nYou have to walk across the clearing to get to the tree.");
    Room trunk = new Room("tree-trunk", "at the base of a large oak", "You finally reach the tree. Only a climb to go!");
    Room branch = new Room("branch", "on the branch of the oak tree", "You walk out onto a branch and are surrounded by leaves!\nAll you have to do now is pick one up and bring it home.");
    Room river = new Room("river", "on the banks of a river", "You're on the banks of the river, by the beetle's dam.\nThere's a pile of twigs of various sizes. In the pile you see one a small-twig, one medium-twig, and one large-twig.");
    Room d1 = new Room("dam1", "at the northern part of the dam", "There's a large gap here.");
    Room d2 = new Room("dam2", "at the eastern part of the dam", "There's a small gap here.");
    Room d3 = new Room("dam3", "at the southern part of the dam", "There's a medium gap here.");
    Room wTrail = new Room("winding-trail", "on a small winding trail", "You're on the path between the road and the hedge maze");
    Room m1 = new Room("maze1", "in the maze", "This appears to be a dead end.");
    Room m2 = new Room("maze2", "in the maze", "Keep looking.");
    Room m3 = new Room("maze3", "in the maze", "Keep looking.");
    Room m4 = new Room("maze4", "in the maze", "Keep looking.");
    Room m5 = new Room("maze5", "in the maze", "Keep looking.");
    Room m6 = new Room("maze6", "in the maze", "Keep looking.");
    Room m7 = new Room("maze7", "in the maze", "This appears to be a dead end.");
    Room m8 = new Room("maze8", "in the maze", "Keep looking.");
    Room m9 = new Room("maze9", "in the maze", "Keep looking.");
    Room m10 = new Room("maze10", "in the maze", "Keep looking.");
    Room m11 = new Room("maze11", "in the maze", "Keep looking.");
    Room m12 = new Room("maze12", "in the maze", "You found the baby-spider!\nPick him up and get out of here the way you came, if you can remember.");
    Room m13 = new Room("maze13", "in the maze", "Keep looking.");
    Room m14 = new Room("maze14", "in the maze", "Keep looking.");
    Room m15 = new Room("maze15", "in the maze", "Keep looking.");
    Room m16 = new Room("maze16", "in the maze", "Keep looking.");
    nursery.setExit("north", "corridor");
    nursery.setExit("west", "egg-chamber");
    corridor.setExit("south", "nursery");
    corridor.setExit("west", "soldier-barracks");
    corridor.setExit("east", "fungus-farm");
    corridor.setExit("north", "throne-room");
    eggChamber.setExit("east", "nursery");
    fungusFarm.setExit("west", "corridor");
    soldierBarracks.setExit("east", "corridor");
    throneRoom.setExit("south", "corridor");
    throneRoom.setExit("up", "the-hill");
    theHill.setExit("down", "throne-room");
    theHill.setExit("east", "dirt-path");
    dirt1.setExit("west", "the-hill");
    dirt1.setExit("south", "clearing");
    dirt2.setExit("north", "dirt-path");
    dirt2.setExit("south", "tree-trunk");
    trunk.setExit("north", "clearing");
    trunk.setExit("up", "branch");
    branch.setExit("down", "tree-trunk");
    river.setExit("west", "clearing");
    river.setExit("north", "dam1");
    river.setExit("east", "dam2");
    river.setExit("south", "dam3");
    d1.setExit("south", "river");
    d2.setExit("west", "river");
    d3.setExit("north", "river");
    wTrail.setExit("south", "dirt-path");
    wTrail.setExit("west", "maze8");
    m1.setExit("south", "maze5");
    m2.setExit("east", "maze3");
    m2.setExit("south", "maze6");
    m3.setExit("west", "maze2");
    m3.setExit("east", "maze4");
    m4.setExit("west", "maze3");
    m4.setExit("south", "maze8");
    m5.setExit("north", "maze1");
    m5.setExit("east", "maze6");
    m5.setExit("south", "maze9");
    m6.setExit("north", "maze2");
    m6.setExit("west", "maze5");
    m6.setExit("south", "maze10");
    m7.setExit("south", "maze11");
    m8.setExit("east", "winding-trail");
    m8.setExit("north", "maze4");
    m9.setExit("north", "maze5");
    m9.setExit("south", "maze13");
    m10.setExit("north", "maze6");
    m10.setExit("east", "maze11");
    m11.setExit("north", "maze7");
    m11.setExit("west", "maze10");
    m12.setExit("south", "maze16");
    m13.setExit("north", "maze9");
    m13.setExit("east", "maze14");
    m14.setExit("east", "maze15");
    m14.setExit("west", "maze13");
    m15.setExit("east", "maze16");
    m15.setExit("west", "maze14");
    m16.setExit("north", "maze12");
    m16.setExit("west", "maze15");
    branch.addItem(ItemManager.getItemFromString("leaf"));
    river.addItem(ItemManager.getItemFromString("large-twig"));
    river.addItem(ItemManager.getItemFromString("small-twig"));
    river.addItem(ItemManager.getItemFromString("medium-twig"));
    m12.addItem(ItemManager.getItemFromString("baby-spider"));
    RoomManager.addRoom(nursery);
    RoomManager.addRoom(corridor);
    RoomManager.addRoom(eggChamber);
    RoomManager.addRoom(fungusFarm);
    RoomManager.addRoom(soldierBarracks);
    RoomManager.addRoom(throneRoom);
    RoomManager.addRoom(theHill);
    RoomManager.addRoom(dirt1);
    RoomManager.addRoom(dirt2);
    RoomManager.addRoom(trunk);
    RoomManager.addRoom(branch);
    RoomManager.addRoom(river);
    RoomManager.addRoom(d1);
    RoomManager.addRoom(d2);
    RoomManager.addRoom(d3);
    RoomManager.addRoom(wTrail);
    RoomManager.addRoom(m1);
    RoomManager.addRoom(m2);
    RoomManager.addRoom(m3);
    RoomManager.addRoom(m4);
    RoomManager.addRoom(m5);
    RoomManager.addRoom(m6);
    RoomManager.addRoom(m7);
    RoomManager.addRoom(m8);
    RoomManager.addRoom(m9);
    RoomManager.addRoom(m10);
    RoomManager.addRoom(m11);
    RoomManager.addRoom(m12);
    RoomManager.addRoom(m13);
    RoomManager.addRoom(m14);
    RoomManager.addRoom(m15);
    RoomManager.addRoom(m16);
    this.player = new Player(RoomManager.getRoomFromString("nursery"));
  }
  
  public void processCommand(Command command) {
    String commandWord = command.getCommandWord();
    if (commandWord == null) {
      this.gui.appendText("I don't understand your command...");
      return;
    } 
    switch (commandWord) {
      case "go":
        go(command);
        return;
      case "inventory":
        checkInventory();
        return;
      case "use":
        use(command);
        return;
      case "pickup":
        pickup(command);
        return;
      case "drop":
        drop(command);
        return;
      case "save":
        save();
        return;
      case "load":
        load();
        return;
    } 
    this.gui.appendText("I don't know what you mean...");
  }
  
  private void go(Command command) {
    if (!command.hasSecondWord()) {
      this.gui.appendText("Go where?");
      return;
    } 
    String direction = command.getSecondWord();
    Room nextRoom = RoomManager.getRoomFromString(this.player.getCurrentRoom().getName()).getExit(direction);
    if (nextRoom == null) {
      this.gui.appendText("There is no door!");
      return;
    } 
    if (Set.<String>of("spoke to beetle", "spoke to spider").contains(StateManager.getGameState()) && this.player.hasItem(ItemManager.getItemFromString("leaf"))) {
      this.gui.appendText("You should put down the leaf before you go.");
      return;
    } 
    if (StateManager.getGameState().equals("solved maze") && !this.player.hasItem(ItemManager.getItemFromString("baby-spider"))) {
      this.gui.appendText("Looks like you're not carrying the baby spider.");
      return;
    } 
    if (StateManager.getGameState().equals("spoke to spider again") && this.player.hasItem(ItemManager.getItemFromString("baby-spider"))) {
      this.gui.appendText("You should leave the baby with mama spider before you go.");
      return;
    } 
    printDivider();
    this.player.setCurrentRoom(nextRoom);
    Room currentRoom = RoomManager.getRoomFromString(this.player.getCurrentRoom().getName());
    this.gui.appendText(currentRoom.getDescription());
    this.gui.appendText(currentRoom.getDialogue());
    if (currentRoom.getName().equals("throne-room") && (
      StateManager.getGameState().equals("spawn") || StateManager.getGameState().equals("got third leaf")))
      StateManager.nextGameState(); 
    if (currentRoom.getName().equals("clearing") && (
      StateManager.getGameState().equals("got first leaf") || StateManager.getGameState().equals("fixed dam")))
      StateManager.nextGameState(); 
    if (currentRoom.getName().equals("dirt-path") && (
      StateManager.getGameState().equals("got second leaf") || StateManager.getGameState().equals("solved maze")))
      StateManager.nextGameState(); 
    this.gui.appendText(currentRoom.getExitString());
  }
  
  private void printDivider() {
    this.gui.appendText("---------------------------------------------------------------------------------------------------\n");
  }
  
  public void checkInventory() {
    ArrayList<String> inventoryList = this.player.getInventoryList();
    for (String line : inventoryList)
      this.gui.appendText(line); 
  }
  
  public void use(Command command) {
    if (!command.hasSecondWord()) {
      this.gui.appendText("Use what?");
      return;
    } 
    String itemName = command.getSecondWord();
    if (!ItemManager.isItem(itemName)) {
      this.gui.appendText("That item does not exist.");
      return;
    } 
    Item item = ItemManager.getItemFromString(itemName);
    if (!this.player.hasItem(item)) {
      this.gui.appendText("You don't have this item.");
      return;
    } 
    String used = item.use(this.player.getCurrentRoom());
    this.gui.appendText(used);
    if (used.charAt(0) == 'U')
      this.player.consumeItem(item); 
    if (StateManager.getGameState().equals("spoke to beetle") && Dam.allGapsPlugged())
      StateManager.nextGameState(); 
    if (itemName.equals("leaf") && StateManager.getGameState().equals("spoke to queen again")) {
      printDivider();
      this.gui.appendText("You won the game!!!!!\nYou fulfilled your purpose as a leaf-cutter ant, providing food for the next generation of your kind.");
    } 
  }
  
  public void pickup(Command command) {
    if (!command.hasSecondWord()) {
      this.gui.appendText("Pickup what?");
      return;
    } 
    String itemName = command.getSecondWord();
    if (!ItemManager.isItem(itemName)) {
      this.gui.appendText("That item does not exist.");
      return;
    } 
    Item item = ItemManager.getItemFromString(itemName);
    if (!this.player.getCurrentRoom().hasItem(item)) {
      this.gui.appendText("That item isn't in the room.");
      return;
    } 
    this.player.pickupItem(item);
    this.player.getCurrentRoom().deleteItem(item);
    this.gui.appendText("[" + itemName + "] has been added to your inventory.");
    if (itemName.equals("leaf") && (StateManager.getGameState().equals("spoke to queen") || StateManager.getGameState().equals("spoke to beetle again") || StateManager.getGameState().equals("spoke to spider again")))
      StateManager.nextGameState(); 
    if (itemName.equals("baby-spider") && StateManager.getGameState().equals("spoke to spider"))
      StateManager.nextGameState(); 
  }
  
  public void drop(Command command) {
    if (!command.hasSecondWord()) {
      this.gui.appendText("Drop what?");
      return;
    } 
    String itemName = command.getSecondWord();
    if (!ItemManager.isItem(itemName)) {
      this.gui.appendText("That item does not exist.");
      return;
    } 
    Item item = ItemManager.getItemFromString(itemName);
    if (!this.player.hasItem(item)) {
      this.gui.appendText("You aren't holding that item.");
      return;
    } 
    this.player.dropItem(item);
    this.player.getCurrentRoom().addItem(item);
    this.gui.appendText("[" + itemName + "] has been dropped from your inventory.");
  }
  
  public void save() {
    SaveState saveState = new SaveState();
    saveState.inventory = this.player.getInventory();
    saveState.playerCurrentRoom = this.player.getCurrentRoom();
    saveState.map = RoomManager.getAllRooms();
    saveState.damGaps = Dam.getGapList();
    saveState.gameState = StateManager.getGameStateInt();
    try {
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("sav.dat"));
      try {
        out.writeObject(saveState);
        out.close();
      } catch (Throwable throwable) {
        try {
          out.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        } 
        throw throwable;
      } 
    } catch (IOException e) {
      this.gui.appendText("\n *** FAILED TO SAVE ***");
      e.printStackTrace();
      return;
    } 
    this.gui.appendText("\n *** SAVE SUCCESSFUL *** ");
  }
  
  public void load() {
    SaveState loadState;
    try {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream("sav.dat"));
      try {
        loadState = (SaveState)in.readObject();
        in.close();
      } catch (Throwable throwable) {
        try {
          in.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        } 
        throw throwable;
      } 
    } catch (IOException|ClassNotFoundException e) {
      this.gui.appendText("\n *** FAILED TO LOAD ***");
      e.printStackTrace();
      return;
    } 
    this.player.setCurrentRoom(loadState.playerCurrentRoom);
    this.player.setInventory(loadState.inventory);
    RoomManager.setAllRooms(loadState.map);
    Dam.setGapList(loadState.damGaps);
    while (loadState.gameState != StateManager.getGameStateInt() - 1)
      StateManager.nextGameState(); 
    ArrayList<Item> correctedInventory = new ArrayList<>();
    for (Item item : this.player.getInventory())
      correctedInventory.add(ItemManager.getItemFromString(item.getName())); 
    this.player.setInventory(correctedInventory);
    for (Room room : RoomManager.getAllRooms()) {
      ArrayList<Item> correctedRoomItems = new ArrayList<>();
      for (Item item : room.getItems())
        correctedRoomItems.add(ItemManager.getItemFromString(item.getName())); 
      room.setItems(correctedRoomItems);
    } 
    this.gui.appendText("\n *** LOAD SUCCESSFUL *** ");
    printDivider();
    this.gui.appendText(this.player.getCurrentRoom().getDescription());
    this.gui.appendText(this.player.getCurrentRoom().getDialogue());
    this.gui.appendText(this.player.getCurrentRoom().getExitString());
  }
  
  public Player getPlayer() {
    return this.player;
  }
}
