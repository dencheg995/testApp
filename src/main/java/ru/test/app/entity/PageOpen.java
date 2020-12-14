package ru.test.app.entity;

import ru.test.app.entity.support.PageOpenId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import static ru.test.app.entity.EntityTableName.PAGE_OPEN;

@Entity
@Table(name = PAGE_OPEN)
public class PageOpen {

    private PageOpenId id;

    @EmbeddedId
    public PageOpenId getId() {
        return id;
    }

    public void setId(PageOpenId id) {
        this.id = id;
    }
}
