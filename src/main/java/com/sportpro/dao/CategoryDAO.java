package com.sportpro.dao;

import com.sportpro.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAO extends SuperDAOImpl<Category, Integer> {

    public CategoryDAO() {
        super(Category.class);
    }
}
