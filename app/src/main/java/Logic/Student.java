package Logic;

import android.media.Image;

import Logic.Enums.Gender;
import Logic.Enums.Region;
import Logic.Enums.Type;

public class Student extends User{


    public Student(String userName, String email, String phone, String password, Region region, Type type, int image, Gender gender, String age) {
        super(userName, email, phone, password, region, type, image, gender, age);
    }

    public Student(String userName, String age, int Image) {
        super(userName, age, Image);
    }
}
