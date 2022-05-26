package org.example.dao;

import org.example.model.User;

public interface UserDao extends GenericDao<User>{
    User findUser(String login);

    User findUser(int id);

    boolean ban(int id, String banned);
    boolean promote(int id);
}
