package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        final String sqlQuery = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(45) NOT NULL," +
                "lastname VARCHAR(45) NOT NULL," +
                "age TINYINT NOT NULL);";

        try (Session session = Util.getSessionFactory().getCurrentSession()){

            session.beginTransaction();
            session.createSQLQuery(sqlQuery).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();

        }

    }

    @Override
    public void dropUsersTable() {

        try (Session session = Util.getSessionFactory().getCurrentSession()){

            session.beginTransaction();
            session.createSQLQuery("drop table if exists users").addEntity(User.class).executeUpdate();
            session.getTransaction().commit();

        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = Util.getSessionFactory().getCurrentSession()){

            User user = new User(name, lastName, age);

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

            System.out.println("User with the name - " + user.getName() +
                    " was added to database");

        }

    }

    @Override
    public void removeUserById(long id) {

        try (Session session = Util.getSessionFactory().getCurrentSession()){

            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

        }

    }

    @Override
    public List<User> getAllUsers() {

        try (Session session = Util.getSessionFactory().getCurrentSession()) {

            session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();

            return users;
        }

    }

    @Override
    public void cleanUsersTable() {

        try (Session session = Util.getSessionFactory().getCurrentSession()) {

            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();

        }

    }

}