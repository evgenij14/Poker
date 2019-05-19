package dao;

import entity.User;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;


@Component
public class HibernateUserDao implements UserDao {

    @Override
    public User getByLogin(String login) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        User user = (User) s.createQuery(String.format("FROM User WHERE login='%s'", login)).uniqueResult();
        s.close();
        return user;
    }

    @Override
    public void add(User u) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(u);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void update(User user) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(user);
        s.getTransaction().commit();
        s.close();
    }
}
