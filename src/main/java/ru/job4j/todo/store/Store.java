package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.util.List;

public interface Store {

    List<User> findUserByEmail(String email);

    Item add(Item item);

    User addUser(User user);

    boolean updateTask(int id);

    List<Item> findAll();

    List<Item> findByStatusTask(Boolean status);

    Item findById(int id);
}
