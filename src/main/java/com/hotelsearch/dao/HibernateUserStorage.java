package com.hotelsearch.dao;

import com.hotelsearch.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Repository
public class HibernateUserStorage implements UserStorage {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public boolean save(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
        return true;
    }

    @Override
    @Transactional
    public User findUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        User userByLogin = session
                .createQuery("from User where login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
        session.close();
        return userByLogin;
    }

    @Override
    @Transactional
    public User findUserByName(String name) {
        Session session = sessionFactory.openSession();
        User userByName = session
                .createQuery("from User where name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
        session.close();
        return userByName;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        List<User> resultList = session
                .createQuery("from User", User.class).getResultList();
        session.close();
        return resultList;
    }

    @Override
    @Transactional
    public boolean userExists(String login) {
        Session session = sessionFactory.openSession();
        Long totalUsers = (Long) session
                .createQuery("SELECT COUNT(*) FROM User WHERE login = :login")
                .setParameter("login", login)
                .getSingleResult();
        session.close();
        return totalUsers > 0;
    }

    @Override
    @Transactional
    public boolean userExistsByName(String name) {
        Session session = sessionFactory.openSession();
        Long totalUsers = (Long) session
                .createQuery("SELECT COUNT(*) FROM User WHERE name = :name")
                .setParameter("name", name)
                .getSingleResult();
        session.close();
        return totalUsers > 0;
    }

    @Override
    @Transactional
    public void update(String login, User newUser, HttpServletRequest request,
                       HttpServletResponse response) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User oldUser = findUserByLogin(login);
        if (oldUser.equals(newUser)) return;
        if (!(newUser.getLogin() == null) && !(newUser.getLogin().equals(oldUser.getLogin())))
            oldUser.setLogin(newUser.getLogin());
        if (!(newUser.getEmail() == null) && !(newUser.getEmail().equals(oldUser.getEmail())))
            oldUser.setEmail(newUser.getEmail());
        if (!(newUser.getName() == null) && !(newUser.getName().equals(oldUser.getName())))
            oldUser.setName(newUser.getName());
        if (!(newUser.getPassword() == null) && !(newUser.getPassword().equals(oldUser.getPassword())))
            oldUser.setPassword(newUser.getPassword());
        request.getSession().setAttribute("user", oldUser);
        session.merge(oldUser);
        tx.commit();
        session.close();
    }
}