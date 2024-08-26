package Logic;

import com.example.finalproject.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import Logic.Enums.Gender;
import Logic.Enums.Region;
import Logic.Enums.Subject;

public class SysData {

    private static SysData instance;
    static ArrayList<Teacher> teachers;
    static ArrayList<Student> students;
    static ArrayList<User> users;
    static ArrayList<Class> classes;
    static ArrayList<Review> reviews;
    static ArrayList<Message> messages;
    static ArrayList<Room> rooms;
    static ArrayList<School> schools;
    static HashMap<User , ArrayList<Class>> userClasses;
    static HashMap<User, ArrayList<Message>> userMessages;



    public SysData() {

    }

    public static synchronized SysData getInstance() {
        if (null == instance) {
            instance = new SysData();
            teachers = new ArrayList<Teacher>();
            teachers.add(new Teacher("Saeed", R.drawable.male_boy_person_people_avatar_icon_159358, Subject.Arabic));
            teachers.add(new Teacher("Hamzi", R.drawable.male_people_avatar_man_boy_curly_hair_icon_159362, Subject.Biology));
            teachers.add(new Teacher("Sara", R.drawable.female_woman_avatar_people_person_white_tone_icon_159370, Subject.Art));
            teachers.add(new Teacher("Sami", R.drawable.male_boy_person_people_avatar_icon_159358, Subject.Sciences));
            teachers.add(new Teacher("Raghad", R.drawable.female_woman_avatar_people_person_white_tone_icon_159370, Subject.English));
            teachers.add(new Teacher("Rina", R.drawable.female_woman_person_people_avatar_icon_159366, Subject.Computers));
            teachers.add(new Teacher("Maher", R.drawable.male_people_avatar_man_boy_curly_hair_icon_159362, Subject.Hebrew));
            teachers.add(new Teacher("Khaled", R.drawable.male_boy_person_people_avatar_white_tone_icon_159368, Subject.Math));
            students = new ArrayList<Student>();
            students.add(new Student("Adam", "First Class", R.drawable.male_boy_person_people_avatar_icon_159358));
            students.add(new Student("Laila", "Fifth Class", R.drawable.female_woman_avatar_people_person_white_tone_icon_159370));
            students.add(new Student("Mousa", "Second Class", R.drawable.male_boy_person_people_avatar_white_tone_icon_159368));
            students.add(new Student("Salma", "Sixth Class", R.drawable.female_woman_person_people_avatar_icon_159366));
            students.add(new Student("Aisha", "Fourth Class", R.drawable.female_woman_person_people_avatar_user_white_tone_icon_159359));
            students.add(new Student("Mohammad", "Third Class", R.drawable.male_people_avatar_man_boy_curly_hair_icon_159362));
            students.add(new Student("Omar", "Fifth Class", R.drawable.male_people_avatar_man_boy_curly_hair_icon_159362));
            users = new ArrayList<User>();
            classes = new ArrayList<Class>();
            classes.add(new Class(new Date(), new Time(9), Subject.Arabic, "Third Class" ));
            classes.add(new Class(new Date(), new Time(12), Subject.Math, "First Class" ));
            classes.add(new Class(new Date(), new Time(14), Subject.Geography, "Fifth Class" ));
            classes.add(new Class(new Date(), new Time(9), Subject.Sciences, "Second Class" ));
            classes.add(new Class(new Date(), new Time(17), Subject.English, "Third Class" ));
            classes.add(new Class(new Date(), new Time(11), Subject.Computers, "Sixth Class" ));
            classes.add(new Class(new Date(), new Time(13), Subject.Hebrew, "Sixth Class" ));
            classes.add(new Class(new Date(), new Time(18), Subject.Arabic, "Fourth Class" ));
            reviews = new ArrayList<Review>();
            reviews.add(new Review(R.drawable.number_circle_four_icon_172323, new User("Dana"), "this class is amazing i learned a lot", new Class("Math class with teacher khaled")));
            reviews.add(new Review(R.drawable.number_circle_one_icon_172321, new User("Yosef"),"the chairs were not comfortable", new Class("Art Class with teacher Tala")));
            reviews.add(new Review(R.drawable.number_circle_three_icon_172318, new User("Jameel"), "nice class ", new Class("Science class")));
            reviews.add(new Review(R.drawable.number_circle_five_icon_172324, new User("Maryam"), "i understand every thing for exam now",new Class("Math class in eastern School") ));
            messages = new ArrayList<Message>();
            messages.add(new Message(new User("Hajar", R.drawable.female_woman_avatar_people_person_white_tone_icon_159370), "Math Class Homework", "Hi, can you add me to math class in sunday"));
            messages.add(new Message(new User("Tamer", R.drawable.male_boy_person_people_avatar_icon_159358), "English Exam", "Hello admin, can you add a new class for English exam"));
            messages.add(new Message(new User("Rani", R.drawable.male_people_avatar_man_boy_curly_hair_icon_159362), "Delay my class", "i want to delay my class to monday morning"));
            messages.add(new Message(new User("Maram", R.drawable.female_woman_person_people_avatar_icon_159366), "updating student", "Hi, can you change my age in the app"));
            rooms = new ArrayList<Room>();
            rooms.add(new Room(new School("Alnajah School"), 20, 2, 5));
            rooms.add(new Room(new School("Alrazi School"), 20, 2, 5));
            rooms.add(new Room(new School("Eastern School"), 20, 2, 5));
            rooms.add(new Room(new School("Ibn Sina School"), 20, 2, 5));
            rooms.add(new Room(new School("Alnajah School"), 20, 2, 5));
            rooms.add(new Room(new School("Alrazi School"), 20, 2, 5));
            rooms.add(new Room(new School("Western School"), 20, 2, 5));
            rooms.add(new Room(new School("Alzaytoon School"), 20, 2, 5));
            rooms.add(new Room(new School("Institute of Education"), 20, 2, 5));
            rooms.add(new Room(new School("Math School"), 20, 2, 5));
            rooms.add(new Room(new School("English School"), 20, 2, 5));
            rooms.add(new Room(new School("Hebrew School"), 20, 2, 5));
            schools = new ArrayList<School>();
            schools.add(new School("Alnajah School", Region.East, new Time(8), new Time(18)));
            schools.add(new School("Alrazi School", Region.West, new Time(9), new Time(17)));
            schools.add(new School("Eastern School", Region.East, new Time(8), new Time(16)));
            schools.add(new School("Ibn Sina School", Region.North, new Time(8), new Time(16)));
            schools.add(new School("Western School", Region.West, new Time(10), new Time(15)));
            schools.add(new School("Alzaytoon School", Region.North, new Time(8), new Time(16)));
            schools.add(new School("Institute of Education", Region.South, new Time(12), new Time(20)));
            schools.add(new School("Math School", Region.East, new Time(8), new Time(15)));
            userClasses = new HashMap<User, ArrayList<Class>>();
            userMessages = new HashMap<User, ArrayList<Message>>();


        }
        return instance;
    }

