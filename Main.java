package org.example;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, Room.RoomClass.LUXURY, 2, 150));
        rooms.add(new Room(102, Room.RoomClass.STANDARD, 2, 100));

        RoomManager roomManager = new RoomManager(rooms);

        String textFileName = "rooms.txt";

        roomManager.saveToTextFile(textFileName);
        System.out.println("Rooms saved to text file: " + textFileName);

        roomManager.loadFromTextFile(textFileName);
        System.out.println("Rooms loaded from text file: " + textFileName);

        List<Room> loadedRooms = roomManager.getRooms();
        for (Room room : loadedRooms) {
            System.out.println(room);
        }
    }
}