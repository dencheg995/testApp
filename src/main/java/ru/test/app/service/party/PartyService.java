package ru.test.app.service.party;

import ru.test.app.entity.party.Party;
import ru.test.app.entity.support.party.PartyDetailDto;
import ru.test.app.entity.support.party.PartyDto;
import ru.test.app.service.BaseEntityService;

import java.util.List;

public interface PartyService extends BaseEntityService<Party> {

    List<PartyDto> getAllPartyDto();

    PartyDto getPartyDtoById(Long id);

    PartyDetailDto getPartyDetailDtoById(Long id);

    PartyDto createPartyDto(String json);
}
