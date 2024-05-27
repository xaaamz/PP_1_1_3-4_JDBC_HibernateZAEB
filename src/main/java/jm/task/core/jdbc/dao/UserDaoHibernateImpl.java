package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Util util = new Util();
        try (Session session = util.getSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users(id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(30)," +
                    "last_name VARCHAR(40)," +
                    "age INT)";

            NativeQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void dropUsersTable() {
        Util util = new Util();
        try (Session session = util.getSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE  users";
            NativeQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        try (Session session = util.getSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
            NativeQuery nq = session.createSQLQuery(sql);
            nq.setParameter(1, name);
            nq.setParameter(2, lastName);
            nq.setParameter(3, age);
            nq.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        Util util = new Util();
        try (Session session = util.getSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DELETE FROM users WHERE id = ? ";
            NativeQuery nq = session.createNativeQuery(sql);
            nq.setParameter(1, id);
            nq.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public List<User> getAllUsers() {
        Util util = new Util();
        List<User> list = null;
        try (Session session = util.getSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "FROM User";
            list = session.createQuery(sql).getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void cleanUsersTable() {
        Util util = new Util();
        try (Session session = util.getSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DELETE FROM users";
            NativeQuery nq = session.createNativeQuery(sql);
            nq.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
