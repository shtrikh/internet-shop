package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    User findUser(String login);
    User findUser(int id);
    User saveUser(User user);
    List<User> findAll();

    boolean ban(int id, String banned);
    boolean promote(int id);
    boolean deleteUser(int id);
}
