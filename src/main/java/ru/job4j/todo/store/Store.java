package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.todo.model.Item;

import java.util.List;

public interface Store {

    Item add(Item item);

    boolean updateTask(int id);

    List<Item> findAll();

    List<Item> findByStatusTask(Boolean status);

    Item findById(int id);
}
