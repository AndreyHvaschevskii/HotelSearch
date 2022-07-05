package com.hotelsearch.dao;

import com.hotelsearch.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserStorage {

    /**
     * @param user model to save
     * @return saved user model
     */
    boolean save(User user);

    /**
     * @param login the login
     * @return the user
     */
    User findUserByLogin(String login);

    /**
     * @param name the name
     * @return the user
     */

    User findUserByName(String name);

    /**
     * @return users list
     */
    List<User> findAll();

    boolean userExists(String login);

    boolean userExistsByName(String name);

    void update(String login, User newUser,
                HttpServletRequest request, HttpServletResponse response);
}