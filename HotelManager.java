package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class HotelManager {
    private List<Room> rooms;

    public HotelManager() {
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void updateRoom(int roomNumber, Room updatedRoom) {
        Optional<Room> roomOpt = findRoomByNumber(roomNumber);
        roomOpt.ifPresent(room -> {
            room.setRoomNumber(updatedRoom.getRoomNumber());
            room.setRoomClass(updatedRoom.getRoomClass());
            room.setNumberOfPersons(updatedRoom.getNumberOfPersons());
            room.setPricePerNight(updatedRoom.getPricePerNight());
        });
    }

    public void deleteRoom(int roomNumber) {
        rooms.removeIf(room -> room.getRoomNumber() == roomNumber);
    }
    public Optional<Room> findRoomByNumber(int roomNumber) {
        return rooms.stream().filter(room -> room.getRoomNumber() == roomNumber).findFirst();
    }
    public void sortByNumberOfPersons() {
        rooms.sort(Comparator.comparingInt(Room::getNumberOfPersons));
    }
    public void sortByPrice() {
        rooms.sort(Comparator.comparingInt(Room::getPricePerNight));
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
