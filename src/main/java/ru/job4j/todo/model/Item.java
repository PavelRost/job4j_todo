package ru.job4j.todo.model;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "items")
public class Item {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date created = new Date(System.currentTimeMillis());
    private String description;
    private boolean done = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Item() {
    }

    public Item(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", description='" + description + '\'' + ", done=" + done + '}';
    }
}
