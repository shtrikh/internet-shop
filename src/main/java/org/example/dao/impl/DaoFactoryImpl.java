package org.example.dao.impl;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.DaoFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactoryImpl implements DaoFactory {
    private static final Logger LOGGER = LogManager.getLogger(DaoFactoryImpl.class);

    private static final String USER = "root";

    private static final String PASSWORD = "Ivan2003!1";

    private MysqlDataSource dataSource;

    @Override
    public Connection getConnection() {
        if(dataSource == null){
            initDataSource();
        }
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Failed to create connection to database.", e);
            throw new RuntimeException(e);
        }
    }

    private void initDataSource(){
        dataSource = new MysqlDataSource();
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
    }
}
