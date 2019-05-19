package service;

import collections.Users;
import dao.HibernateUserDao;
import entity.User;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final HibernateUserDao hibernateUserDao;
    private final MainService mainService;

    @Autowired
    public UserService(HibernateUserDao hibernateUserDao, MainService mainService) {
        this.hibernateUserDao = hibernateUserDao;
        this.mainService = mainService;
    }

    public User getByLogin(String login) {
        for (User u : Users.getUsers()) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    public boolean isAddUser(String login, String password, String name, String lastname, String email) {
        boolean valid = EmailValidator.getInstance().isValid(email);
        if (!valid || login.isEmpty() || password.isEmpty() || name.isEmpty() || lastname.isEmpty()) {
            return false;
        }
        for (User u : Users.getUsers()) {
            if (u.getLogin().equals(login)) {
                return false;
            }
        }
        User u = new User(login, password, name, lastname, email, 0, 0, 0, 0, 12000);
        hibernateUserDao.add(u);
        Users.getUsers().add(u);
        return true;
    }

    public User getUser() {
        return getByLogin(mainService.getCurrentUsername());
    }

}
