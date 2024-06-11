package org.example;
public class Room {
    public enum RoomClass {
        LUXURY, STANDARD, ECONOMY
    }

    private int roomNumber;
    private RoomClass roomClass;
    private int numberOfPersons;
    private int pricePerNight;

    public Room(int roomNumber, RoomClass roomClass, int numberOfPersons, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomClass = roomClass;
        this.numberOfPersons = numberOfPersons;
        this.pricePerNight = pricePerNight;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomClass=" + roomClass +
                ", numberOfPersons=" + numberOfPersons +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}

