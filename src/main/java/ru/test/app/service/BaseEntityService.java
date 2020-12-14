package ru.test.app.service;

import java.util.List;

public interface BaseEntityService<T> {

    T getById(Long id);

    List<T> getAll();

    T create(String json, Object... params);

    T update(Long id, String json, Object... params);

    void remove(Long id, Object... params);

}
