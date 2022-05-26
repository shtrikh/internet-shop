package org.example.model;

import org.example.enums.Role;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private String email;
    private boolean ban;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public boolean getBan() {
        return ban;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }


    public static class Builder{
        private User newUser;

        public Builder() {
            newUser = new User();
        }

        public User.Builder withId(int id) {
            newUser.id = id;
            return this;
        }

        public User.Builder withLogin(String login) {
            newUser.login = login;
            return this;
        }

        public User.Builder withPassword(String password) {
            newUser.password = password;
            return this;
        }

        public User.Builder withName(String name) {
            newUser.name = name;
            return this;
        }

        public User.Builder withSurname(String surname) {
            newUser.surname = surname;
            return this;
        }

        public User.Builder withRole(Role role){
            newUser.role = role;
            return this;
        }
        public User.Builder withEmail(String email) {
            newUser.email = email;
            return this;
        }
        public User.Builder withBan(Boolean ban) {
            newUser.ban = ban;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
