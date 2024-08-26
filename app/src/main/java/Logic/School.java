package Logic;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import Logic.Enums.Region;

public class School {

    private int id;
    private String briefInformation;
    private String name;
    private Region region;
    private Time openingHour;
    private Time closingHour;
    private HashMap<DayOfWeek, Boolean> openingDays;
    private ArrayList<Room> rooms;
    public static int idCounter = 0;

    public School(String name, Region region, Time openingHour, Time closingHour) {
        this.name = name;
        this.region = region;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.id = idCounter++;
    }

    public School(String briefInformation, String name, Region region, Time openingHour, Time closingHour, HashMap<DayOfWeek, Boolean> openingDays , ArrayList<Room> rooms) {
        this.briefInformation = briefInformation;
        this.name = name;
        this.region = region;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.openingDays = openingDays;
        this.rooms = rooms;
        this.id = idCounter++;
    }

    public School(String name) {
        this.name = name;
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBriefInformation() {
        return briefInformation;
    }

    public void setBriefInformation(String briefInformation) {
        this.briefInformation = briefInformation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Time getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(Time openingHour) {
        this.openingHour = openingHour;
    }

    public Time getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(Time closingHour) {
        this.closingHour = closingHour;
    }

    public HashMap<DayOfWeek, Boolean> getOpeningDays() {
        return openingDays;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void setOpeningDays(HashMap<DayOfWeek, Boolean> openingDays) {
        this.openingDays = openingDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return id == school.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", briefInformation='" + briefInformation + '\'' +
                ", name='" + name + '\'' +
                ", region=" + region +
                ", openingHour=" + openingHour +
                ", closingHour=" + closingHour +
                ", openingDays=" + openingDays +
                ", rooms=" + rooms +
                '}';
    }
}
