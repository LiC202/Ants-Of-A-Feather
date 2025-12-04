import java.util.ArrayList;

public class RoomManager {
  private static ArrayList<Room> roomList = new ArrayList<>();
  
  public static void addRoom(Room room) {
    roomList.add(room);
  }
  
  public static ArrayList<Room> getAllRooms() {
    return roomList;
  }
  
  public static void setAllRooms(ArrayList<Room> map) {
    roomList = map;
  }
  
  public static Room getRoomFromString(String roomName) {
    for (Room r : roomList) {
      if (r.getName().equals(roomName))
        return r; 
    } 
    return null;
  }
}
