package Logic;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import Logic.Enums.Equipments;

public class Room {

    private int id;
    private School school;
    private int capacity;
    private int floor;
    private int number;
    private HashMap<Equipments, Boolean> equipments;
    private HashMap<HashMap<Date,Time>, Boolean> busyNow;
    public static int idCounter = 0;

    public Room(School school, int capacity, HashMap<Equipments, Boolean> equipments, HashMap<HashMap<Date, Time>, Boolean> busyNow, int floor, int number) {
        this.school = school;
        this.capacity = capacity;
        this.equipments = equipments;
        this.busyNow = busyNow;
        this.floor = floor;
        this.number = number;
        this.id = idCounter++;
    }

    public Room(School school, int capacity, int floor, int number) {
        this.school = school;
        this.capacity = capacity;
        this.floor = floor;
        this.number = number;
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public HashMap<Equipments, Boolean> getEquipments() {
        return equipments;
    }

    public void setEquipments(HashMap<Equipments, Boolean> equipments) {
        this.equipments = equipments;
    }

    public HashMap<HashMap<Date, Time>, Boolean> getBusyNow() {
        return busyNow;
    }

    public void setBusyNow(HashMap<HashMap<Date, Time>, Boolean> busyNow) {
        this.busyNow = busyNow;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", school=" + school +
                ", capacity=" + capacity +
                ", equipments=" + equipments +
                ", busyNow=" + busyNow +
                ", floor=" + floor +
                ", number=" + number +
                '}';
    }
}
