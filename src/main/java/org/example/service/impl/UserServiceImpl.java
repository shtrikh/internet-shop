package org.example.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.impl.LoginCommand;
import org.example.dao.UserDao;
import org.example.dao.impl.UserDaoImpl;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

public final class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private static final UserService INSTANCE = new UserServiceImpl();

    private final UserDao userDao = new UserDaoImpl();

    private UserServiceImpl() {
        //private constructor
    }
    protected static UserService getInstance() {
        return INSTANCE;
    }

    @Override
    public User findUser(String login) {
        return userDao.findUser(login);
    }

    @Override
    public User findUser(int id) {
        return userDao.findUser(id);
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }



    @Override
    public boolean ban(int id, String banned) {
        return userDao.ban(id, banned);
    }

    @Override
    public boolean promote(int id) {
        return userDao.promote(id);
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.delete(id);
    }


}
