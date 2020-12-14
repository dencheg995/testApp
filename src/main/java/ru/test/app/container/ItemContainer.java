package ru.test.app.container;


public class ItemContainer {

    private String serialNumber;
    private Integer limit;
    private Integer offset;

    public ItemContainer(String serialNumber, Integer limit, Integer offset) {
        this.serialNumber = serialNumber;
        this.limit = limit;
        this.offset = offset;
    }

    public static ItemContainer of(String serialNumber, Integer limit, Integer offset) {
        return new ItemContainer(serialNumber, limit, offset);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }
}
