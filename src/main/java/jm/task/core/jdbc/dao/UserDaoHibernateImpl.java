package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
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

        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().getCurrentSession()){

            transaction = session.beginTransaction();
            session.createSQLQuery(sqlQuery).addEntity(User.class).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {

        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().getCurrentSession()){

            transaction = session.beginTransaction();
            session.createSQLQuery("drop table if exists users").addEntity(User.class).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().getCurrentSession()){

            User user = new User(name, lastName, age);

            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();

            System.out.println("User with the name - " + user.getName() +
                    " was added to database");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {

        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().getCurrentSession()){

            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {

        Transaction transaction = null;
        List<User> users = new ArrayList<>();

        try (Session session = Util.getSessionFactory().getCurrentSession()) {

            transaction = session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return users;

    }

    @Override
    public void cleanUsersTable() {

        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().getCurrentSession()) {

            transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

}