package dao;

import model.User;

import java.io.Serializable;
import java.util.List;

public interface UserDao <T, Id extends Serializable>{

    public void persist(T entity);

    public void update(T entity);

    public T findById(Id id);

    public void delete(T entity);

    public List<User> findAll();

    public void deleteAll();
}
