package ru.test.app.entity.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.test.app.entity.AbstractBaseEntityWithCreateDate;
import ru.test.app.entity.party.Party;

import javax.persistence.*;
import java.util.List;

import static java.util.Objects.isNull;
import static ru.test.app.entity.EntityTableName.ITEM;

@Entity
@Table(name = ITEM)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public class Item extends AbstractBaseEntityWithCreateDate {

    private Long id;
    private String serial;
    private Long type;
    private Long childrenCount;
    private Party party;
    private Item parentItem;
    private List<Item> childrenItems;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "serial")
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Column(name = "type")
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Column(name = "children_count")
    public Long getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Long childrenCount) {
        this.childrenCount = childrenCount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Item getParentItem() {
        return parentItem;
    }

    public void setParentItem(Item parentItem) {
        this.parentItem = parentItem;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "parentItem")
    @JsonIgnore
    public List<Item> getChildrenItems() {
        return childrenItems;
    }

    public void setChildrenItems(List<Item> childrenItems) {
        this.childrenItems = childrenItems;
    }

}
