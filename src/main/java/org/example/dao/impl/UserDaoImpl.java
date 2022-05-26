package org.example.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.DaoFactory;
import org.example.dao.UserDao;
import org.example.enums.Role;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    private final DaoFactory daoFactory;

    public UserDaoImpl() {
        daoFactory = new DaoFactoryImpl();
    }


    @Override
    public User save(User model) {
        PreparedStatement stmt = null;

        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("INSERT INTO shop.user(login, password, name, surname, role, Email, ban)"+
                    " VALUES (?, ?, ?, ?, ?, ?, 0)");
            LOGGER.error("login: " + model.getLogin());
            stmt.setString(1, model.getLogin());
            LOGGER.error("password: " + model.getPassword());
            stmt.setString(2, model.getPassword());
            LOGGER.error("name: " + model.getName());
            stmt.setString(3, model.getName());
            stmt.setString(4, model.getSurname());
            stmt.setString(5, model.getRole().toString());
            stmt.setString(6, model.getEmail());

            if (stmt.executeUpdate() == 1) {
                LOGGER.debug("Saved user successfully.");
                return model;
            } else {
                LOGGER.error("Failed to save user.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to save user.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
        }
    }

    @Override
    public boolean update(User model) {
        PreparedStatement stmt = null;

        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("INSERT INTO shop.user(login, password, name, surname, role, Email)"+
                    " VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, model.getLogin());
            stmt.setString(2, model.getPassword());
            stmt.setString(3, model.getName());
            stmt.setString(4, model.getSurname());
            stmt.setString(5, model.getRole().toString());
            stmt.setString(6, model.getEmail());

            if (stmt.executeUpdate() == 1) {
                LOGGER.debug("Update user successfully.");
                return true;
            } else {
                LOGGER.error("Failed to update user.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to update user.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
        }
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement stmt = null;

        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("DELETE FROM shop.user WHERE userId =?");

                stmt.setInt(1, id);


            if (stmt.executeUpdate() == 1) {
                LOGGER.debug("Banned user successfully with id {}.", id);
                return true;
            } else {
                LOGGER.error("Failed to ban user.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to ban user.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
        }
    }

    @Override
    public List<User> findAll() {
        List<User> selectedUsers = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("SELECT * FROM shop.user WHERE role ='USER'");
            rs = stmt.executeQuery();

            while (rs.next()) {
                selectedUsers.add(new User.Builder().withId(rs.getInt(1)).withLogin(rs.getString(2)).
                        withPassword(rs.getString(3)).withName(rs.getString(4)).
                        withSurname(rs.getString(5)).withRole(Role.valueOf(rs.getString(6).toUpperCase())).
                        withEmail(rs.getString(7)).withBan(rs.getInt(8) != 0).build());
            }

            LOGGER.debug("Users successfully selected.");
            return selectedUsers;

        } catch (SQLException e) {
            LOGGER.error("Failed to select users.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing result set.", e);
            }
        }
    }

    @Override
    public User findUser(String login){
        User selectedUser = new User();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("SELECT * FROM shop.user WHERE login=?");
            stmt.setString(1, login);
            rs = stmt.executeQuery();


            if(rs.next()) {
                selectedUser = new User.Builder()
                        .withId(rs.getInt(1))
                        .withLogin(rs.getString(2))
                        .withPassword(rs.getString(3))
                        .withName(rs.getString(4))
                        .withSurname(rs.getString(5))
                        .withRole(Role.valueOf(rs.getString(6).toUpperCase()))
                        .withEmail(rs.getString(7))
                        .withBan(rs.getInt(8)==1).build();
            }

            LOGGER.debug("User successfully selected.");
            return selectedUser;

        } catch (SQLException e) {
            LOGGER.error("Failed to select user.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing result set.", e);
            }
        }
    }

    @Override
    public User findUser(int id){
        User selectedUser = new User();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("SELECT * FROM shop.user WHERE userId=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();


            if(rs.next()) {
                selectedUser = new User.Builder().withId(rs.getInt(1)).withLogin(rs.getString(2)).
                        withPassword(rs.getString(3)).withName(rs.getString(4)).
                        withSurname(rs.getString(5)).withRole(Role.valueOf(rs.getString(6).toUpperCase())).
                        withEmail(rs.getString(7)).withBan(rs.getInt(8) == 1).build();
            }

            LOGGER.debug("User successfully selected.");
            return selectedUser;

        } catch (SQLException e) {
            LOGGER.error("Failed to select user.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing result set.", e);
            }
        }
    }

    @Override
    public boolean ban(int id, String banned) {
        PreparedStatement stmt = null;

        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("UPDATE shop.user SET ban =? WHERE userId =?");
            if(banned.equalsIgnoreCase("true")){
                stmt.setInt(1, 0);
            } else {
                stmt.setInt(1, 1);
            }
            stmt.setInt(2, id);

            if (stmt.executeUpdate() == 1) {
                LOGGER.debug("Banned user successfully with id {}.", id);
                return true;
            } else {
                LOGGER.error("Failed to ban user.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to ban user.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
        }
    }

    @Override
    public boolean promote(int id) {
        PreparedStatement stmt = null;

        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("UPDATE shop.user SET role ='ADMIN' WHERE userId =?");
            stmt.setInt(1, id);

            if (stmt.executeUpdate() == 1) {
                LOGGER.debug("Promoted user successfully with id {}.", id);
                return true;
            } else {
                LOGGER.error("Failed to promote user.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to save user.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
        }
    }

}
