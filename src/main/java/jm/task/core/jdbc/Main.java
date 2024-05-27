package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.dropUsersTable();
        userDaoHibernate.saveUser("Roman", "Ruzol", (byte) 35);
        userDaoHibernate.removeUserById(2);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();

    }
}
