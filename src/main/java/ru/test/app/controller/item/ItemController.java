package ru.test.app.controller.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.test.app.controller.AbstractController;
import ru.test.app.entity.support.item.ItemDetailDto;
import ru.test.app.entity.support.item.ItemDto;
import ru.test.app.service.item.ItemService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(ItemController.PATH)
public class ItemController extends AbstractController {

    public static final String PATH = MAIN_PATH + "/item/";
    public static final String GET_ALL_DESCRIPTION = "list of items";
    public static final String GET_BY_ID_DESCRIPTION = "items's info";
    public static final String CREATED_DESCRIPTION = "created";
    public static final String SALE_DESCRIPTION = "sales";

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDto>> getAll() {
        HttpHeaders httpHeaders = getHttpHeadersDescription(GET_ALL_DESCRIPTION);
        return new ResponseEntity<>(itemService.getAllWithoutParentId(), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "{itemId}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDetailDto> getById(@PathVariable Long itemId) {
        HttpHeaders httpHeaders = getHttpHeadersDescription(GET_BY_ID_DESCRIPTION);
        return new ResponseEntity<>(itemService.getDetailInfoById(itemId), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDto> create(@RequestBody String json) {
        HttpHeaders httpHeaders = getHttpHeadersDescription(CREATED_DESCRIPTION);
        return new ResponseEntity<>(itemService.createItemDto(json), httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{itemId}/sale", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@PathVariable Long itemId) {
        itemService.sale(itemId);
        HttpHeaders httpHeaders = getHttpHeadersDescription(SALE_DESCRIPTION);
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }
}
