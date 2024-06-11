package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RoomManager {

    private static final Logger logger = LogManager.getLogger(RoomManager.class);

    private List<Room> rooms;

    public RoomManager(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
        logger.info("Room added: " + room);
    }

    public void editRoom(int roomNumber, Room newRoom) {
        Optional<Room> roomOptional = rooms.stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst();
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            room.setRoomClass(newRoom.getRoomClass());
            room.setNumberOfPersons(newRoom.getNumberOfPersons());
            room.setPricePerNight(newRoom.getPricePerNight());
            logger.info("Room edited: " + room);
        } else {
            logger.warn("Room not found: " + roomNumber);
        }
    }

    public void deleteRoom(int roomNumber) {
        rooms.removeIf(room -> room.getRoomNumber() == roomNumber);
        logger.info("Room deleted: " + roomNumber);
    }

    public void sortByVisitors() {
        rooms.sort(Comparator.comparingInt(Room::getNumberOfPersons));
        logger.info("Rooms sorted by number of visitors.");
    }

    public void sortByPrice() {
        rooms.sort(Comparator.comparingInt(Room::getPricePerNight));
        logger.info("Rooms sorted by price.");
    }

    public Room findRoomById(int roomNumber) {
        Room room = rooms.stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst().orElse(null);
        if (room != null) {
            logger.info("Room found: " + room);
        } else {
            logger.warn("Room not found: " + roomNumber);
        }
        return room;
    }

    public void saveToTextFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Room room : rooms) {
                writer.write(room.getRoomNumber() + "," + room.getRoomClass() + "," +
                        room.getNumberOfPersons() + "," + room.getPricePerNight());
                writer.newLine();
            }
            logger.info("Data saved to text file: " + fileName);
        } catch (IOException e) {
            logger.error("Error saving to text file: " + fileName, e);
        }
    }

    public void loadFromTextFile(String fileName) {
        rooms = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int roomNumber = Integer.parseInt(parts[0]);
                Room.RoomClass roomClass = Room.RoomClass.valueOf(parts[1]);
                int numberOfPersons = Integer.parseInt(parts[2]);
                int pricePerNight = Integer.parseInt(parts[3]);
                rooms.add(new Room(roomNumber, roomClass, numberOfPersons, pricePerNight));
            }
            logger.info("Data loaded from text file: " + fileName);
        } catch (IOException e) {
            logger.error("Error loading from text file: " + fileName, e);
        }
    }
}