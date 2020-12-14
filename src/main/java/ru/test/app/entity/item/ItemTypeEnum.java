package ru.test.app.entity.item;

public enum ItemTypeEnum {

    PACK(1),
    ITEM(2);

    private final long type;

    ItemTypeEnum(long type) {
        this.type = type;
    }

    public long getType() {
        return type;
    }
}
