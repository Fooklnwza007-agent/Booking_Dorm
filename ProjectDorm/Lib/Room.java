package Lib;

public class Room {
    private final int roomId;
    private final int pricePerYear;
    boolean isAvailable = true; // ห้องว่าง

public Room(int roomId,int pricePerYear){
this.roomId = roomId;
this.pricePerYear = pricePerYear;
}

public int getroomId(){
    return roomId;
}

public boolean isAvailable() {
    return isAvailable;
    }
}
