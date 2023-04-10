package jm.task.core.jdbc;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.Session;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserService userService = new UserServiceImpl();
         userService.dropUsersTable();
        //userService.cleanUsersTable();
//
//        userService.createUsersTable();
//
//        userService.saveUser("Коля", "Иванов", (byte) 23);
//        userService.saveUser("Иван", "Шишкин", (byte) 56);
//        userService.saveUser("Александр", "Пушкин", (byte) 32);
//        userService.removeUserById(2);
          userService.getAllUsers();
//
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
        //userService.dropUsersTable();
    }
}
