package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;

public class HbnStore implements Store, AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public HbnStore() {
    }

    private static final class Lazy {
        private static final Store INST = new HbnStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public List<User> findUserByEmail(String email) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User u where u.email = :paramEmail");
        query.setParameter("paramEmail", email);
        List<User> rsl = query.list();
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public User addUser(User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public boolean updateTask(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Item i set i.done = :newDone where i.id = :fId");
        query.setParameter("newDone", true);
        query.setParameter("fId", id);
        int rsl = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return rsl != 0;
    }

    @Override
    public List<Item> findAll() {
        return this.tx(
                session -> session.createQuery("from Item").list()
        );
    }

    @Override
    public List<Item> findByStatusTask(Boolean status) {
        return this.tx(
                session -> {
                    List<Item> temp = session.createQuery("from Item").list();
                    List<Item> rsl = new ArrayList<>();
                    for (var item : temp) {
                        if (item.isDone() == status) {
                            rsl.add(item);
                        }
                    }
                    return rsl;
                }
        );
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private <T> T tx(final Function<Session, T> command) {
        Session session = sf.openSession();
        session.beginTransaction();
        T rsl = command.apply(session);
        session.getTransaction().commit();
        session.close();
        return rsl;
    }
}
