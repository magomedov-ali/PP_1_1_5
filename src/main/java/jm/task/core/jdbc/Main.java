package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userServiceImpl = new UserServiceImpl();

        userServiceImpl.createUsersTable();

        userServiceImpl.saveUser("someName", "someLastName", (byte) 10);
        userServiceImpl.saveUser("anotherName", "anotherLastName", (byte) 20);
        userServiceImpl.saveUser("oneMoreName", "oneMoreLastName", (byte) 30);
        userServiceImpl.saveUser("oneMoreName", "oneMoreLastName", (byte) 30);

        List<User> users = userServiceImpl.getAllUsers();

        for (User user :
                users) {
            System.out.println(user);
        }

        userServiceImpl.cleanUsersTable();

        userServiceImpl.dropUsersTable();


//----------------OTHER TESTS----------------
//        userServiceImpl.dropUsersTable();
//
//        System.out.println();
//
//        userServiceImpl.createUsersTable();
//
//        System.out.println();
//
//        userServiceImpl.saveUser("Name1", "LastName1", (byte) 20);
//        userServiceImpl.saveUser("Name2", "LastName2", (byte) 25);
//        userServiceImpl.saveUser("Name3", "LastName3", (byte) 31);
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 38);
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 38);
//        userServiceImpl.saveUser("Name5", "LastName5", (byte) 39);
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 40);
//
//        System.out.println();
//
//        userServiceImpl.getAllUsers();
//
//        System.out.println();
//
//        userServiceImpl.removeUserById(1);
//        userServiceImpl.removeUserById(1);
//
//        System.out.println();
//
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 40);
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 40);
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 40);
//
//        System.out.println();
//
//        userServiceImpl.cleanUsersTable();
//
//        System.out.println();
//
//        userServiceImpl.removeUserById(1);
//        userServiceImpl.removeUserById(1);
//
//        System.out.println();
//
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 40);
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 40);
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 40);
//
//        System.out.println();
//
//        userServiceImpl.getAllUsers();
//
//        System.out.println();
//
//        userServiceImpl.removeUserById(3);
//        userServiceImpl.removeUserById(3);
//        userServiceImpl.removeUserById(6);
//        userServiceImpl.removeUserById(6);
//
//        System.out.println();
//
//        userServiceImpl.getAllUsers();
//
//        System.out.println();
//
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 40);
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 40);
//
//        System.out.println();
//
//        userServiceImpl.getAllUsers();
//
//        System.out.println();
//
//        userServiceImpl.removeUserById(1);
//        userServiceImpl.removeUserById(6);
//
//        System.out.println();
//
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 40);
//        userServiceImpl.saveUser("Name4", "LastName4", (byte) 40);
//
//        System.out.println();
//
//        userServiceImpl.removeUserById(1);
//        userServiceImpl.removeUserById(1);
//        userServiceImpl.removeUserById(5);
//        userServiceImpl.removeUserById(5);
//
//        System.out.println();
//
//        userServiceImpl.getAllUsers();

    }
}