package Logic;

import android.media.Image;

import Logic.Enums.Gender;
import Logic.Enums.Region;
import Logic.Enums.Subject;
import Logic.Enums.Type;

public class Teacher extends User{

    private Subject subject;
    private int seniority;


    public Teacher(String userName, String email, String phone, String password, Region region, Type type, int image, Gender gender, String age, Subject subject, int seniority) {
        super(userName, email, phone, password, region, type, image, gender, age);
        this.subject = subject;
        this.seniority = seniority;
    }

    public Teacher(String userName, int image, Subject subject) {
        super(userName, image);
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "subject=" + subject +
                ", seniority=" + seniority +
                '}';
    }


}
