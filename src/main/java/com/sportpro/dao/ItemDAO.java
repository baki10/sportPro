package com.sportpro.dao;

import com.sportpro.entity.Item;
import com.sportpro.entity.ItemPK;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAO extends SuperDAOImpl<Item, ItemPK> {

    public ItemDAO() {
        super(Item.class);
    }

    public List<Item> getListByCategory(int i) {
        TypedQuery<Item> namedQuery = em.createNamedQuery("Item.findByCategory", Item.class);
        namedQuery.setParameter("categoryId", i);
        return namedQuery.getResultList();
    }

    public List<Item> getListByItemNo(int itemNo) {
        TypedQuery<Item> namedQuery = em.createNamedQuery("Item.findByItemNo", Item.class);
        namedQuery.setParameter("itemNo", itemNo);
        return namedQuery.getResultList();
    }
}
