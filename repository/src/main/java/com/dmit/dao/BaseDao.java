package com.dmit.dao;

import java.util.List;

public interface BaseDao <T, V>{
    void create(T object);
    T read(T object);
    void update(T object);
    void delete(T object);

    T findById(V id);
    List<T> findAll();
}
