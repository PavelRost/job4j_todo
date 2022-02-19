package ru.job4j.todo.store;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.util.List;

public interface Store {

    User findUserByEmail(String email);

    Item add(Item item, String[] categoryIds);

    User addUser(User user);

    boolean updateTask(int id);

    List<Item> findAll();

    List<Category> findAllCategory();

    List<Item> findByStatusTask(Boolean status);

    Item findById(int id);
}
