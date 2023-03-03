package Repositories;

import Models.User;
import Util.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserRepo {
    public User getById(int id) {
        return HibernateFactory.getSessionFactory().openSession().get(User.class, id);
    }

    public void save(User user) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(user);
        tx1.commit();
        session.close();
    }

    public List<User> getAll() {
        return (List<User>) HibernateFactory.getSessionFactory().openSession().createQuery("From User").list();
    }
}
