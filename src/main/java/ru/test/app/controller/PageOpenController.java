package ru.test.app.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.test.app.entity.support.party.PartyDto;
import ru.test.app.service.PageOpenService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(PageOpenController.PATH)
public class PageOpenController extends AbstractController {

    public static final String PATH = MAIN_PATH + "/partyPage/open";
    public static final String UPDATED_DESCRIPTION = "updated button";

    private final PageOpenService pageOpenService;

    public PageOpenController(PageOpenService pageOpenService) {
        this.pageOpenService = pageOpenService;
    }

    @RequestMapping(value = "", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update() {
        pageOpenService.updatePageOpen();
        HttpHeaders httpHeaders = getHttpHeadersDescription(UPDATED_DESCRIPTION);
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

}
