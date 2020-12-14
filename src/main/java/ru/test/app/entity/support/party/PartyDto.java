package ru.test.app.entity.support.party;

import ru.test.app.entity.party.Party;

public class PartyDto {

    private Long id;
    private String name;

    public PartyDto(Party party) {
        this.id = party.getId();
        this.name = party.getName();
    }

    public PartyDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
