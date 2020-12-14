package ru.test.app.entity.support.party;

import ru.test.app.entity.party.Party;
import ru.test.app.entity.support.item.ItemDto;

import java.util.List;

public class PartyDetailDto extends PartyDto {

    private List<ItemDto> itemDtos;

    public PartyDetailDto(Party party) {
        super(party);
    }

    public PartyDetailDto() {
    }

    public List<ItemDto> getItemDtos() {
        return itemDtos;
    }

    public void setItemDtos(List<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }
}
