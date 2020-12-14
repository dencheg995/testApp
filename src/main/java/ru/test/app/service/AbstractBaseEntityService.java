package ru.test.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.app.exception.NotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractBaseEntityService<T, D extends JpaRepository>
        implements BaseEntityService<T> {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected abstract D getDao();

    protected abstract Class<T> getEntityClass();

    @Override
    public T getById(Long id) {
        Optional<T> entity = (Optional<T>) getDao().findById(id);
        isPresent(id, !entity.isPresent(), "Not found entity with id = '%s'");
        return entity.get();
    }

    @Override
    public List<T> getAll() {
        return getDao().findAll();
    }

    @Override
    public T create(String json, Object... params) {
        T entity = getEntityFromJson(json);
        getDao().saveAndFlush(entity);
        return entity;
    }

    @Override
    public T update(Long id, String json, Object... params) {
        T entity = getById(id);
        isPresent(id, entity == null, "Not found user with id = '%s'");
        updateFromString(json, entity);
        getDao().saveAndFlush(entity);
        return entity;
    }

    @Override
    public void remove(Long id, Object... params) {
        T entity = getById(id);
        isPresent(id, entity == null, "Not found user with id = '%s'");
        getDao().deleteById(id);
    }

    private void isPresent(Long id, boolean isPresent, String msg) {
        if (isPresent) {
            String message = String.format(msg, id);
            throw new NotFoundException(message);
        }
    }

    protected T getEntityFromJson(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, getEntityClass());
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Can't give json from given string: '%s'", json), e);
        }
    }

    protected static void updateFromString(String json, Object objectForUpdate) {
        try {
            OBJECT_MAPPER.readerForUpdating(objectForUpdate).readValue(json);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Can't give json from given string: '%s'", json), e);
        }
    }
}
