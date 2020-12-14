package ru.test.app.controller.party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.test.app.controller.AbstractController;
import ru.test.app.entity.support.party.PartyDetailDto;
import ru.test.app.entity.support.party.PartyDto;
import ru.test.app.service.party.PartyService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(PartyController.PATH)
public class PartyController extends AbstractController {

    public static final String PATH = MAIN_PATH + "/party/";
    public static final String CREATED_DESCRIPTION = "created";
    public static final String GET_BY_ID_DESCRIPTION = "Party's info";
    public static final String GET_ALL_DESCRIPTION = "list of Parties";

    private final PartyService partyService;

    @Autowired
    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @RequestMapping(value = "", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PartyDto>> getAll() {
        HttpHeaders httpHeaders = getHttpHeadersDescription(GET_ALL_DESCRIPTION);
        return new ResponseEntity<>(partyService.getAllPartyDto(), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "{partyId}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PartyDetailDto> getById(@PathVariable Long partyId) {
        HttpHeaders httpHeaders = getHttpHeadersDescription(GET_BY_ID_DESCRIPTION);
        return new ResponseEntity<>(partyService.getPartyDetailDtoById(partyId), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PartyDto> create(@RequestBody String json) {
        PartyDto partyDto = partyService.createPartyDto(json);
        HttpHeaders httpHeaders = getHttpHeadersDescription(CREATED_DESCRIPTION);
        return new ResponseEntity<>(partyDto, httpHeaders, HttpStatus.CREATED);
    }

}
