package org.example.dao;

import java.util.List;

public interface GenericDao<T> {
    T save(T model);
    boolean update(T model);
    boolean delete(int id);
    List<T> findAll();
}
