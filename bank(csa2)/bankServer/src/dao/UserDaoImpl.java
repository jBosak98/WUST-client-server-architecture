package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDaoImpl implements UserDao<Object,Integer> {

    private Session session;

    private Transaction transaction;

    public Session openSession(){
        return getSessionFactory().openSession();
    }

    private static SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public void persist(Object entity) {
        getSession().save(entity);
    }

    @Override
    public void update(Object entity) {
        getSession().update(entity);
    }

    @Override
    public Object findById(Integer id) {
        return  getSession().get(User.class, id);
    }


    @Override
    public void delete(Object entity) {
        getSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return getSession().createQuery("from User").list();

    }

    @Override
    public void deleteAll() {
        List<User> users = findAll();
        for (User user: users){
            delete(user);
        }

    }
}
