package Logic;

import java.util.Date;
import java.util.Objects;

public class Message {
    private int id;
    private User sender;
    private String title;
    private String content;
    private User receiver;
    private Date sendingDate;
    public static int idCounter = 0;

    public Message(User sender, User receiver, Date sendingDate, String title, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.sendingDate = sendingDate;
        this.title = title;
        this.content = content;
        this.id = idCounter++;
    }

    public Message(User sender, String title, String content) {
        this.sender = sender;
        this.title = title;
        this.content = content;
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", sendingDate=" + sendingDate +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
