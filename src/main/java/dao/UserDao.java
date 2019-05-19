package dao;

import entity.User;
import hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public interface UserDao {
    User getByLogin(String login);

    void update(User user);

    static List<User> getAllUsers() {
        List<User> out;
        Session s = HibernateUtil.getSessionFactory().openSession();
        out = s.createQuery("FROM User").list();
        return out;
    }

    void add(User u);
}
