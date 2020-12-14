package ru.test.app.entity.support.item;

import ru.test.app.entity.item.Item;

import java.util.List;

public class ItemDetailDto extends ItemDto {

    private List<ItemDetailDto> children;

    public ItemDetailDto(Item item) {
        super(item);
    }

    public ItemDetailDto() {
    }

    public List<ItemDetailDto> getChildren() {
        return children;
    }

    public void setChildren(List<ItemDetailDto> children) {
        this.children = children;
    }
}
