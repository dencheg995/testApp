package ru.test.app.service.impl;

import org.springframework.stereotype.Service;
import ru.test.app.entity.PageOpen;
import ru.test.app.entity.support.PageOpenId;
import ru.test.app.repository.PageOpenDao;
import ru.test.app.service.PageOpenService;

import java.util.Date;

@Service
public class PageOpenServiceImpl implements PageOpenService {

    private final PageOpenDao dao;

    public PageOpenServiceImpl(PageOpenDao dao) {
        this.dao = dao;
    }

    @Override
    public void updatePageOpen() {
        dao.deleteAll();
        PageOpen pageOpen =  new PageOpen();
        pageOpen.setId(getPageOpenId());
        dao.saveAndFlush(pageOpen);
    }

    private PageOpenId getPageOpenId() {
        PageOpenId pageOpenId = new PageOpenId();
        pageOpenId.setDate(new Date());
        return pageOpenId;
    }
}
