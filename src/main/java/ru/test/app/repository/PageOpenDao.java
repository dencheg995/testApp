package ru.test.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.test.app.entity.PageOpen;
import ru.test.app.entity.support.PageOpenId;

public interface PageOpenDao extends JpaRepository<PageOpen, PageOpenId> {

    @Query("SELECT pageOpen FROM PageOpen pageOpen")
    PageOpen getPageOpen();

}
