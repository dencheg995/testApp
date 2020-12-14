package ru.test.app.entity.support.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.test.app.entity.item.Item;
import ru.test.app.entity.support.party.PartyDto;

public class ItemDto {

    private Long id;
    private Long parentId;
    private String serialNumber;
    private PartyDto owner;
    private String type;
    private Long childrenCount;

    public ItemDto(Item item) {
        this.id = item.getId();
        this.serialNumber = item.getSerial();
        this.childrenCount = item.getChildrenCount();
    }

    public ItemDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @JsonProperty("owner")
    public PartyDto getOwner() {
        return owner;
    }

    public void setOwner(PartyDto owner) {
        this.owner = owner;
    }

    public Long getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Long childrenCount) {
        this.childrenCount = childrenCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
