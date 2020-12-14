package ru.test.app.service.item.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.app.entity.item.Item;
import ru.test.app.entity.item.ItemTypeEnum;
import ru.test.app.entity.party.Party;
import ru.test.app.entity.support.item.ItemDetailDto;
import ru.test.app.entity.support.item.ItemDto;
import ru.test.app.entity.support.party.PartyDto;
import ru.test.app.repository.item.ItemDao;
import ru.test.app.service.AbstractBaseEntityService;
import ru.test.app.service.item.ItemService;
import ru.test.app.service.party.PartyService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class ItemServiceImpl extends AbstractBaseEntityService<Item, ItemDao> implements ItemService {

    private final ItemDao itemDao;

    @Autowired
    private PartyService partyService;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    protected ItemDao getDao() {
        return itemDao;
    }

    @Override
    protected Class<Item> getEntityClass() {
        return Item.class;
    }

    @Override
    public List<ItemDto> getByPartyId(Long partyId) {
        if (isNull(partyId))
            return Collections.emptyList();

        List<Item> items = getDao().getByPartyId(partyId);
        return getDtoList(items);
    }

    @Override
    public ItemDto createItemDto(String json) {

        Item item = new Item();

        ItemDto itemDto = getItemDtoByJson(json);

        if (itemDto.getParentId() != null) {
            Item parentItem = super.getById(itemDto.getParentId());
            item.setParentItem(parentItem);
            updateChildrenCount(parentItem);
            getDao().saveAndFlush(parentItem);
        }

        if (itemDto.getOwner() != null) {
            Party party = partyService.getById(itemDto.getOwner().getId());
            item.setParty(party);
        }

        item.setSerial(itemDto.getSerialNumber());
        item.setType(ItemTypeEnum.valueOf(itemDto.getType()).getType());
        Item savedItem = getDao().saveAndFlush(item);
        return convertToDto(savedItem);
    }

    @Override
    public void sale(Long itemId) {
        Item item = getDao().getByIdAndType(itemId, ItemTypeEnum.ITEM.getType());

        if (isNull(item))
            return;

        Item parentItem = item.getParentItem();

        item.setParty(null);
        item.setParentItem(null);
        item.setChildrenCount(0L);
        getDao().saveAndFlush(item);

        parentItem.setChildrenCount(parentItem.getChildrenCount() - 1);

        if (parentItem.getChildrenCount() == 0)
            sale(parentItem.getId());
        else
            getDao().saveAndFlush(parentItem);

    }

    private ItemDto getItemDtoByJson(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, ItemDto.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Can't give json from given string: '%s'", json), e);
        }
    }

    private List<ItemDto> getDtoList(List<Item> items) {
        return items
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getAllWithoutParentId() {


        List<Item> items = getDao().getAllWithoutParentIdByType(ItemTypeEnum.PACK.getType());

        if (isEmpty(items))
            return Collections.emptyList();

        return getDtoList(items);
    }

    @Override
    public ItemDetailDto getDetailInfoById(Long id) {
        Item item = super.getById(id);

        if (isNull(item))
            return null;

        return convertToDetailDto(item);
    }

    private ItemDto convertToDto(Item item) {
        ItemDto itemDto = new ItemDto(item);

        setItemDtoInfo(item, itemDto);
        return itemDto;
    }

    private void setItemDtoInfo(Item item, ItemDto itemDto) {
        Party party = item.getParty();

        if (nonNull(party)) {
            PartyDto dto = partyService.getPartyDtoById(party.getId());
            itemDto.setOwner(dto);
        }

        Item parentItem = item.getParentItem();
        if (nonNull(parentItem))
            itemDto.setParentId(parentItem.getId());

        itemDto.setType(getItemStringType(item));
    }

    private ItemDetailDto convertToDetailDto(Item item) {
        ItemDetailDto itemDetailDto = new ItemDetailDto(item);
        setItemDtoInfo(item, itemDetailDto);
        List<ItemDetailDto> childrenDetailDtos = item.getChildrenItems()
                .stream().map(this::convertToDetailDto).collect(Collectors.toList());

        itemDetailDto.setChildren(childrenDetailDtos);
        return itemDetailDto;
    }

    private String getItemStringType(Item item) {
        Long type = item.getType();
        if (isNull(type))
            return null;

        if (type.longValue() == ItemTypeEnum.PACK.getType())
            return ItemTypeEnum.PACK.toString();
        else if (type.longValue() == ItemTypeEnum.ITEM.getType())
            return ItemTypeEnum.ITEM.toString();

        return null;
    }


    private void updateChildrenCount(Item item) {
        Long childrenCount = item.getChildrenCount();
        if (isNull(childrenCount))
            item.setChildrenCount(0L);
        else
            item.setChildrenCount(childrenCount + 1);
    }
}
