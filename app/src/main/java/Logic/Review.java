package Logic;

import java.util.Objects;

public class Review {
    private int id;
    private int degree;
    private User reviewer;
    private String comment;
    private Class class1;
    public static int idCounter = 0;

    public Review(int degree, User reviewer, String comment, Class class1) {
        this.degree = degree;
        this.reviewer = reviewer;
        this.comment = comment;
        this.class1 = class1;
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Class getClass1() {
        return class1;
    }

    public void setClass1(Class class1) {
        this.class1 = class1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", degree=" + degree +
                ", reviewer=" + reviewer +
                ", comment='" + comment + '\'' +
                '}';
    }
}
