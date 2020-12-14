package ru.test.app.repository.party;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.app.entity.party.Party;

@Repository
public interface PartyDao extends JpaRepository<Party, Long> {
}
