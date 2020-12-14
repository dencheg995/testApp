package ru.test.app.service.item;

import ru.test.app.entity.item.Item;
import ru.test.app.entity.support.item.ItemDetailDto;
import ru.test.app.entity.support.item.ItemDto;
import ru.test.app.service.BaseEntityService;

import java.util.List;

public interface ItemService extends BaseEntityService<Item> {

    List<ItemDto> getByPartyId(Long partyId);

    List<ItemDto> getAllWithoutParentId();

    ItemDetailDto getDetailInfoById(Long id);

    ItemDto createItemDto(String json);

    void sale(Long itemId);
}