    public  ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public  void setTeachers(ArrayList<Teacher> teachers) {
        SysData.teachers = teachers;
    }

    public  HashMap<User, ArrayList<Class>> getUserClasses() {
        return userClasses;
    }

    public void setUserClasses(HashMap<User, ArrayList<Class>> userClasses) {
        SysData.userClasses = userClasses;
    }

    public  HashMap<User, ArrayList<Message>> getUserMessages() {
        return userMessages;
    }

    public static void setUserMessages(HashMap<User, ArrayList<Message>> userMessages) {
        SysData.userMessages = userMessages;
    }

    public  ArrayList<Student> getStudents() {
        return students;
    }

    public  void setStudents(ArrayList<Student> students) {
        SysData.students = students;
    }

    public  ArrayList<User> getUsers() {
        return users;
    }

    public  void setUsers(ArrayList<User> users) {
        SysData.users = users;
    }

    public  ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        SysData.classes = classes;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        SysData.reviews = reviews;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        SysData.messages = messages;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        SysData.rooms = rooms;
    }
    public void addRoom (Room r){
        rooms.add(r);
    }
    public void addClass (Class c){
        classes.add(c);
    }
    public void addStudent (Student s){
        students.add(s);
    }
    public void addTeacher (Teacher t){
        teachers.add(t);
    }
    public void addUser (User u){
        users.add(u);
    }
    public void removeRoom (Room r){
        rooms.remove(r);
    }
    public void removeClass (Class c){
        classes.remove(c);
    }
    public void removeStudent (Student s){
        students.remove(s);
    }
    public void removeTeacher (Teacher t){
        teachers.remove(t);
    }
    public void removeUser (User u){
        users.remove(u);
    }

    public ArrayList<School> getSchools() {
        return schools;
    }

    public static void setSchools(ArrayList<School> schools) {
        SysData.schools = schools;
    }

    public ArrayList<Class> sortByTeacher (Teacher t ){
     ArrayList<Class> Classes = new ArrayList<>();
       for(int i=0;i<classes.size();i++){
           if(classes.get(i).getTeacher().equals(t)){
               Classes.add(classes.get(i));
           }
       }
       return Classes;
    }

    //TODO
    public Teacher getTeacherByUserName(String UserName){
        return null;
    }

    //TODO
    public Subject getSubjectBySubjectName(String subjectName){
        return null;
    }

    //TODO
    public Room getRoomByRoomNumber(String roomNumber){
        return null;
    }

    //TODO
    public School getSchoolByName(String schoolName){
        return null;
    }

    //TODO
    public Date getDateByString(String dateString){
        return null;
    }

    //TODO
    public Time getHourByString(String hourString){
        return null;
    }

    //TODO
    public ArrayList<Student> getStudentsByMultiText(String students){
        return null;
    }

    //TODO
    public Time getHourByText(String hour){
        return null;
    }
    //TODO
    public Region getRegionByRegionName(String region){
        return null;
    }
    //TODO
    public Gender getGenderByGenderName(String gender){
        return null;
    }
}


