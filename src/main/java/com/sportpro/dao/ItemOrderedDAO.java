package com.sportpro.dao;

import com.sportpro.entity.ItemOrdered;
import org.springframework.stereotype.Repository;

@Repository
public class ItemOrderedDAO extends SuperDAOImpl<ItemOrdered, Integer> {

    public ItemOrderedDAO() {
        super(ItemOrdered.class);
    }
}
