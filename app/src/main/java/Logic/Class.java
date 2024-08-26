package Logic;

import Logic.Enums.Subject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Class {
    private int id;
    private Teacher teacher;
    private ArrayList<Student> students;
    private String briefInformation;
    private Date classDate;
    private Time classTime;
    private Subject subject;
    private int duration;
    private String age;
    private School school;
    private Room room;
    private boolean everyWeek;
    private boolean everyDay;
    public static int idCounter = 0;


    public Class(Teacher teacher, ArrayList<Student> students, String briefInformation, Date classDate, Time classTime, Subject subject, int duration, String age,School school ,Room room, boolean everyWeek, boolean everyDay) {
        this.teacher = teacher;
        this.students = students;
        this.briefInformation = briefInformation;
        this.classDate = classDate;
        this.classTime = classTime;
        this.subject = subject;
        this.duration = duration;
        this.age = age;
        this.school = school;
        this.room = room;
        this.everyWeek = everyWeek;
        this.everyDay = everyDay;
        this.id = idCounter++;
    }

    public Class(String briefInformation) {
        this.briefInformation = briefInformation;
    }

    public Class(Date classDate, Time classTime, Subject subject, String age) {
        this.classDate = classDate;
        this.classTime = classTime;
        this.subject = subject;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public String getBriefInformation() {
        return briefInformation;
    }

    public void setBriefInformation(String briefInformation) {
        this.briefInformation = briefInformation;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    public Time getClassTime() {
        return classTime;
    }

    public void setClassTime(Time classTime) {
        this.classTime = classTime;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public boolean isEveryWeek() {
        return everyWeek;
    }

    public void setEveryWeek(boolean everyWeek) {
        this.everyWeek = everyWeek;
    }

    public boolean isEveryDay() {
        return everyDay;
    }

    public void setEveryDay(boolean everyDay) {
        this.everyDay = everyDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return id == aClass.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Class{" +
                "briefInformation='" + briefInformation + '\'' +
                '}';
    }
}