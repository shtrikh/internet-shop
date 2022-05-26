package org.example.dao;

import java.sql.Connection;

public interface DaoFactory {
    Connection getConnection();
}
