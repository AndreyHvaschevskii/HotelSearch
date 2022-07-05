package com.hotelsearch.service;

import com.hotelsearch.dao.UserStorage;
import com.hotelsearch.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private final UserStorage userStorage;

    public UserService(@Qualifier("hibernateUserStorage") UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public boolean save(User user) {
        if (userStorage.userExists(user.getLogin())) {
            return false;
        }
        return userStorage.save(user);
    }

    public User findUserByLogin(User user) {
        return userStorage.findUserByLogin(user.getLogin());
    }

    public User findUserByName(String name) {
        return userStorage.findUserByName(name);
    }

    public List<User> findAll() {
        return userStorage.findAll();
    }

    public boolean userExists(String login) {
        return userStorage.userExists(login);
    }

    public boolean userExistsByName(String name) {
        return userStorage.userExistsByName(name);
    }

    public boolean authUserByLoginAndPass(User user) {
        if (!userStorage.userExists(user.getLogin())) {
            return false;
        }
        User userByLogin = findUserByLogin(user);
        return userByLogin.getPassword().equals(user.getPassword());
    }

    public void update(String login, User newUser, HttpServletRequest request,
                       HttpServletResponse response) {
        userStorage.update(login, newUser, request, response);
    }
}