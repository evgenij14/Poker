package collections;


import dao.UserDao;
import entity.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Users {

    private static List<User> users = new CopyOnWriteArrayList<>();

    static {
        users.addAll(UserDao.getAllUsers());
    }

    public static List<User> getUsers() {
        return users;
    }
}
