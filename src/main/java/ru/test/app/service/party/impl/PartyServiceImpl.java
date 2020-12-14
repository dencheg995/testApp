package ru.test.app.service.party.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.app.entity.party.Party;
import ru.test.app.entity.support.party.PartyDetailDto;
import ru.test.app.entity.support.party.PartyDto;
import ru.test.app.repository.party.PartyDao;
import ru.test.app.service.AbstractBaseEntityService;
import ru.test.app.service.item.ItemService;
import ru.test.app.service.party.PartyService;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class PartyServiceImpl extends AbstractBaseEntityService<Party, PartyDao> implements PartyService {

    private final PartyDao partyDao;

    @Autowired
    private ItemService itemService;

    @Autowired
    public PartyServiceImpl(PartyDao partyDao) {
        this.partyDao = partyDao;
    }

    @Override
    protected PartyDao getDao() {
        return partyDao;
    }

    @Override
    protected Class<Party> getEntityClass() {
        return Party.class;
    }

    @Override
    public List<PartyDto> getAllPartyDto() {
        List<Party> parties = super.getAll();

        if (isEmpty(parties))
            return Collections.emptyList();

        return parties
                .stream()
                .map(this::convertToDto)
                .collect(toList());
    }

    @Override
    public PartyDto getPartyDtoById(Long id) {
        return convertToDto(super.getById(id));
    }

    @Override
    public PartyDetailDto getPartyDetailDtoById(Long id) {
        return convertToDtoWithDetails(super.getById(id));
    }

    @Override
    public PartyDto createPartyDto(String json) {
        return convertToDto(super.create(json));
    }

    private PartyDto convertToDto(Party party) {
        return new PartyDto(party);
    }

    private PartyDetailDto convertToDtoWithDetails(Party party) {
        PartyDetailDto partyDetailDto = new PartyDetailDto(party);
        partyDetailDto.setItemDtos(itemService.getByPartyId(party.getId()));
        return partyDetailDto;
    }

}
