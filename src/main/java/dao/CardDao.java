package dao;

import entity.Card;
import hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public interface CardDao {
    static List<Card> getAllCards() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Card> out = s.createQuery("FROM Card").list();
        return out;
    }
}
