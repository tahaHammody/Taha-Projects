package Logic;


import Logic.Enums.Gender;
import Logic.Enums.Region;
import Logic.Enums.Subject;
import Logic.Enums.Type;

import java.util.Objects;

public class User {
    private String userName;
    private String email;
    private String phone;
    private String password;
    private Region region;
    private Type type;
    private int image;
    private Gender gender;
    private String age;
    private int id;
    public static int idCounter = 0;

    public User(String userName, String email, String phone, String password, Region region, Type type, int image, Gender gender, String age) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.region = region;
        this.type = type;
        this.image = image;
        this.gender = gender;
        this.age = age;
        this.id = idCounter++;
    }

    public User(String userName, String age, int Image) {
        this.userName = userName;
        this.age = age;
        this.image = Image;
    }

    public User(String userName, int image) {
        this.userName = userName;
        this.image = image;
    }

    public User(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
