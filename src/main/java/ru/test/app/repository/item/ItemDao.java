package ru.test.app.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.test.app.entity.item.Item;

import java.util.List;

@Repository
public interface ItemDao extends JpaRepository<Item, Long> {

    @Query("SELECT items FROM Item items JOIN items.party party WHERE party.id = :partyId")
    List<Item> getByPartyId(@Param("partyId") Long partyId);

    @Query("SELECT item FROM Item item WHERE item.party IS NOT NULL AND item.type = :typeId AND item.parentItem IS NULL")
    List<Item> getAllWithoutParentIdByType(@Param("typeId") Long typeId);

    @Query("SELECT item FROM Item item WHERE item.party IS NOT NULL AND item.parentItem IS NOT NULL AND item.type = :typeId AND item.id = :itemId")
    Item getByIdAndType(@Param("itemId") Long itemId, @Param("typeId") Long typeId);

}
